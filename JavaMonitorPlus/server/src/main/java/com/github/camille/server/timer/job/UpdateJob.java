package com.github.camille.server.timer.job;


import com.github.camille.server.client.CpuEntityDTO;
import com.github.camille.server.client.DiskEntityDTO;
import com.github.camille.server.client.MemEntityDTO;
import com.github.camille.server.client.NetEntityDTO;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.service.*;
import com.github.camille.server.remote.CallingMethod;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Instant;
import java.util.List;

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
    private HostService hostService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<Host> aliveHost = hostService.getAliveHost();
        if (CollectionUtils.isEmpty(aliveHost)) {
            throw new RuntimeException("当前无在线主机");
        }
        logger.debug("Regularly updated data...");
        Instant instant = Instant.now();
        for (Host host : aliveHost) {
            String ip = host.getIp();
            String address = "http://" + ip + ":" + host.getAgentPort();
            try {
                CpuEntityDTO cpuInfo = CallingMethod.getCpuInfo(address);
                MemEntityDTO memEntity = CallingMethod.getMemoryUsage(address);
                DiskEntityDTO diskEntity = CallingMethod.getDiskInfo(address);
                NetEntityDTO netEntity = CallingMethod.getNetInfo(address);
                //写入系统当前CPU使用信息
                cpuService.write(address, instant, cpuInfo);
                //写入系统当前内存使用信息
                memoryService.write(address, instant, memEntity);
                diskService.write(address, instant, diskEntity);
                networkService.write(address, instant, netEntity);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
