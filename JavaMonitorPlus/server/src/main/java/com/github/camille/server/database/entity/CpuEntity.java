package com.github.camille.server.database.entity;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:03
 **/
@Data
public class CpuEntity {

    private Integer id;
    private String address; //进程所在主机
    private String date; //x：时间
    private String cpuUsage;
    private String oneMinuteLoad;
    private String fiveMinuteLoad;
    private String fifteenMinuteLoad;


}
