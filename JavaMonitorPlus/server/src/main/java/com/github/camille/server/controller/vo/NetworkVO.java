package com.github.camille.server.controller.vo;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 16:36
 **/
@Data
public class NetworkVO {

    private String address;
    private String date;
    private Double receivePacket;
    private Double sendPacket;
    private Double receive;
    private Integer tcpCount;
    private Double send;


}
