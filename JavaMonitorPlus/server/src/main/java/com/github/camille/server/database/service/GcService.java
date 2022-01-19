package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.KVEntity;
import com.github.camille.server.database.dao.GcRepository;
import com.github.camille.server.database.entity.data.GcEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:54
 */
@Service
public class GcService {
    @Autowired
    private GcRepository gcRepository;

    public void write(String address, String name, String date, List<KVEntity> kvEntities) {
        GcEntity entity = new GcEntity();
        entity.setAddress(address);
        entity.setName(name);
        entity.setDate(date);
        entity.setS0c(kvEntities.get(0).getValue());
        entity.setS1c(kvEntities.get(1).getValue());
        entity.setS0u(kvEntities.get(2).getValue());
        entity.setS1u(kvEntities.get(3).getValue());
        entity.setEc(kvEntities.get(4).getValue());
        entity.setEu(kvEntities.get(5).getValue());
        entity.setOc(kvEntities.get(6).getValue());
        entity.setOu(kvEntities.get(7).getValue());
        entity.setMc(kvEntities.get(8).getValue());
        entity.setMu(kvEntities.get(9).getValue());
        entity.setCcsc(kvEntities.get(10).getValue());
        entity.setCcsu(kvEntities.get(11).getValue());
        entity.setYgc(kvEntities.get(12).getValue());
        entity.setYgct(kvEntities.get(13).getValue());
        entity.setFgc(kvEntities.get(14).getValue());
        entity.setFgct(kvEntities.get(15).getValue());
        entity.setGct(kvEntities.get(16).getValue());
        gcRepository.save(entity);
    }

    public List<GcEntity> findAllByAddressAndName(String address, String name) {
        return gcRepository.findAllByAddressAndName(address, name);
    }

    public void clearAll() {
        gcRepository.deleteAll();
    }
}
