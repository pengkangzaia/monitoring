package com.github.camille.server.database.dao;


import com.github.camille.server.database.entity.data.ClassLoadEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:38
 */
@Mapper
public interface ClassLoadDao {
    List<ClassLoadEntity> findAllByAddressAndName(String address, String name);

    Integer save(ClassLoadEntity classLoadEntity);

    void deleteAll();
}
