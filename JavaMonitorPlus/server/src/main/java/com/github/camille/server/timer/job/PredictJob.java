package com.github.camille.server.timer.job;

import com.alibaba.fastjson.JSON;
import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.core.entity.MemoryEntity;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.entity.alarm.AlarmConditionConfig;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.HardDiskEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.*;
import com.github.camille.server.remote.parm.AddressParm;
import com.github.camille.server.remote.util.HttpClient;
import com.github.camille.server.util.ConditionDiagnotor;
import com.github.camille.server.util.MonitorConstant;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AlarmConfigService alarmConfigService;
    @Autowired
    private HostService hostService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<AlarmConfig> alarmConfigs = alarmConfigService.getAllConfig();
        if (CollectionUtils.isEmpty(alarmConfigs)) {
            throw new RuntimeException("没有配置要监控的远程主机策略");
        }
        logger.debug("predict data to check if abnormal...");
        for (AlarmConfig alarmConfig : alarmConfigs) {
            int hostId = alarmConfig.getHostId();
            Host host = hostService.selectById(hostId);
            String add = host.getIp();
            if (alarmConfig.isDynamic()) {
                dynamicPred(alarmConfig, add);
            } else {
                // 静态的
                staticDetect(alarmConfig, add);
            }

        }
    }

    private void staticDetect(AlarmConfig alarmConfig, String address) {
        List<AlarmConditionConfig> conditions = alarmConfigService.selectByConfigId(alarmConfig.getId());
        if (CollectionUtils.isNotEmpty(conditions)) {
            boolean isAbnormal = false;
            for (AlarmConditionConfig condition : conditions) {
                String metric = condition.getMetric();
                String columnName = MonitorConstant.metricMap.get(metric);
                if (metric.startsWith("cpu")) {
                    List<Double> values = cpuService.selectDataByColumnName(address, condition.getContinuePeriod(), columnName);
                    isAbnormal = ConditionDiagnotor.diagnose(condition.getOperator(), condition.getValue(), values);
                } else if (metric.startsWith("mem")) {
                    List<Double> values = memoryService.selectDataByColumnName(address, condition.getContinuePeriod(), columnName);
                    isAbnormal = ConditionDiagnotor.diagnose(condition.getOperator(), condition.getValue(), values);
                } else if (metric.startsWith("disk")) {
                    List<Double> values = diskService.selectDataByColumnName(address, condition.getContinuePeriod(), columnName);
                    isAbnormal = ConditionDiagnotor.diagnose(condition.getOperator(), condition.getValue(), values);
                }
            }
            if (isAbnormal) {
                sendAlarm(alarmConfig, address);
            }
        }
    }

    public void dynamicPred(AlarmConfig alarmConfig, String address) {
        List<CPUEntity> cpuEntities = cpuService.selectPredictData(address, slidingWindowSize);
        List<MemEntity> memoryEntities = memoryService.selectPredictData(address, slidingWindowSize);
        List<HardDiskEntity> diskEntities = diskService.selectPredictData(address, slidingWindowSize);
        if (cpuEntities == null || memoryEntities == null || diskEntities == null) {
            logger.warn("数据库中无数据，无法预测");
        }
        if (cpuEntities.size() == memoryEntities.size() && cpuEntities.size() == diskEntities.size()) {
            // 整合数据
            ArrayList<ArrayList<Double>> res = new ArrayList<>();
            int size = cpuEntities.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Double> list = new ArrayList<>();
                // CPU
                CPUEntity cpuEntity = cpuEntities.get(i);
                list.add(cpuEntity.getCpuUsage());
                list.add(cpuEntity.getOneMinuteLoad());
                list.add(cpuEntity.getFiveMinuteLoad());
                list.add(cpuEntity.getFifteenMinuteLoad());
                // 内存
                MemEntity memoryEntity = memoryEntities.get(i);
                list.add(memoryEntity.getUsed());
                list.add(memoryEntity.getUsedPercent());
                // 磁盘
                HardDiskEntity diskEntity = diskEntities.get(i);
                list.add(diskEntity.getRio());
                list.add(diskEntity.getWio());
                list.add(diskEntity.getRkb());
                list.add(diskEntity.getWkb());
                list.add(diskEntity.getRAwait());
                list.add(diskEntity.getWAwait());
                list.add(diskEntity.getSvctm());
                list.add(diskEntity.getUtil());
                res.add(list);
            }
            // 发送POST请求
            String response = HttpClient.doPost(modelPredUrl, JSON.toJSONString(res), null, "POST");
            response = response.replaceAll("\r\n", "");
            logger.info("主机地址：" + address + " 模型反馈：" + response);
            if ("1".equals(response)) {
                sendAlarm(alarmConfig, address);
            }
            // 大小不相等，无法预测
            logger.warn("数据库中数据不足，当前无法预测，请稍后再试");
        }
    }


    public void sendAlarm(AlarmConfig alarmConfig, String address) {
        // 转到报警流程
        MailEntity mail = new MailEntity();
        mail.setSentDate(new Date());
        List<User> noticeUser = alarmConfigService.getNoticeUser(alarmConfig.getId());
        String[] emails = new String[noticeUser.size()];
        for (int i = 0; i < noticeUser.size(); i++) {
            emails[i] = noticeUser.get(i).getEmail();
        }
        mail.setTo(emails);
        mail.setSubject("系统监控告警");
        mail.setText("系统发生异常，请您检查主机" + address + "运行状况");
        mailService.sendSimpleMailMessage(mail);
        System.out.println("我报警了！！！！！！！！！");
    }

}
