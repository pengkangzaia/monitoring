package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.alarm.AlarmEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-03 15:38
 **/
@Mapper
public interface AlarmEventDao {

    int insert(AlarmEvent alarmEvent);


}
