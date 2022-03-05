package com.github.camille.server.database.service;

import com.github.camille.server.database.dao.HostDao;
import com.github.camille.server.database.entity.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-21 17:02
 **/
@Service
public class HostService {

    @Autowired
    private HostDao hostDao;

    public List<Host> hostList(String hostName, String hostIp, int offset, int limit) {
        List<Host> hosts = hostDao.selectHosts(hostName, hostIp, offset, limit);
        return hosts;
    }


    public int hostCount(String hostName, String hostIp) {
        int count = hostDao.selectCount(hostName, hostIp);
        return count;
    }

    public Host selectById(int hostId) {
        return hostDao.selectById(hostId);
    }

    public List<Integer> getAliveHostId() {
        return hostDao.selectAliveHostId();
    }


    public void updateStatus(List<Integer> idList, int status) {
        hostDao.updateStatus(idList, status);
    }
}
