package com.github.camille.server.database.entity.statistic;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 17:54
 **/
@Data
public class MinMaxMemMetric {

    private String minUsed;
    private String maxUsed;
    private String minUsedPercent;
    private String maxUsedPercent;


}
