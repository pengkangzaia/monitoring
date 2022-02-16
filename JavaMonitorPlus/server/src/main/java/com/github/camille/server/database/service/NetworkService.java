package com.github.camille.server.database.service;

import com.github.camille.server.client.NetEntity;
import com.github.camille.server.database.dao.NetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 15:34
 **/
@Service
public class NetworkService {

    @Autowired
    private NetDao netDao;


    public void write(String address, Instant now, NetEntity net) {
        com.github.camille.server.database.entity.data.NetEntity netEntity = new com.github.camille.server.database.entity.data.NetEntity();
        netEntity.setDate(now);
        netEntity.setAddress(address);
        netEntity.setReceivePacket(net.getReceivePacket());
        netEntity.setSendPacket(net.getSendPacket());
        netEntity.setReceive(net.getReceive());
        netEntity.setSend(net.getSend());
        netEntity.setTcpCount(net.getTcpConnect());
        netDao.save(netEntity);
    }
}
