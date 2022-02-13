package com.github.camille.server.database.entity.data;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:27
 **/
@Data
@Measurement(name = "memory")
public class MemEntity {

    @Column(tag = true)
    String address;

    @Column
    Double used;

    @Column
    Double usedPercent;

    @Column(timestamp = true)
    Instant date;
}
