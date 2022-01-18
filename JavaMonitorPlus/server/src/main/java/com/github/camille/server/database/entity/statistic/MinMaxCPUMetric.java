package com.github.camille.server.database.entity.statistic;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 17:53
 **/
@Data
public class MinMaxCPUMetric {

    private String minCPUUsage;
    private String maxCpuUsage;
    private String minOneMinuteLoad;
    private String maxOneMinuteLoad;
    private String minFiveMinuteLoad;
    private String maxFiveMinuteLoad;
    private String minFifteenMinuteLoad;
    private String maxFifteenMinuteLoad;



}
