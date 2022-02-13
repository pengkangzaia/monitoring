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
@Measurement(name = "cpu2")
public class CPUEntity {
    @Column(tag = true)
    String address;

    @Column
    Double cpuUsage;

    @Column
    Double fifteenMinuteLoad;

    @Column
    Double fiveMinuteLoad;

    @Column
    Double oneMinuteLoad;

    @Column(timestamp = true)
    Instant date;
}
