package com.github.camille.server.controller.dto;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-23 10:17
 **/
@Data
public class AlarmConfigDTO {

    private String name;
    private String remark;
    private int dynamic;
    private int emailNotice;
    private int phoneNotice;
    private int noticeUserId;


}
