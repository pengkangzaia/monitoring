package com.github.onblog.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientApplication {

    /**
     * 监控参数
     * 内存使用量
     * 磁盘监控
     *  读流量，写流量，磁盘IO等待，磁盘利用率
     * 网络监控
     *  TCP连接个数，子机utc时间和ntp时间差值
     */


    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

