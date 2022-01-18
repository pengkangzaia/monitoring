package com.github.camille.server.database.service;

import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.database.dao.DiskRepository;
import com.github.camille.server.database.entity.HardDiskEntity;
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
    private DiskRepository diskRepository;

    public List<HardDiskEntity> findAllByAddress(String address) {
        return diskRepository.findAllByAddress(address);
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
        diskRepository.save(hardDiskEntity);
    }

    public void clearAll() {
        diskRepository.deleteAll();
    }

}
