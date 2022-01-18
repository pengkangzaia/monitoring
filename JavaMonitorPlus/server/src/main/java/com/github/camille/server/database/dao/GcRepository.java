package com.github.camille.server.database.dao;


import com.github.camille.server.database.entity.GcEntity;
import org.apache.ibatis.annotations.Mapper;

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
