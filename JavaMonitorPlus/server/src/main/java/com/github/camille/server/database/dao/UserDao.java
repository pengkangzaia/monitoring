package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 11:16
 **/
@Mapper
public interface UserDao {

    User selectByEmail(String email);

    Integer insertUser(User user);


    User selectById(int userId);
}
