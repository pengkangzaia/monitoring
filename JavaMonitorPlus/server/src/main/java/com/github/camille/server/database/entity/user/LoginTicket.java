package com.github.camille.server.database.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 11:29
 **/
@Data
public class LoginTicket {

    private int id;
    private int user_id;
    private String ticket;
    private int status;
    private Date expired;

}
