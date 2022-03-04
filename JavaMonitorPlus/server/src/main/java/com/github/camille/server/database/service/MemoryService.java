package com.github.camille.server.database.service;

import com.github.camille.server.client.MemEntityDTO;
import com.github.camille.server.database.metric.MemoryDao;
import com.github.camille.server.database.entity.data.MemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:32
 **/
@Service
public class MemoryService {

    @Autowired
    private MemoryDao memoryDao;


    public List<MemEntity> findAllByAddress(String address) {
        return memoryDao.findAllByAddress(address);
    }


    public void write(String address, Instant date, MemEntityDTO memEntity) {
        MemEntity entity = new MemEntity();
        entity.setAddress(address);
        entity.setDate(date);
        entity.setUsed(Double.valueOf(memEntity.getUsed()));
        entity.setUsedPercent(Double.valueOf(memEntity.getUsedPercent()));
        memoryDao.save(entity);
    }

    public void clearAll() {
        memoryDao.deleteAll();
    }
}
