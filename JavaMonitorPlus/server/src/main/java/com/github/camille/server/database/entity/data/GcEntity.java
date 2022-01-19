package com.github.camille.server.database.entity.data;

import lombok.Data;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 20:39
 */
@Data

public class GcEntity {

    private Integer id;
    private String address; //进程所在主机
    private String name; //进程ID
    private String date; //x：时间
    private String s0c;
    private String s1c;
    private String s0u;
    private String s1u;
    private String ec;
    private String eu;
    private String oc;
    private String ou;
    private String mc;//PC
    private String mu;//PU
    private String ccsc;
    private String ccsu;
    private String ygc;
    private String ygct;
    private String fgc;
    private String fgct;
    private String gct;

}
