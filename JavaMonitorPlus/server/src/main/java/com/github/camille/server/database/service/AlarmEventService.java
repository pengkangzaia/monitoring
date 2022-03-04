package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.AlarmEventDao;
import com.github.camille.server.database.entity.alarm.AlarmEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-03 20:08
 **/
@Service
public class AlarmEventService {

    @Autowired
    private AlarmEventDao alarmEventDao;


    public List<AlarmEvent> getActiveEventByHostId(int hostId) {
        return alarmEventDao.selectActiveByHostId(hostId);
    }


    public void save(AlarmEvent alarmEvent) {
        alarmEventDao.insert(alarmEvent);
    }

    public AlarmEvent getEventById(int eventId) {
        return alarmEventDao.selectById(eventId);
    }

    /**
     * 设置无告警状态并且设置告警停止时间
     *
     * @param event
     */
    public void releaseAlarm(AlarmEvent event) {
        event.setIsAlarm(0);
        alarmEventDao.updateStatus(event);
    }

    public List<AlarmEvent> list() {
        return alarmEventDao.list();
    }


}
