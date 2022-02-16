package com.github.camille.server.database.entity.data;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:34
 **/
@Data
@Measurement(name = "disk")
public class DiskEntity {

    @Column(tag = true)
    private String address;
    @Column(timestamp = true)
    private Instant date;
    @Column
    private Double rio;
    @Column
    private Double wio;
    @Column
    private Double rkb;
    @Column
    private Double wkb;
    @Column
    private Double rAwait;
    @Column
    private Double wAwait;
    @Column
    private Double svctm;
    @Column
    private Double util;



}
