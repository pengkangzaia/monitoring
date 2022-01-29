package com.github.camille.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 16:46
 **/
public class MonitorConstant {

    public static final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;


    public static final Map<String, String> metricMap;

    static {
        // key=metricName, value=columnName
        metricMap = new HashMap<>();
        metricMap.put("cpuUsage", "cpu_usage");
        metricMap.put("cpu1Load", "one_minute_load");
        metricMap.put("cpu5Load", "five_minute_load");
        metricMap.put("cpu15Load", "fifteen_minute_load");
        metricMap.put("memUsed", "used");
        metricMap.put("memUsedPercent", "used_percent");
        metricMap.put("diskRawait", "r_await");
        metricMap.put("diskRio", "rio");
        metricMap.put("diskRkb", "rkb");
        metricMap.put("diskSvctm", "svctm");
        metricMap.put("diskUtil", "util");
        metricMap.put("diskWawait", "w_await");
        metricMap.put("diskWio", "wio");
        metricMap.put("diskWkb", "wkb");
    }



}
