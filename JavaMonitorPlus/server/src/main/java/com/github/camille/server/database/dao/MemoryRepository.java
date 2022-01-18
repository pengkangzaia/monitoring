package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.MemEntity;
import com.github.camille.server.database.entity.statistic.MinMaxMemMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:00
 **/
@Mapper
public interface MemoryRepository {

    List<MemEntity> findAllByAddress(String address);

    Integer save(MemEntity memEntity);

    void deleteAll();

    MinMaxMemMetric selectMinMaxMetricByAddress(String address);
}
