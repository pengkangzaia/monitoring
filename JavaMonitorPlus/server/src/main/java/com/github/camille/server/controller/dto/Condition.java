package com.github.camille.server.controller.dto;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-24 20:58
 **/
@Data
public class Condition {

    private String metricName;
    private String operator;
    private String value;
    private String continuePeriod;
    private String noticeFrequency;



}
