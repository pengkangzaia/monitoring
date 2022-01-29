package com.github.camille.server.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-29 12:59
 **/
public class ConditionDiagnotor {


    public static boolean diagnose(String operator, Double threshold, List<Double> values) {
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



}
