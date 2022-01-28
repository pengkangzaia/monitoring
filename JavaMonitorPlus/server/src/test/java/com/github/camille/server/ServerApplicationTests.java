package com.github.camille.server;

import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.controller.dto.Condition;
import com.github.camille.server.database.dao.AlarmConfigDao;
import com.github.camille.server.database.dao.HostDao;
import com.github.camille.server.database.dao.ThresholdDao;
import com.github.camille.server.database.dao.UserDao;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.entity.alarm.AlarmConditionConfig;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.data.Threshold;
import com.github.camille.server.database.entity.statistic.MinMaxMetric;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.AlarmConfigService;
import com.github.camille.server.database.service.MailService;
import com.github.camille.server.database.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {


    @Autowired
    private ThresholdDao thresholdDao;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private AlarmConfigDao alarmConfigDao;
    @Autowired
    private MailService mailService;
    @Autowired
    private HostDao hostDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AlarmConfigService alarmConfigService;

    @Test
    public void thresholdTest() {
        Threshold threshold = thresholdDao.getThresholdByAddress("http://101.35.159.221:8081");
        System.out.println(threshold);
    }

    @Test
    public void statisticsTest() {
        MinMaxMetric res = statisticsService.getMinMaxMetric("http://1.15.117.64:8081");
        System.out.println(res);
    }

    @Test
    public void alarmConfigTest() {
        for (int i = 4; i < 10; i++) {
            AlarmConfig config = new AlarmConfig();
            config.setHostId(i);
            config.setDynamic(true);
            config.setName("告警配置测试" + i);
            config.setRemark("备注");
            config.setEmailNotice(true);
            config.setPhoneNotice(false);
            int res = alarmConfigDao.insertConfig(config);
            System.out.println(config.getId());
        }
//        AlarmConfig config = alarmConfigDao.selectByHostId(1);
//        System.out.println(config);
    }

    @Test
    public void sendMailTest() {
        MailEntity mail = new MailEntity();
        mail.setSentDate(new Date());
        mail.setTo(new String[]{"1029560353@qq.com"});
        mail.setSubject("发送邮件测试");
        mail.setText("系统发生异常，我要报警啦！");
        mailService.sendSimpleMailMessage(mail);
    }

    @Test
    public void insertHost() {
        for (int i = 0; i < 1000; i++) {
            Host host = new Host();
            host.setIp(i + "");
            host.setName("主机" + i);
            host.setDesc("这是第" + i + "个主机");
            hostDao.insert(host);
        }
    }

    @Test
    public void selectByUserIds() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<User> res = userDao.selectByIds(list);
        System.out.println(res);
    }

    @Test
    public void condition() {
        List<Condition> conditions = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setMetric("camille");
        condition1.setOperator("<");
        condition1.setValue(10.01);
        condition1.setId(1);
//        condition1.setContinuePeriod(3);
//        condition1.setNoticeFrequency(3600);
        conditions.add(condition1);


        Condition condition2 = new Condition();
        condition2.setId(2);
        condition2.setMetric("cau");
        condition2.setOperator(">=");
        condition2.setValue(101.0);
        condition2.setContinuePeriod(4);
        condition2.setNoticeFrequency(3603);
        conditions.add(condition2);
        alarmConfigService.updateCondition(conditions);
//        alarmConfigService.saveCondition(conditions, 3);

    }


}

