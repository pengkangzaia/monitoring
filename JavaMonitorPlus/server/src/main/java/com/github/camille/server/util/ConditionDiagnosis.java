package com.github.camille.server.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-29 12:59
 **/
public class ConditionDiagnosis {


    public static boolean diagnose(String operator, Double threshold, List<Double> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            if (">".equals(operator)) {
                boolean isAbnormal = values.get(0) > threshold;
                for (int i = 1; i < values.size(); i++) {
                    isAbnormal = isAbnormal && values.get(i) > threshold;
                }
                return isAbnormal;
            }
            if ("<".equals(operator)) {
                boolean isAbnormal = values.get(0) < threshold;
                for (int i = 1; i < values.size(); i++) {
                    isAbnormal = isAbnormal && values.get(i) < threshold;
                }
                return isAbnormal;
            }
            if (">=".equals(operator)) {
                boolean isAbnormal = values.get(0) >= threshold;
                for (int i = 1; i < values.size(); i++) {
                    isAbnormal = isAbnormal && values.get(i) >= threshold;
                }
                return isAbnormal;
            }
            if ("<=".equals(operator)) {
                boolean isAbnormal = values.get(0) <= threshold;
                for (int i = 1; i < values.size(); i++) {
                    isAbnormal = isAbnormal && values.get(i) <= threshold;
                }
                return isAbnormal;
            }
            return false;
        }
        System.out.println("静态阈值检测：无检测数据...");
        return false;


    }


}
