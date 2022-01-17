package com.github.onblog.client.core.order;

import com.github.onblog.client.core.cmd.ExecuteCmd;
import com.github.onblog.client.core.entity.CpuInfoEntity;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-10-31 14:49
 **/
public class CpuInfo {

    public static CpuInfoEntity info() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
        // What % load the overall system is at, from 0.0-1.0
        double cpuUsage = osBean.getSystemCpuLoad();
        String loads = ExecuteCmd.execute(new String[]{"sh", "-c", "cat /proc/loadavg | awk '{for(i = 1; i <=3; i++) {print $i}}'"});
        String[] loadInfo = loads.split("\n");
        return new CpuInfoEntity(cpuUsage, loadInfo[0], loadInfo[1], loadInfo[2]);
    }

    public static void main(String[] args) {
        System.out.println(info());
    }



}
