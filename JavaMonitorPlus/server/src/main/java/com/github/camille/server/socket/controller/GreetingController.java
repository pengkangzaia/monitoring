package com.github.camille.server.socket.controller;


import com.github.camille.server.database.entity.data.*;
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
    private CPUService cpuService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private DiskService diskService;
    @Autowired
    private NetworkService networkService;

    @MessageMapping("/cpu")
    @SendTo("/topic/cpu")
    public List<CPUEntity> socketCpu(Message message) {
        return cpuService.findAllByAddress(message.getAddress());
    }

    @MessageMapping("/memory")
    @SendTo("/topic/memory")
    public List<MemEntity> socketMemory(Message message) {
        return memoryService.findAllByAddress(message.getAddress());
    }

    @MessageMapping("/disk")
    @SendTo("/topic/disk")
    public List<DiskEntity> socketDisk(Message message) {
        return diskService.findAllByAddress(message.getAddress());
    }

    @MessageMapping("/network")
    @SendTo("/topic/network")
    public List<NetEntity> socketNetwork(Message message) {
        return networkService.findAllByAddress(message.getAddress());
    }
}
