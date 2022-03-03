package com.github.camille.server.database.entity.alarm;

import lombok.Data;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-03 15:41
 **/
@Data
public class AlarmEvent {

    private int id;
    private int hostId;
    private String metricName;
    private String content;
    private int isAlarm;
    private Date createTime;
    private Date modifyTime;


}
