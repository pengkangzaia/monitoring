package com.github.camille.server.database.entity.alarm;

import lombok.Data;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 11:39
 **/
@Data
public class AlarmConfig {

    private int id;
    private int hostId;
    private String name;
    private String remark;
    private boolean dynamic;
    private boolean emailNotice;
    private boolean phoneNotice;
    // 用户组ID，如果为0则不采用用户组
    private int groupId;
    private int isDeleted;
    private Date createTime;
    private Date modifyTime;



}
