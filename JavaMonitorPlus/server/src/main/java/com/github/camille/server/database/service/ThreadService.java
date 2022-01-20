package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.JstackEntity;
import com.github.camille.server.database.dao.ThreadDao;
import com.github.camille.server.database.entity.data.ThreadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:56
 */
@Service
public class ThreadService {
    @Autowired
    private ThreadDao threadDao;

    public List<ThreadEntity> findAllByAddressAndName(String address, String name) {
        return threadDao.findAllByAddressAndName(address, name);
    }

    public void write(String address, String name, String date, JstackEntity jstatk) {
        ThreadEntity entity = new ThreadEntity();
        entity.setAddress(address);
        entity.setName(name);
        entity.setDate(date);
        entity.setTotal(jstatk.getTotal());
        entity.setRunnable(jstatk.getRUNNABLE());
        entity.setTimedWaiting(jstatk.getTIMED_WAITING());
        entity.setWaiting(jstatk.getWAITING());
        threadDao.save(entity);
    }

    public void clear() {

    }

    public void clearAll() {
        threadDao.deleteAll();
    }
}
