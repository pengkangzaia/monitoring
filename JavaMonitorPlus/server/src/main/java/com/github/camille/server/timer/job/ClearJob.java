package com.github.camille.server.timer.job;


import com.github.camille.server.database.service.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Create by yster@foxmail.com 2018/11/14 0014 21:01
 */
public class ClearJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.warn("Clear all data on a regular basis");
        cpuService.clearAll();
        memoryService.clearAll();
        diskService.clearAll();
    }
}
