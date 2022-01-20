package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.CPUDao;
import com.github.camille.server.database.dao.DiskDao;
import com.github.camille.server.database.dao.MemoryDao;
import com.github.camille.server.database.entity.statistic.MinMaxCPUMetric;
import com.github.camille.server.database.entity.statistic.MinMaxDiskMetric;
import com.github.camille.server.database.entity.statistic.MinMaxMemMetric;
import com.github.camille.server.database.entity.statistic.MinMaxMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 16:13
 **/
@Service
public class StatisticsService {

    @Autowired
    private CPUDao cpuDao;
    @Autowired
    private MemoryDao memoryDao;
    @Autowired
    private DiskDao diskDao;

    public MinMaxMetric getMinMaxMetric(String address) {
        MinMaxMetric minMaxMetric = new MinMaxMetric();
        MinMaxCPUMetric cpuMinMaxMetric = cpuDao.selectMinMaxMetricByAddress(address);
        MinMaxMemMetric memMinMaxMetric = memoryDao.selectMinMaxMetricByAddress(address);
        MinMaxDiskMetric diskMinMaxMetric = diskDao.selectMinMaxMetricByAddress(address);
        // CPU
        minMaxMetric.setMinCPUUsage(cpuMinMaxMetric.getMinCPUUsage());
        minMaxMetric.setMaxCpuUsage(cpuMinMaxMetric.getMaxCpuUsage());
        minMaxMetric.setMinOneMinuteLoad(cpuMinMaxMetric.getMinOneMinuteLoad());
        minMaxMetric.setMaxOneMinuteLoad(cpuMinMaxMetric.getMaxOneMinuteLoad());
        minMaxMetric.setMinFiveMinuteLoad(cpuMinMaxMetric.getMinFiveMinuteLoad());
        minMaxMetric.setMaxFiveMinuteLoad(cpuMinMaxMetric.getMaxFiveMinuteLoad());
        minMaxMetric.setMinFifteenMinuteLoad(cpuMinMaxMetric.getMinFifteenMinuteLoad());
        minMaxMetric.setMaxFifteenMinuteLoad(cpuMinMaxMetric.getMaxFifteenMinuteLoad());
        // Mem
        minMaxMetric.setMinUsed(memMinMaxMetric.getMinUsed());
        minMaxMetric.setMaxUsed(memMinMaxMetric.getMaxUsed());
        minMaxMetric.setMinUsedPercent(memMinMaxMetric.getMinUsedPercent());
        minMaxMetric.setMaxUsedPercent(memMinMaxMetric.getMaxUsedPercent());
        // Disk
        minMaxMetric.setMinRio(diskMinMaxMetric.getMinRio());
        minMaxMetric.setMaxRio(diskMinMaxMetric.getMaxRio());
        minMaxMetric.setMinWio(diskMinMaxMetric.getMinWio());
        minMaxMetric.setMaxWio(diskMinMaxMetric.getMaxWio());

        minMaxMetric.setMinRkb(diskMinMaxMetric.getMinRkb());
        minMaxMetric.setMaxRkb(diskMinMaxMetric.getMaxRkb());
        minMaxMetric.setMinWkb(diskMinMaxMetric.getMinWkb());
        minMaxMetric.setMaxWkb(diskMinMaxMetric.getMaxWkb());

        minMaxMetric.setMinRAwait(diskMinMaxMetric.getMinRAwait());
        minMaxMetric.setMaxRAwait(diskMinMaxMetric.getMaxRAwait());
        minMaxMetric.setMinWAwait(diskMinMaxMetric.getMinWAwait());
        minMaxMetric.setMaxWAwait(diskMinMaxMetric.getMaxWAwait());

        minMaxMetric.setMinSvctm(diskMinMaxMetric.getMinSvctm());
        minMaxMetric.setMaxSvctm(diskMinMaxMetric.getMaxSvctm());
        minMaxMetric.setMinUtil(diskMinMaxMetric.getMinUtil());
        minMaxMetric.setMaxUtil(diskMinMaxMetric.getMaxUtil());
        return minMaxMetric;
    }


}
