package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.AlarmConfigDao;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 18:12
 **/
@Service
public class AlarmConfigService {

    @Autowired
    private AlarmConfigDao alarmConfigDao;

    public AlarmConfig getAlarmConfigByAddress(String address) {
        return alarmConfigDao.selectByAddress(address);
    }


}
