package com.github.camille.server.timer.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.service.HostService;
import com.github.camille.server.remote.util.HttpClient;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-05 15:13
 **/
public class HealthCheckJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${spring.cloud.consul.host}")
    private String consulHost;

    @Value("${spring.cloud.consul.port}")
    private String consulPort;

    @Autowired
    private HostService hostService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        String response = HttpClient.doGet("http://" + consulHost + ":" + consulPort + "/v1/health/state/passing");
        List<Integer> serviceList = new ArrayList<>();
        JSONArray parsed = (JSONArray) JSON.parse(response);
        for (int i = 0; i < parsed.size(); i++) {
            String serviceName = (String) parsed.getJSONObject(i).get("ServiceName");
            if (serviceName.startsWith("client")) {
                serviceList.add(Integer.parseInt(serviceName.split("-")[1]));
            }
        }
        List<Integer> aliveHostsList = hostService.getAliveHostId();
        List<Integer> updateOffline = new ArrayList<>();
        // 此次更新下线主机
        Set<Integer> serverSet = new HashSet<>(serviceList);
        for (Integer aliveId : aliveHostsList) {
            if (!serverSet.contains(aliveId)) {
                updateOffline.add(aliveId);
            }
        }
        hostService.updateStatus(serviceList, 1);
        if (CollectionUtils.isNotEmpty(updateOffline)) {
            hostService.updateStatus(updateOffline, 0);
            // 告警
        }
    }
}
