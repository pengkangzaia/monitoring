package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.alarm.AlarmConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 11:37
 **/
@Mapper
public interface AlarmConfigDao {

    AlarmConfig selectByAddress(String address);



}
