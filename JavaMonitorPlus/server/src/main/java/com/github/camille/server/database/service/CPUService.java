package com.github.camille.server.database.service;

import com.github.camille.server.client.CpuEntityDTO;
import com.github.camille.server.database.metric.CPUDao;
import com.github.camille.server.database.entity.data.CPUEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:29
 **/
@Service
public class CPUService {

    @Autowired
    private CPUDao cpuDao;

    public List<CPUEntity> findAllByAddress(String address) {
        return cpuDao.findAllByAddress(address);
    }


    public void write(String address, Instant date, CpuEntityDTO cpuInfo) {
        CPUEntity entity = new CPUEntity();
        entity.setAddress(address);
        entity.setDate(date);
        entity.setCpuUsage(cpuInfo.getCpuUsage());
        entity.setOneMinuteLoad(Double.valueOf(cpuInfo.getOneMinuteLoad()));
        entity.setFiveMinuteLoad(Double.valueOf(cpuInfo.getFiveMinuteLoad()));
        entity.setFifteenMinuteLoad(Double.valueOf(cpuInfo.getFifteenMinuteLoad()));
        cpuDao.save(entity);
    }

    public void clearAll() {
        cpuDao.deleteAll();
    }
}
