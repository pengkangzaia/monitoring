package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.MemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:00
 **/
@Repository
public interface MemoryRepository extends JpaRepository<MemEntity, Integer> {

    List<MemEntity> findAllByAddress(String address);
}
