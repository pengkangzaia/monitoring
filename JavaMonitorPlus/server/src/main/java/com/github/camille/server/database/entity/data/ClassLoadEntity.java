package com.github.camille.server.database.entity.data;

import lombok.Data;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 20:59
 */
@Data
public class ClassLoadEntity {

    private Integer id;
    private String address; //进程所在主机
    private String name; //进程ID
    private String date; //x：时间
    private String loaded;
    private String bytes1;
    private String unloaded;
    private String bytes2;
    private String time1;
    private String compiled;
    private String failed;
    private String invalid;
    private String time2;

}
