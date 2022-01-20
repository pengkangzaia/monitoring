package com.github.camille.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 12:03
 **/
@Controller
public class LoginController {

    @RequestMapping(value = "/mylogin", method = RequestMethod.GET)
    public String login() {
        return "login";
    }



}
