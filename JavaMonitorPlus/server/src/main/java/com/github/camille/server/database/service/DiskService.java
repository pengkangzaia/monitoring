package com.github.camille.server.database.service;

import com.github.camille.server.client.DiskEntity;
import com.github.camille.server.database.dao.DiskDao;
import com.github.camille.server.database.entity.data.HardDiskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 13:38
 **/
@Service
public class DiskService {

    @Autowired
    private DiskDao diskDao;

    public List<HardDiskEntity> findAllByAddress(String address) {
        return diskDao.findAllByAddress(address);
    }

    public void write(String address, String data, DiskEntity diskEntity) {
        HardDiskEntity hardDiskEntity = new HardDiskEntity();
        hardDiskEntity.setAddress(address);
        hardDiskEntity.setDate(Instant.now());
        hardDiskEntity.setRio(Double.valueOf(diskEntity.getRio()));
        hardDiskEntity.setWio(Double.valueOf(diskEntity.getWio()));
        hardDiskEntity.setRkb(Double.valueOf(diskEntity.getRkb()));
        hardDiskEntity.setWkb(Double.valueOf(diskEntity.getWkb()));
        hardDiskEntity.setRAwait(Double.valueOf(diskEntity.getRAwait()));
        hardDiskEntity.setWAwait(Double.valueOf(diskEntity.getWAwait()));
        hardDiskEntity.setSvctm(Double.valueOf(diskEntity.getSvctm()));
        hardDiskEntity.setUtil(Double.valueOf(diskEntity.getUtil()));
        diskDao.save(hardDiskEntity);
    }

    public void clearAll() {
        diskDao.deleteAll();
    }

    public List<HardDiskEntity> selectPredictData(String address, int slidingWindowSize) {
        return diskDao.selectLimitByAddress(address, slidingWindowSize);
    }

    public List<Double> selectDataByColumnName(String address, Integer continuePeriod, String columnName) {
        return diskDao.selectByColumn(address, continuePeriod, columnName);
    }
}
