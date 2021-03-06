package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.LoginTicketDao;
import com.github.camille.server.database.dao.UserDao;
import com.github.camille.server.database.entity.user.LoginTicket;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.util.MonitorConstant;
import com.github.camille.server.util.MonitorUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public User getByEmail(String email) {
        User user = userDao.selectByEmail(email);
        return user;
    }

    public User getByUsername(String username) {
        User user = userDao.selectByUsername(username);
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

    public void register(String username, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userDao.insertUser(user);
    }

    public List<User> findUserByIds(List<Integer> userIds) {
        if (!CollectionUtils.isEmpty(userIds)) {
            return userDao.selectByIds(userIds);
        } else {
            return new ArrayList<>();
        }
    }

    public List<User> list() {
        return userDao.selectAll();
    }
}
