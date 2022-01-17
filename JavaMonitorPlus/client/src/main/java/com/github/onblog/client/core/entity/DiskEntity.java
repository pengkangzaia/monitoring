package com.github.onblog.client.core.entity;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 12:54
 **/
@Data
public class DiskEntity {

    // 每秒完成的读I/O设备次数（所有设备求和）
    private String rio;
    // 每秒完成的写I/O设备次数（所有设备求和）
    private String wio;
    // 每秒读字节数（所有设备求和）
    private String rkb;
    // 每秒写字节数（所有设备求和）
    private String wkb;
    // 平均每次设备读操作的等待时间（毫秒，所有设备求平均）
    private String rAwait;
    // 平均每次设备写操作的等待时间（毫秒，所有设备求平均）
    private String wAwait;
    // 平均每次设备I/O操作的服务时间（毫秒，所有设备求平均）
    private String svctm;
    // 一秒中CPU有多少时间用于I/O操作（所有设备求平均）
    private String util;

    public DiskEntity(String rio, String wio, String rkb, String wkb, String rAwait, String wAwait, String svctm, String util) {
        this.rio = rio;
        this.wio = wio;
        this.rkb = rkb;
        this.wkb = wkb;
        this.rAwait = rAwait;
        this.wAwait = wAwait;
        this.svctm = svctm;
        this.util = util;
    }
}
