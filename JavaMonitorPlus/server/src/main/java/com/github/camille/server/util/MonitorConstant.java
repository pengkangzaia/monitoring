package com.github.camille.server.util;

import java.util.*;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 16:46
 **/
public class MonitorConstant {

    public static final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    public static final String[] measurements = {"cpu2", "memory", "disk", "net"};

    public static final String underline = "_";

    public static final Set<String> metricSet;

    static {
        // key=metricName, value=field
        metricSet = new HashSet<>();
        metricSet.add(measurements[0] + underline + "cpuUsage");
        metricSet.add(measurements[0] + underline + "oneMinuteLoad");
        metricSet.add(measurements[0] + underline + "fiveMinuteLoad");
        metricSet.add(measurements[0] + underline + "fifteenMinuteLoad");
        metricSet.add(measurements[1] + underline + "used");
        metricSet.add(measurements[1] + underline + "usedPercent");
        metricSet.add(measurements[2] + underline + "rAwait");
        metricSet.add(measurements[2] + underline + "rio");
        metricSet.add(measurements[2] + underline + "rkb");
        metricSet.add(measurements[2] + underline + "svctm");
        metricSet.add(measurements[2] + underline + "util");
        metricSet.add(measurements[2] + underline + "wAwait");
        metricSet.add(measurements[2] + underline + "wio");
        metricSet.add(measurements[2] + underline + "wkb");
        metricSet.add(measurements[3] + underline + "receive");
        metricSet.add(measurements[3] + underline + "receivePacket");
        metricSet.add(measurements[3] + underline + "send");
        metricSet.add(measurements[3] + underline + "sendPacket");
        metricSet.add(measurements[3] + underline + "tcpCount");
    }

    public static final String Dynamic_Alarm_Template = "经动态检测，主机发生异常\n"
            + "告警对象：%s\n" + "告警策略:%s\n" + "告警指标:%s\n" + "触发时间:%s\n";

    public static final String Static_Alarm_Template = "经静态门限阈值检测，主机发生异常\n"
            + "告警对象：%s\n" + "告警策略:%s\n" + "告警指标:%s\n" + "触发时间:%s\n";

}
