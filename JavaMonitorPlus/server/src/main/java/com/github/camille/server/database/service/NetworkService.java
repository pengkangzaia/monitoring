package com.github.camille.server.database.service;

import com.github.camille.server.client.NetEntityDTO;
import com.github.camille.server.database.metric.NetDao;
import com.github.camille.server.database.entity.data.NetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 15:34
 **/
@Service
public class NetworkService {

    @Autowired
    private NetDao netDao;


    public void write(String address, Instant now, NetEntityDTO net) {
        NetEntity netEntity = new NetEntity();
        netEntity.setDate(now);
        netEntity.setAddress(address);
        netEntity.setReceivePacket(net.getReceivePacket());
        netEntity.setSendPacket(net.getSendPacket());
        netEntity.setReceive(net.getReceive());
        netEntity.setSend(net.getSend());
        netEntity.setTcpCount(net.getTcpConnect());
        netDao.save(netEntity);
    }


    public List<NetEntity> findAllByAddress(String address) {
        return netDao.findAllByAddress(address);
    }

    public List<Double> selectDataByColumnName(String address, Integer continuePeriod, String columnName) {
        return netDao.selectByColumn(address, continuePeriod, columnName);
    }
}
