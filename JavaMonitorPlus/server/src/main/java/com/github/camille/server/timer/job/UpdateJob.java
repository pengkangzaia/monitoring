package com.github.camille.server.timer.job;


import com.github.camille.server.client.CpuEntityDTO;
import com.github.camille.server.client.DiskEntityDTO;
import com.github.camille.server.client.MemEntityDTO;
import com.github.camille.server.client.NetEntityDTO;
import com.github.camille.server.database.service.*;
import com.github.camille.server.remote.CallingMethod;
import com.github.camille.server.remote.parm.AddressParm;
import com.github.camille.server.remote.parm.entity.Address;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Instant;

/**
 * Create by yster@foxmail.com 2018/11/11 0011 15:25
 */
public class UpdateJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private AddressParm address;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        if (address.getServe() == null || address.getServe().size() == 0) {
            throw new RuntimeException("没有配置要监控的远程主机");
        }
        logger.debug("Regularly updated data...");
        for (Address address : address.getServe()) {
            String addressAddress = address.getAddress();
            try {
                CpuEntityDTO cpuInfo = CallingMethod.getCpuInfo(addressAddress);
                MemEntityDTO memEntity = CallingMethod.getMemoryUsage(addressAddress);
                DiskEntityDTO diskEntity = CallingMethod.getDiskInfo(addressAddress);
                NetEntityDTO netEntity = CallingMethod.getNetInfo(addressAddress);
                Instant instant = Instant.now();
                //写入系统当前CPU使用信息
                cpuService.write(addressAddress, instant, cpuInfo);
                //写入系统当前内存使用信息
                memoryService.write(addressAddress, instant, memEntity);
                diskService.write(addressAddress, instant, diskEntity);
                networkService.write(addressAddress, instant, netEntity);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
