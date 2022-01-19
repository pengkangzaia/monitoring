package com.github.camille.server.database.entity.alarm;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 11:39
 **/
@Data
public class AlarmConfig {

    private int id;
    private String address;
    private String alarmEmail;


}
