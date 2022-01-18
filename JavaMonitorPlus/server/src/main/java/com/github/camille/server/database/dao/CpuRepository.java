package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.CpuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 8:57
 **/
@Mapper
public interface CpuRepository {
    List<CpuEntity> findAllByAddress(String address);

    Integer save(CpuEntity cpuEntity);

    void deleteAll();
}
