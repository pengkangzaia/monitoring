package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.AlarmConfigDao;
import com.github.camille.server.database.dao.AlarmUserConfigDao;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.alarm.AlarmUserConfig;
import com.github.camille.server.database.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserService userService;

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


    public List<User> getNoticeUser(int configId) {
        List<AlarmUserConfig> userConfigs = alarmUserConfigDao.selectByConfigId(configId);
        if (userConfigs == null) {
            return new ArrayList<>();
        }
        List<Integer> userIds = userConfigs.stream().map(AlarmUserConfig::getUserId).collect(Collectors.toList());
        return userService.findUserByIds(userIds);

    }

    public void editConfig(AlarmConfig config) {
        alarmConfigDao.updateConfig(config);
    }

    public void updateNoticeUser(int configId, int noticeUserId) {
        alarmUserConfigDao.deleteByConfigId(configId);
        AlarmUserConfig userConfig = new AlarmUserConfig();
        userConfig.setUserId(noticeUserId);
        userConfig.setConfigId(configId);
        alarmUserConfigDao.insert(userConfig);
    }
}
