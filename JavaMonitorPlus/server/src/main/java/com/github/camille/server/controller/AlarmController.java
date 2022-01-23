package com.github.camille.server.controller;

import com.github.camille.server.controller.dto.AlarmConfigDTO;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.user.User;
import com.github.camille.server.database.service.AlarmConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-22 12:51
 **/
@Controller
@RequestMapping("/alarm")
public class AlarmController extends BaseController {

    @Autowired
    private AlarmConfigService alarmConfigService;


    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String alarmConfigPage(int hostId, Model model) {
        AlarmConfig config = alarmConfigService.getConfigByHostId(hostId);
        model.addAttribute("hostId", hostId);
        if (config == null) {
            return "alarm/add";
        } else {
            List<User> noticeUsers = alarmConfigService.getNoticeUser(config.getId());
            model.addAttribute("noticeUser", noticeUsers.get(0));
            model.addAttribute("config", config);
            return "alarm/detail";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/config/add", method = RequestMethod.POST)
    public String insertConfig(@RequestBody AlarmConfigDTO alarmConfigDTO) {
        AlarmConfig config = new AlarmConfig();
        config.setHostId(alarmConfigDTO.getHostId());
        config.setName(alarmConfigDTO.getName());
        config.setRemark(alarmConfigDTO.getRemark());
        config.setDynamic(Objects.equals(alarmConfigDTO.getDynamic(), 1));
        config.setEmailNotice(Objects.equals(alarmConfigDTO.getEmailNotice(), 1));
        config.setPhoneNotice(Objects.equals(alarmConfigDTO.getPhoneNotice(), 1));
        alarmConfigService.saveConfig(config);
        int noticeUserId = alarmConfigDTO.getNoticeUserId();
        alarmConfigService.saveNoticeUser(config.getId(), noticeUserId);
        return getResponse(0, "添加成功");
    }


    @ResponseBody
    @RequestMapping(value = "/config/edit", method = RequestMethod.POST)
    public String editConfig(@RequestBody AlarmConfigDTO alarmConfigDTO) {
        AlarmConfig config = alarmConfigService.getConfigByHostId(alarmConfigDTO.getHostId());
        config.setName(alarmConfigDTO.getName());
        config.setRemark(alarmConfigDTO.getRemark());
        config.setDynamic(Objects.equals(alarmConfigDTO.getDynamic(), 1));
        config.setEmailNotice(Objects.equals(alarmConfigDTO.getEmailNotice(), 1));
        config.setPhoneNotice(Objects.equals(alarmConfigDTO.getPhoneNotice(), 1));
        alarmConfigService.editConfig(config);
        int noticeUserId = alarmConfigDTO.getNoticeUserId();
        alarmConfigService.updateNoticeUser(config.getId(), noticeUserId);
        return getResponse(0, "修改成功");
    }






}
