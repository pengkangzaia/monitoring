package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.alarm.AlarmConditionConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-28 16:54
 **/
@Mapper
public interface AlarmConditionConfigDao {


    void batchInsert(List<AlarmConditionConfig> conditions);

    void update(AlarmConditionConfig config);

    List<AlarmConditionConfig> selectByConfigId(Integer configId);
}
