package com.github.camille.server.database.entity.data;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:03
 **/
@Data
@Measurement(name = "net")
public class NetEntity {
    @Column(tag = true)
    String address;

    @Column
    Double receivePacket;

    @Column
    Double sendPacket;

    @Column
    Double receive;

    @Column
    Integer tcpCount;

    @Column
    Double send;

    @Column(timestamp = true)
    Instant date;
}
