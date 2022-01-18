package com.github.camille.client.core.entity;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-10-31下午6:50
 **/
@Data
public class CpuInfoEntity {

    /**
     * CPU利用率
     */
    private double cpuUsage;
    /**
     *  一分钟CPU平均负载
     */
    private String oneMinuteLoad;
    /**
     * 五分钟CPU平均负载
     */
    private String fiveMinuteLoad;
    /**
     * 十五分钟CPU平均负载
     */
    private String fifteenMinuteLoad;

    public CpuInfoEntity(double cpuUsage, String oneMinuteLoad, String fiveMinuteLoad, String fifteenMinuteLoad) {
        this.cpuUsage = cpuUsage;
        this.oneMinuteLoad = oneMinuteLoad;
        this.fiveMinuteLoad = fiveMinuteLoad;
        this.fifteenMinuteLoad = fifteenMinuteLoad;
    }
}
