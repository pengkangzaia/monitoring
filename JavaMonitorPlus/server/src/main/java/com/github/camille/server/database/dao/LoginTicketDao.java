package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.user.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 11:31
 **/
@Mapper
public interface LoginTicketDao {

    Integer insertTicket(LoginTicket loginTicket);

    LoginTicket selectByTicket(String ticket);


    void updateStatus(String ticket, int status);
}
