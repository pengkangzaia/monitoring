package com.github.camille.server.socket.controller;

import com.github.camille.server.controller.vo.CpuVO;
import com.github.camille.server.controller.vo.DiskVO;
import com.github.camille.server.controller.vo.MemoryVO;
import com.github.camille.server.controller.vo.NetworkVO;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.DiskEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.data.NetEntity;
import com.github.camille.server.database.service.*;
import com.github.camille.server.timer.util.TimerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-06 14:23
 **/
@RestController
public class WebController {


    @Autowired
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private HostService hostService;


    @GetMapping("/cpu")
    public List<CpuVO> webCpu(int id) {
        Host host = hostService.selectById(id);
        String address = "http://" + host.getIp() + ":" + host.getAgentPort();
        List<CPUEntity> entities = cpuService.findAllByAddress(address);
        List<CpuVO> res = new ArrayList<>();
        for (CPUEntity cpu : entities) {
            CpuVO vo = new CpuVO();
            BeanUtils.copyProperties(cpu, vo);
            vo.setDate(TimerUtil.InstantToDate(cpu.getDate()));
            res.add(vo);
        }
        return res;
    }



    @GetMapping("/memory")
    public List<MemoryVO> socketMemory(int id) {
        Host host = hostService.selectById(id);
        String address = "http://" + host.getIp() + ":" + host.getAgentPort();
        List<MemEntity> entities = memoryService.findAllByAddress(address);
        List<MemoryVO> res = new ArrayList<>();
        for (MemEntity mem : entities) {
            MemoryVO vo = new MemoryVO();
            BeanUtils.copyProperties(mem, vo);
            vo.setDate(TimerUtil.InstantToDate(mem.getDate()));
            res.add(vo);
        }
        return res;
    }

    @GetMapping("/disk")
    public List<DiskVO> socketDisk(int id) {
        Host host = hostService.selectById(id);
        String address = "http://" + host.getIp() + ":" + host.getAgentPort();
        List<DiskEntity> diskEntities = diskService.findAllByAddress(address);
        List<DiskVO> res = new ArrayList<>();
        for (DiskEntity diskEntity : diskEntities) {
            DiskVO vo = new DiskVO();
            BeanUtils.copyProperties(diskEntity, vo);
            vo.setDate(TimerUtil.InstantToDate(diskEntity.getDate()));
            res.add(vo);
        }
        return res;
    }

    @GetMapping("/net")
    public List<NetworkVO> socketNetwork(int id) {
        Host host = hostService.selectById(id);
        String address = "http://" + host.getIp() + ":" + host.getAgentPort();
        List<NetEntity> netEntityList = networkService.findAllByAddress(address);
        List<NetworkVO> res = new ArrayList<>();
        for (NetEntity netEntity : netEntityList) {
            NetworkVO vo = new NetworkVO();
            BeanUtils.copyProperties(netEntity, vo);
            vo.setDate(TimerUtil.InstantToDate(netEntity.getDate()));
            res.add(vo);
        }
        return res;
    }

}
