package com.github.camille.server.database.entity.statistic;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 16:19
 **/
@Data
public class MinMaxMetric {

    /**
     * CPU指标历史最大最小值
     */
    private String minCPUUsage;
    private String maxCpuUsage;
    private String minOneMinuteLoad;
    private String maxOneMinuteLoad;
    private String minFiveMinuteLoad;
    private String maxFiveMinuteLoad;
    private String minFifteenMinuteLoad;
    private String maxFifteenMinuteLoad;

    /**
     * 内存指标历史最大最小值
     */
    private String minUsed;
    private String maxUsed;
    private String minUsedPercent;
    private String maxUsedPercent;

    /**
     * 磁盘指标历史最大最小值
     */
    private String minRio;
    private String maxRio;
    private String minWio;
    private String maxWio;
    private String minRkb;
    private String maxRkb;
    private String minWkb;
    private String maxWkb;
    private String minRAwait;
    private String maxRAwait;
    private String minWAwait;
    private String maxWAwait;
    private String minSvctm;
    private String maxSvctm;
    private String minUtil;
    private String maxUtil;


}
