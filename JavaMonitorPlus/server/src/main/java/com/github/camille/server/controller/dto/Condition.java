package com.github.camille.server.controller.dto;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-24 20:58
 **/
@Data
public class Condition {

    private Integer id;
    private Integer configId;
    private String metric;
    private String operator;
    private Double value;
    private Integer continuePeriod;
    private Integer noticeFrequency;



}
