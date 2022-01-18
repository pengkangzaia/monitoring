package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.CpuEntity;
import com.github.camille.server.database.entity.HardDiskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
