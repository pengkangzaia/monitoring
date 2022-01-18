package com.github.camille.server.database.entity.statistic;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 17:55
 **/
@Data
public class MinMaxDiskMetric {

    private String minRio;
    private String maxRio;
    private String minWio;
    private String maxWio;
    private String minRkb;
    private String maxRkb;
    private String minWkb;
    private String maxWkb;
    private String minRAwait;
    private String maxRAwait;
    private String minWAwait;
    private String maxWAwait;
    private String minSvctm;
    private String maxSvctm;
    private String minUtil;
    private String maxUtil;


}
