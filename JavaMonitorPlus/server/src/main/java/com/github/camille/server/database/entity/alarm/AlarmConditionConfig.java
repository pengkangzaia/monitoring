package com.github.camille.server.database.entity.alarm;

import lombok.Data;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-28 18:44
 **/
@Data
public class AlarmConditionConfig {

    private Integer id;
    private Integer configId;
    private String metric;
    private String operator;
    private Double value;
    private Integer continuePeriod;
    private Integer noticeFrequency;
    private Date modifyTime;
    private Date createTime;


}
