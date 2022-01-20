package com.github.camille.server.controller;

import com.github.camille.server.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 12:03
 **/
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        boolean exist = userService.exist(username);
        if (!exist) {
            // 用户不存在，需要注册
            return getResponse(1001, "用户不存在，请您先注册");
        }
        boolean verify = userService.verify(username, password);
        if (verify) {
            // 账号密码正确，登录成功
            return getResponse(0, "登录成功");
        } else {
            // 密码错误，登录失败
            return getResponse(1001, "密码错误，请重试");
        }

    }




}
