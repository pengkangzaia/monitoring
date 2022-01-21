package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.Host;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-21 16:57
 **/
@Mapper
public interface HostDao {

    List<Host> selectHosts(int offset, int limit);


    int selectCount();

    void insert(Host host);
}
