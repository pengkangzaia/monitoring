package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.alarm.AlarmConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 11:37
 **/
@Mapper
public interface AlarmConfigDao {

    AlarmConfig selectByHostId(int hostId);

    int insertConfig(AlarmConfig alarmConfig);

    void updateConfig(AlarmConfig config);

    List<AlarmConfig> selectAll();

    List<AlarmConfig> selectByHostIds(@Param("hostIds") List<Integer> hostIds);
}
