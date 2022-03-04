package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 11:16
 **/
@Mapper
public interface UserDao {

    User selectByEmail(String email);

    User selectByUsername(String username);

    Integer insertUser(User user);


    User selectById(int userId);

    List<User> selectByIds(@Param("userIds") List<Integer> userIds);

    List<User> selectAll();
}
