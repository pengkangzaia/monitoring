package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.ThreadEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:38
 */
@Mapper
public interface ThreadDao {
    List<ThreadEntity> findAllByAddressAndName(String address, String name);

    Integer save(ThreadEntity threadEntity);

    void deleteAll();
}
