package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.LoginTicketDao;
import com.github.camille.server.database.dao.UserDao;
import com.github.camille.server.database.entity.user.LoginTicket;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.util.MonitorConstant;
import com.github.camille.server.util.MonitorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 15:46
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTicketDao loginTicketDao;


    public User get(String email) {
        User user = userDao.selectByEmail(email);
        return user;
    }

    /**
     * 创建并持久化ticket
     *
     * @param user
     * @return
     */
    public LoginTicket saveLoginTicket(User user) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(user.getId());
        ticket.setStatus(0);
        ticket.setTicket(MonitorUtil.generateUUID());
        ticket.setExpired(new Date(System.currentTimeMillis() + MonitorConstant.DEFAULT_EXPIRED_SECONDS * 1000));
        loginTicketDao.insertTicket(ticket);
        return ticket;
    }


    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketDao.selectByTicket(ticket);
    }

    public User findUserById(int userId) {
        return userDao.selectById(userId);
    }

    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket, 1);
    }
}
