package com.github.camille.server.controller;

import com.alibaba.fastjson.JSON;
import com.github.camille.server.controller.dto.UserDTO;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-23 15:40
 **/
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String userList() {
        List<User> userList = userService.list();
        return getResponse(0, "获取用户列表成功", userList);
    }

    @ResponseBody
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public String currentUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("彭康");
        userDTO.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        userDTO.setUnreadCount(12);
        userDTO.setAccess("admin");
        Map<Object, Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("data", userDTO);
        return JSON.toJSONString(map);
    }


}
