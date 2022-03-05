package com.github.camille.server.timer.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.camille.server.remote.util.HttpClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        String response = HttpClient.doGet("http://" + consulHost + ":" + consulPort + "/v1/health/state/passing");
        Set<String> serviceList = new HashSet<>();
        JSONArray parsed = (JSONArray) JSON.parse(response);
        for (int i = 0; i < parsed.size(); i++) {
            String serviceName = (String) parsed.getJSONObject(i).get("ServiceName");
            if (serviceName.startsWith("client")) {
                serviceList.add(serviceName.split("-")[1]);
            }
        }

    }
}
