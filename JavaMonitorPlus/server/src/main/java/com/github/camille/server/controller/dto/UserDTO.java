package com.github.camille.server.controller.dto;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-17 10:47
 **/
@Data
public class UserDTO {

    private String name;
    private String avatar;
    private Integer unreadCount;
    private String access;


}
