package com.github.camille.server.database.entity.user;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 11:15
 **/
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String email;


}
