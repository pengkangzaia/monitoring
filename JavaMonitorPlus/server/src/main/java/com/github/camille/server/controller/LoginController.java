package com.github.camille.server.controller;

import com.github.camille.server.database.entity.user.LoginTicket;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.UserService;
import com.github.camille.server.util.MonitorConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
        return "user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpServletResponse response) {
        User user = userService.get(email);
        if (user == null) {
            // 用户不存在，需要注册
            return getResponse(1001, "用户不存在，请您先注册");
        }
        if (user.getPassword().equals(password)) {
            // 账号密码正确，登录成功
            // 创建ticket
            LoginTicket ticket = userService.saveLoginTicket(user);
            Cookie cookie = new Cookie("ticket", ticket.getTicket());
            // cookie.setPath(contextPath);
            cookie.setMaxAge(MonitorConstant.DEFAULT_EXPIRED_SECONDS);
            response.addCookie(cookie);
            return getResponse(0, "登录成功");
        } else {
            // 密码错误，登录失败
            return getResponse(1001, "密码错误，请重试");
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        // 重定向
        return "redirect:/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "user/register";
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(String username, String email, String password) {
        User user = userService.get(email);
        if (user != null) {
            return getResponse(1001, "该邮箱已被注册");
        }
        userService.register(username, email, password);
        return getResponse(0, "注册成功");
    }




}
