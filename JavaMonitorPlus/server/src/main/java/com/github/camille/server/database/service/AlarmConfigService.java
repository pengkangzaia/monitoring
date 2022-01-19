package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.AlarmConfigRepository;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 18:12
 **/
@Service
public class AlarmConfigService {

    @Autowired
    private AlarmConfigRepository alarmConfigRepository;

    public AlarmConfig getAlarmConfigByAddress(String address) {
        return alarmConfigRepository.selectByAddress(address);
    }


}
