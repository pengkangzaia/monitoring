package com.github.camille.client.order;

import com.github.camille.client.core.entity.*;
import com.github.camille.client.core.order.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by yster@foxmail.com 2018/12/30 0030 16:44
 */
@RestController
public class OrderController {

    @RequestMapping("/cpuLoadInfo")
    public CpuEntity getCpuLoadInfo() {
        return CpuInfo.info();
    }

    @RequestMapping("/memUsage")
    public MemEntity getMemUsage() {
        return MemInfo.usage();
    }

    @RequestMapping("/disk")
    public DiskEntity getDiskInfo() {
        return DiskInfo.usage();
    }

    @RequestMapping("/network")
    public NetEntity getNetInfo() {
        return NetInfo.info();
    }


}
