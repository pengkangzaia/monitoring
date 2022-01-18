package com.github.camille.server.database.dao;


import com.github.camille.server.database.entity.GcEntity;
import com.github.camille.server.database.entity.HardDiskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:38
 */
@Mapper
public interface GcRepository {
    List<GcEntity> findAllByAddressAndName(String address, String name);

    Integer save(GcEntity gcEntity);

    void deleteAll();
}
