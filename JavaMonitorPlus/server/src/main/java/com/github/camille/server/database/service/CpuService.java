package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.CpuInfoEntity;
import com.github.camille.server.database.dao.CpuRepository;
import com.github.camille.server.database.entity.CpuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:29
 **/
@Service
public class CpuService {

    @Autowired
    private CpuRepository cpuRepository;

    public List<CpuEntity> findAllByAddress(String address) {
        return cpuRepository.findAllByAddress(address);
    }


    public void write(String address, String date, CpuInfoEntity cpuInfo) {
        CpuEntity entity = new CpuEntity();
        entity.setAddress(address);
        entity.setDate(date);
        entity.setCpuUsage(String.valueOf(cpuInfo.getCpuUsage()));
        entity.setOneMinuteLoad(cpuInfo.getOneMinuteLoad());
        entity.setFiveMinuteLoad(cpuInfo.getFiveMinuteLoad());
        entity.setFifteenMinuteLoad(cpuInfo.getFifteenMinuteLoad());
        cpuRepository.save(entity);
    }

    public void clearAll() {
        cpuRepository.deleteAll();
    }


}
