package com.github.camille.server.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-23 10:17
 **/
@Data
public class AlarmConfigDTO {

    private int id;
    private int hostId;
    private String name;
    private String remark;
    private int dynamic;
    private int emailNotice;
    private int phoneNotice;
    private int noticeUserId;
    private List<Condition> conditions;


}
