package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.UserDao;
import com.github.camille.server.database.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 15:46
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean verify(String email, String password) {
        User user = userDao.selectByEmail(email);
        return user != null && user.getPassword().equals(password);
    }


    public boolean exist(String email) {
        User user = userDao.selectByEmail(email);
        return user != null;
    }
}
