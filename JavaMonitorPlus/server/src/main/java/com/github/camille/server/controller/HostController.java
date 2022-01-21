package com.github.camille.server.controller;

import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.service.HostService;
import com.github.camille.server.util.MonitorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-21 16:44
 **/
@Controller
public class HostController extends BaseController {

    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/host", method = RequestMethod.GET)
    public String getHostPage() {
        return "host/host";
    }

    @ResponseBody
    @RequestMapping(value = "/host/list", method = RequestMethod.GET)
    public String getHostList(int page, int limit, String hostName, String hostIp) {
        int offset = (page - 1) * limit;
        List<Host> hosts = hostService.hostList(hostName, hostIp, offset, limit);
        int count = hostService.hostCount(hostName, hostIp);
        return getResponse(0, "成功", count, hosts);
    }





}
