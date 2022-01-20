package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.statistic.MinMaxCPUMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 8:57
 **/
@Mapper
public interface CPUDao {
    List<CPUEntity> findAllByAddress(String address);

    Integer save(CPUEntity cpuEntity);

    void deleteAll();

    MinMaxCPUMetric selectMinMaxMetricByAddress(String address);

    List<CPUEntity> selectLimitByAddress(String address, int limit);
}
