package com.github.camille.server.timer.job;

import com.alibaba.fastjson.JSON;
import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.core.entity.MemoryEntity;
import com.github.camille.server.database.dao.AlarmConfigRepository;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.service.*;
import com.github.camille.server.remote.parm.AddressParm;
import com.github.camille.server.remote.parm.entity.Address;
import com.github.camille.server.remote.util.HttpClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 19:00
 **/
public class PredictJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${model.sliding.window}")
    private int slidingWindowSize;
    @Value("${model.pred.url}")
    private String modelPredUrl;
    @Autowired
    private AddressParm addressParm;
    @Autowired
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AlarmConfigService alarmConfigService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        if (addressParm.getServe() == null || addressParm.getServe().size() == 0) {
            throw new RuntimeException("没有配置要监控的远程主机");
        }
        logger.debug("predict data to check if abnormal...");
        for (Address address : addressParm.getServe()) {
            String add = address.getAddress();
            List<CPUEntity> cpuEntities = cpuService.selectPredictData(add, slidingWindowSize);
            List<MemoryEntity> memoryEntities = memoryService.selectPredictData(add, slidingWindowSize);
            List<DiskEntity> diskEntities = diskService.selectPredictData(add, slidingWindowSize);
            if (cpuEntities == null || memoryEntities == null || diskEntities == null) {
                logger.warn("数据库中无数据，无法预测");
                continue;
            }
            if (cpuEntities.size() == memoryEntities.size() && cpuEntities.size() == diskEntities.size()) {
                // 整合数据
                ArrayList<ArrayList<Double>> res = new ArrayList<>();
                int size = cpuEntities.size();
                for (int i = 0; i < size; i++) {
                    ArrayList<Double> list = new ArrayList<>();
                    // CPU
                    CPUEntity cpuEntity = cpuEntities.get(i);
                    list.add(Double.valueOf(cpuEntity.getCpuUsage()));
                    list.add(Double.valueOf(cpuEntity.getOneMinuteLoad()));
                    list.add(Double.valueOf(cpuEntity.getFiveMinuteLoad()));
                    list.add(Double.valueOf(cpuEntity.getFifteenMinuteLoad()));
                    // 内存
                    MemoryEntity memoryEntity = memoryEntities.get(i);
                    list.add(Double.valueOf(memoryEntity.getUsed()));
                    list.add(Double.valueOf(memoryEntity.getUsedPercent()));
                    // 磁盘
                    DiskEntity diskEntity = diskEntities.get(i);
                    list.add(Double.valueOf(diskEntity.getRio()));
                    list.add(Double.valueOf(diskEntity.getWio()));
                    list.add(Double.valueOf(diskEntity.getRkb()));
                    list.add(Double.valueOf(diskEntity.getWkb()));
                    list.add(Double.valueOf(diskEntity.getRAwait()));
                    list.add(Double.valueOf(diskEntity.getWAwait()));
                    list.add(Double.valueOf(diskEntity.getSvctm()));
                    list.add(Double.valueOf(diskEntity.getUtil()));
                    res.add(list);
                }
                // 发送POST请求
                String response = HttpClient.doPost(modelPredUrl, JSON.toJSONString(res), null, "POST");
                response = response.replaceAll("\r\n", "");
                logger.info("主机地址：" + add + " 模型反馈：" + response);
                if ("1".equals(response)) {
                    // 转到报警流程
                    MailEntity mail = new MailEntity();
                    mail.setSentDate(new Date());
                    AlarmConfig alarmConfig = alarmConfigService.getAlarmConfigByAddress(add);
                    String alarmEmails = alarmConfig.getAlarmEmail();
                    String[] emails = alarmEmails.split(",");
                    mail.setTo(emails);
                    mail.setSubject("系统监控告警");
                    mail.setText("系统发生异常，请您检查主机" + add + "运行状况");
                    mailService.sendSimpleMailMessage(mail);
                    System.out.println("我报警了！！！！！！！！！");
                }
                continue;
            }
            // 大小不相等，无法预测
            logger.warn("数据库中数据不足，当前无法预测，请稍后再试");
        }
    }
}
