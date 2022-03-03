package com.github.camille.server.timer.job;

import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.database.entity.alarm.AlarmEvent;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.AlarmConfigService;
import com.github.camille.server.database.service.AlarmEventService;
import com.github.camille.server.database.service.MailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-03 19:48
 **/
@DisallowConcurrentExecution
public class AlarmJob implements Job {

    @Autowired
    private AlarmEventService alarmEventService;
    @Autowired
    private AlarmConfigService alarmConfigService;
    @Autowired
    private MailService mailService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int eventId = (int) jobExecutionContext.getJobDetail().getJobDataMap().get("eventId");
        int alarmConfigId = (int) jobExecutionContext.getJobDetail().getJobDataMap().get("configId");
        AlarmEvent event = alarmEventService.getEventById(eventId);
        // todo 可优化为消息队列
        sendAlarmByEmail(event, alarmConfigId);
    }


    public void sendAlarmByEmail(AlarmEvent event, int alarmConfigId) {
        // 邮件报警
        MailEntity mail = new MailEntity();
        mail.setSentDate(new Date());
        List<User> noticeUser = alarmConfigService.getNoticeUser(alarmConfigId);
        String[] emails = new String[noticeUser.size()];
        for (int i = 0; i < noticeUser.size(); i++) {
            emails[i] = noticeUser.get(i).getEmail();
        }
        mail.setTo(emails);
        mail.setSubject("系统监控告警");
        mail.setText(event.getContent());
        mailService.sendSimpleMailMessage(mail);
    }

}
