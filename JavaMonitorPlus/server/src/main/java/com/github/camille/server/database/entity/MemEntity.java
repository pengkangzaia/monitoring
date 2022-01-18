package com.github.camille.server.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:27
 **/
@Data
public class MemEntity {

    private Integer id;
    private String address; //进程所在主机
    private String date; //x：时间
    private String used;
    private String usedPercent;


}
