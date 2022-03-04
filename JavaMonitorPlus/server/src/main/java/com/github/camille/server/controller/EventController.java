package com.github.camille.server.controller;

import com.github.camille.server.database.entity.alarm.AlarmEvent;
import com.github.camille.server.database.service.AlarmEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-04 14:32
 **/
@RestController
@RequestMapping("/event")
public class EventController extends BaseController {

    @Autowired
    private AlarmEventService alarmEventService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String eventList() {
        List<AlarmEvent> list = alarmEventService.list();
        return getResponse(200, "成功", list);
    }






}
