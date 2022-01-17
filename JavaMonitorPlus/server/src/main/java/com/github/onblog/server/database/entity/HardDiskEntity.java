package com.github.onblog.server.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:34
 **/
@Data
@Entity
@Table(name = "disk_table")
public class HardDiskEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String address;
    private String date;
    private String rio;
    private String wio;
    private String rkb;
    private String wkb;
    private String rAwait;
    private String wAwait;
    private String svctm;
    private String util;



}
