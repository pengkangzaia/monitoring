package com.github.camille.server.database.dao;

import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.database.entity.data.HardDiskEntity;
import com.github.camille.server.database.entity.statistic.MinMaxDiskMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:30
 **/
@Mapper
public interface DiskDao {

    List<HardDiskEntity> findAllByAddress(String address);

    Integer save(HardDiskEntity hardDiskEntity);

    void deleteAll();

    MinMaxDiskMetric selectMinMaxMetricByAddress(String address);

    List<DiskEntity> selectLimitByAddress(String address, int limit);
}
