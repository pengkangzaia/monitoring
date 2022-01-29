package com.github.camille.server.database.dao;

import com.github.camille.server.core.entity.MemoryEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.statistic.MinMaxMemMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:00
 **/
@Mapper
public interface MemoryDao {

    List<MemEntity> findAllByAddress(String address);

    Integer save(MemEntity memEntity);

    void deleteAll();

    MinMaxMemMetric selectMinMaxMetricByAddress(String address);

    List<MemoryEntity> selectLimitByAddress(String address, int limit);

    List<String> selectByColumn(String address, Integer limit, String columnName);
}
