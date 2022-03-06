package com.github.camille.server.timer.job;

import com.github.camille.server.database.entity.alarm.AlarmEvent;
import com.github.camille.server.database.service.AlarmEventService;
import com.github.camille.server.database.service.AlarmService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-03 19:48
 **/
@DisallowConcurrentExecution
public class AlarmJob implements Job {

    @Autowired
    private AlarmEventService alarmEventService;
    @Autowired
    private AlarmService alarmService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int eventId = (int) jobExecutionContext.getJobDetail().getJobDataMap().get("eventId");
        int alarmConfigId = (int) jobExecutionContext.getJobDetail().getJobDataMap().get("configId");
        AlarmEvent event = alarmEventService.getEventById(eventId);
        // todo 可优化为消息队列
        alarmService.sendAlarmByEmail(event.getContent(), alarmConfigId);
    }




}
