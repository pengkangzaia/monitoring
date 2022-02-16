package com.github.camille.server.controller.vo;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 16:36
 **/
@Data
public class DiskVO {

    private String address;
    private Double rio;
    private Double wio;
    private Double rkb;
    private Double wkb;
    private Double rAwait;
    private Double wAwait;
    private Double svctm;
    private Double util;
    private String date;


}
