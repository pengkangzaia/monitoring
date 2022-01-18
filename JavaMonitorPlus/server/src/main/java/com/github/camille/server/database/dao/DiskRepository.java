package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.HardDiskEntity;
import com.github.camille.server.database.entity.statistic.MinMaxDiskMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:30
 **/
@Mapper
public interface DiskRepository {

    List<HardDiskEntity> findAllByAddress(String address);

    Integer save(HardDiskEntity hardDiskEntity);

    void deleteAll();

    MinMaxDiskMetric selectMinMaxMetricByAddress(String address);
}
