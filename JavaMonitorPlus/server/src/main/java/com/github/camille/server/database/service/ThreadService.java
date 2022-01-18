package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.JstackEntity;
import com.github.camille.server.database.dao.ThreadRepository;
import com.github.camille.server.database.entity.ThreadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:56
 */
@Service
public class ThreadService {
    @Autowired
    private ThreadRepository threadRepository;

    public List<ThreadEntity> findAllByAddressAndName(String address, String name) {
        return threadRepository.findAllByAddressAndName(address, name);
    }

    public void write(String address, String name, String date, JstackEntity jstatk) {
        ThreadEntity entity = new ThreadEntity();
        entity.setAddress(address);
        entity.setName(name);
        entity.setDate(date);
        entity.setTotal(jstatk.getTotal());
        entity.setRUNNABLE(jstatk.getRUNNABLE());
        entity.setTIMED_WAITING(jstatk.getTIMED_WAITING());
        entity.setWAITING(jstatk.getWAITING());
        threadRepository.save(entity);
    }

    public void clear() {

    }

    public void clearAll() {
        threadRepository.deleteAll();
    }
}
