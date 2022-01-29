package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.database.dao.DiskDao;
import com.github.camille.server.database.entity.data.HardDiskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        hardDiskEntity.setDate(data);
        hardDiskEntity.setRio(diskEntity.getRio());
        hardDiskEntity.setWio(diskEntity.getWio());
        hardDiskEntity.setRkb(diskEntity.getRkb());
        hardDiskEntity.setWkb(diskEntity.getWkb());
        hardDiskEntity.setRAwait(diskEntity.getRAwait());
        hardDiskEntity.setWAwait(diskEntity.getWAwait());
        hardDiskEntity.setSvctm(diskEntity.getSvctm());
        hardDiskEntity.setUtil(diskEntity.getUtil());
        diskDao.save(hardDiskEntity);
    }

    public void clearAll() {
        diskDao.deleteAll();
    }

    public List<DiskEntity> selectPredictData(String address, int slidingWindowSize) {
        return diskDao.selectLimitByAddress(address, slidingWindowSize);
    }

    public List<String> selectDataByColumnName(String address, Integer continuePeriod, String columnName) {
        return diskDao.selectByColumn(address, continuePeriod, columnName);
    }
}
