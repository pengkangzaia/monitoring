package com.github.camille.server.controller.vo;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 16:36
 **/
@Data
public class MemoryVO {

    private String address;

    private Double used;

    private Double usedPercent;

    private String date;


}
