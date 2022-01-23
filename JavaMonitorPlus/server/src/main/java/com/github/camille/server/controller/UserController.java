package com.github.camille.server.controller;

import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-23 15:40
 **/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList() {
        List<User> userList = userService.list();
        return getResponse(0, "获取用户列表成功", userList);
    }


}
