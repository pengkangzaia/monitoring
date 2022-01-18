package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.MemEntity;
import com.github.camille.server.database.entity.ThreadEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:38
 */
@Mapper
public interface ThreadRepository {
    List<ThreadEntity> findAllByAddressAndName(String address, String name);

    Integer save(ThreadEntity threadEntity);

    void deleteAll();
}
