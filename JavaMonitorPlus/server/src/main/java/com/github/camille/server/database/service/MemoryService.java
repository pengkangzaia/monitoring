package com.github.camille.server.database.service;

import com.github.camille.server.client.MemEntity;
import com.github.camille.server.database.dao.MemoryDao;
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


    public List<com.github.camille.server.database.entity.data.MemEntity> findAllByAddress(String address) {
        return memoryDao.findAllByAddress(address);
    }


    public void write(String address, Instant date, MemEntity memEntity) {
        com.github.camille.server.database.entity.data.MemEntity entity = new com.github.camille.server.database.entity.data.MemEntity();
        entity.setAddress(address);
        entity.setDate(date);
        entity.setUsed(Double.valueOf(memEntity.getUsed()));
        entity.setUsedPercent(Double.valueOf(memEntity.getUsedPercent()));
        memoryDao.save(entity);
    }

    public void clearAll() {
        memoryDao.deleteAll();
    }


    public List<com.github.camille.server.database.entity.data.MemEntity> selectPredictData(String address, int slidingWindowSize) {
        return memoryDao.selectLimitByAddress(address, slidingWindowSize);
    }

    public List<Double> selectDataByColumnName(String address, Integer continuePeriod, String columnName) {
        return memoryDao.selectByColumn(address, continuePeriod, columnName);
    }
}
