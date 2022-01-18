package com.github.camille.server.database.entity;

import lombok.Data;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 21:32
 */
@Data
public class ThreadEntity {

    private Integer id;
    private String address; //进程所在主机
    private String name; //进程ID
    private String date; //x：时间
    private int total;
    private int runnable;
    private int timedWaiting;
    private int waiting;
}
