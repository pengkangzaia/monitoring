package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.alarm.AlarmUserConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-23 10:41
 **/
@Mapper
public interface AlarmUserConfigDao {
    int insert(AlarmUserConfig userConfig);
}
