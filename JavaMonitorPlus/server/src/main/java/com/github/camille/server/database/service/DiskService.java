package com.github.camille.server.database.service;

import com.github.camille.server.client.DiskEntityDTO;
import com.github.camille.server.database.dao.DiskDao;
import com.github.camille.server.database.entity.data.DiskEntity;
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

    public List<DiskEntity> findAllByAddress(String address) {
        return diskDao.findAllByAddress(address);
    }

    public void write(String address, Instant date, DiskEntityDTO diskEntity) {
        DiskEntity hardDiskEntity = new DiskEntity();
        hardDiskEntity.setAddress(address);
        hardDiskEntity.setDate(date);
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

    public List<DiskEntity> selectPredictData(String address, int slidingWindowSize) {
        return diskDao.selectLimitByAddress(address, slidingWindowSize);
    }

    public List<Double> selectDataByColumnName(String address, Integer continuePeriod, String columnName) {
        return diskDao.selectByColumn(address, continuePeriod, columnName);
    }
}
