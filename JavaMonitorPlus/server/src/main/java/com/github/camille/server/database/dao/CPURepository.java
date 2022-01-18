package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.CpuEntity;
import com.github.camille.server.database.entity.statistic.MinMaxCPUMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 8:57
 **/
@Mapper
public interface CPURepository {
    List<CpuEntity> findAllByAddress(String address);

    Integer save(CpuEntity cpuEntity);

    void deleteAll();

    MinMaxCPUMetric selectMinMaxMetricByAddress(String address);
}
