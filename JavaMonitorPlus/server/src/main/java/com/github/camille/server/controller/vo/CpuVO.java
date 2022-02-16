package com.github.camille.server.controller.vo;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 16:36
 **/
@Data
public class CpuVO {

    private String address;
    private Double cpuUsage;
    private Double fifteenMinuteLoad;
    private Double fiveMinuteLoad;
    private Double oneMinuteLoad;
    private String date;


}
