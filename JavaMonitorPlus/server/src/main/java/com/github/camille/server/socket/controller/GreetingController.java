package com.github.camille.server.socket.controller;


import com.github.camille.server.controller.vo.CpuVO;
import com.github.camille.server.controller.vo.DiskVO;
import com.github.camille.server.controller.vo.MemoryVO;
import com.github.camille.server.controller.vo.NetworkVO;
import com.github.camille.server.database.entity.data.*;
import com.github.camille.server.database.service.*;
import com.github.camille.server.timer.util.TimerUtil;
import com.github.camille.server.view.entity.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by yster@foxmail.com 2018/6/19/019 23:49
 */
@Controller
public class GreetingController {

    @Autowired
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private NetworkService networkService;

    @MessageMapping("/cpu")
    @SendTo("/topic/cpu")
    public List<CpuVO> socketCpu(Message message) {
        List<CPUEntity> entities = cpuService.findAllByAddress(message.getAddress());
        List<CpuVO> res = new ArrayList<>();
        for (CPUEntity cpu : entities) {
            CpuVO vo = new CpuVO();
            BeanUtils.copyProperties(cpu, vo);
            vo.setDate(TimerUtil.InstantToDate(cpu.getDate()));
            res.add(vo);
        }
        return res;
    }

    @MessageMapping("/memory")
    @SendTo("/topic/memory")
    public List<MemoryVO> socketMemory(Message message) {
        List<MemEntity> entities = memoryService.findAllByAddress(message.getAddress());
        List<MemoryVO> res = new ArrayList<>();
        for (MemEntity mem : entities) {
            MemoryVO vo = new MemoryVO();
            BeanUtils.copyProperties(mem, vo);
            vo.setDate(TimerUtil.InstantToDate(mem.getDate()));
            res.add(vo);
        }
        return res;
    }

    @MessageMapping("/disk")
    @SendTo("/topic/disk")
    public List<DiskVO> socketDisk(Message message) {
        List<DiskEntity> diskEntities = diskService.findAllByAddress(message.getAddress());
        List<DiskVO> res = new ArrayList<>();
        for (DiskEntity diskEntity : diskEntities) {
            DiskVO vo = new DiskVO();
            BeanUtils.copyProperties(diskEntity, vo);
            vo.setDate(TimerUtil.InstantToDate(diskEntity.getDate()));
            res.add(vo);
        }
        return res;
    }

    @MessageMapping("/network")
    @SendTo("/topic/network")
    public List<NetworkVO> socketNetwork(Message message) {
        List<NetEntity> netEntityList = networkService.findAllByAddress(message.getAddress());
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
