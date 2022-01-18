package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.HardDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:30
 **/
@Repository
public interface DiskRepository extends JpaRepository<HardDiskEntity, Integer> {

    List<HardDiskEntity> findAllByAddress(String address);

}
