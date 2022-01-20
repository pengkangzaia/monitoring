package com.github.camille.server.util;

import java.util.UUID;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 16:45
 **/
public class MonitorUtil {

    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
