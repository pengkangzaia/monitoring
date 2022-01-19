package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.CpuInfoEntity;
import com.github.camille.server.database.dao.CPURepository;
import com.github.camille.server.database.entity.data.CPUEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:29
 **/
@Service
public class CPUService {

    @Autowired
    private CPURepository cpuRepository;

    public List<CPUEntity> findAllByAddress(String address) {
        return cpuRepository.findAllByAddress(address);
    }


    public void write(String address, String date, CpuInfoEntity cpuInfo) {
        CPUEntity entity = new CPUEntity();
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

    public List<CPUEntity> selectPredictData(String address, int slidingWindowSize) {
        return cpuRepository.selectLimitByAddress(address, slidingWindowSize);
    }
}
