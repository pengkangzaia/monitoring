package com.github.camille.server;

import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.database.dao.AlarmConfigDao;
import com.github.camille.server.database.dao.ThresholdDao;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.data.Threshold;
import com.github.camille.server.database.entity.statistic.MinMaxMetric;
import com.github.camille.server.database.service.MailService;
import com.github.camille.server.database.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        AlarmConfig res = alarmConfigDao.selectByAddress("http://101.35.159.221:8081");
        System.out.println(res);
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


}

