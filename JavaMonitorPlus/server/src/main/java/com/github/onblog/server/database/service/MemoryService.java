package com.github.onblog.server.database.service;

import com.github.onblog.server.core.entity.MemoryEntity;
import com.github.onblog.server.database.dao.MemoryRepository;
import com.github.onblog.server.database.entity.MemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:32
 **/
@Service
public class MemoryService {

    @Autowired
    private MemoryRepository memoryRepository;


    public List<MemEntity> findAllByAddress(String address) {
        return memoryRepository.findAllByAddress(address);
    }


    public void write(String address, String date, MemoryEntity memoryEntity) {
        MemEntity entity = new MemEntity();
        entity.setAddress(address);
        entity.setDate(date);
        entity.setUsed(memoryEntity.getUsed());
        entity.setUsedPercent(memoryEntity.getUsedPercent());
        memoryRepository.save(entity);
    }

    public void clearAll() {
        memoryRepository.deleteAll();
    }


}
