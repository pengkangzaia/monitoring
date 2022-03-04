package com.github.camille.server.controller.vo;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-04 15:52
 **/
@Data
public class LoginParam {

    String username;
    String password;
    Boolean autoLogin;
    String type;
}
