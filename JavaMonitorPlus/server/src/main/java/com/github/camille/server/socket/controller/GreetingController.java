package com.github.camille.server.socket.controller;


import com.github.camille.server.database.entity.*;
import com.github.camille.server.database.service.*;
import com.github.camille.server.view.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/6/19/019 23:49
 */
@Controller
public class GreetingController {

    @Autowired
    private GcService gcService;
    @Autowired
    private ClassService classService;
    @Autowired
    private ThreadService threadService;
    @Autowired
    private CpuService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;

    @MessageMapping("/gc")
    @SendTo("/topic/gc")
    public List<GcEntity> socketGc(Message message) throws Exception {
        return gcService.findAllByAddressAndName(message.getAddress(),message.getPid());
    }

    @MessageMapping("/class")
    @SendTo("/topic/class")
    public List<ClassLoadEntity> socketClass(Message message) throws Exception {
        return classService.findAllByAddressAndName(message.getAddress(),message.getPid());
    }

    @MessageMapping("/thread")
    @SendTo("/topic/thread")
    public List<ThreadEntity> socketThread(Message message) throws Exception {
        return threadService.findAllByAddressAndName(message.getAddress(),message.getPid());
    }

    @MessageMapping("/cpu")
    @SendTo("/topic/cpu")
    public List<CpuEntity> socketCpu(Message message) {
        return cpuService.findAllByAddress(message.getAddress());
    }

    @MessageMapping("/memory")
    @SendTo("/topic/memory")
    public List<MemEntity> socketMemory(Message message) {
        List<MemEntity> res = memoryService.findAllByAddress(message.getAddress());
        return res;
    }

    @MessageMapping("/disk")
    @SendTo("/topic/disk")
    public List<HardDiskEntity> socketDisk(Message message) {
        List<HardDiskEntity> res = diskService.findAllByAddress(message.getAddress());
        return res;
    }

}
