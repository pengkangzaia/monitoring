package com.github.camille.server.client;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-15 20:06
 **/
@Data
public class NetEntityDTO {

    /**
     * 接收数据包
     */
    private Double receivePacket;

    /**
     * 发送数据包
     */
    private Double sendPacket;

    /**
     * 接收带宽(kb/s)
     */
    private Double receive;

    /**
     * 发送带宽(kb/s)
     */
    private Double send;

    /**
     * TCP连接个数
     */
    private Integer tcpConnect;

    public NetEntityDTO(Double receivePacket, Double sendPacket, Double receive, Double send, Integer tcpConnect) {
        this.receivePacket = receivePacket;
        this.sendPacket = sendPacket;
        this.receive = receive;
        this.send = send;
        this.tcpConnect = tcpConnect;
    }
}
