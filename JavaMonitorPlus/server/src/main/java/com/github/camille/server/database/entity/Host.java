package com.github.camille.server.database.entity;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-21 16:59
 **/
@Data
public class Host {

    private int id;
    private String name;
    private String ip;
    private String desc;


}
