package com.github.camille.server;

import com.github.camille.server.database.dao.ThresholdRepository;
import com.github.camille.server.database.entity.Threshold;
import com.github.camille.server.database.entity.statistic.MinMaxMetric;
import com.github.camille.server.database.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {


    @Autowired
    private ThresholdRepository thresholdRepository;
    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void thresholdTest() {
        Threshold threshold = thresholdRepository.getThresholdByAddress("http://101.35.159.221:8081");
        System.out.println(threshold);
    }

    @Test
    public void statisticsTest() {
        MinMaxMetric res = statisticsService.getMinMaxMetric("http://1.15.117.64:8081");
        System.out.println(res);
    }





}

