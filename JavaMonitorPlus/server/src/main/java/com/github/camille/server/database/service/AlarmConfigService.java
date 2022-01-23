package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.AlarmConfigDao;
import com.github.camille.server.database.dao.AlarmUserConfigDao;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.alarm.AlarmUserConfig;
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
    @Autowired
    private AlarmUserConfigDao alarmUserConfigDao;

    public int saveConfig(AlarmConfig config) {
        int res = alarmConfigDao.insertConfig(config);
        return res;
    }


    public AlarmConfig getConfigByHostId(int hostId) {
        AlarmConfig config = alarmConfigDao.selectByHostId(hostId);
        return config;
    }

    public int saveNoticeUser(int configId, int userId) {
        AlarmUserConfig userConfig = new AlarmUserConfig();
        userConfig.setConfigId(configId);
        userConfig.setUserId(userId);
        return alarmUserConfigDao.insert(userConfig);
    }
}
