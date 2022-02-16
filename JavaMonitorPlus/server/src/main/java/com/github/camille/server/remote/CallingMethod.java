package com.github.camille.server.remote;

import com.alibaba.fastjson.JSON;
import com.github.camille.server.client.CpuEntity;
import com.github.camille.server.client.DiskEntity;
import com.github.camille.server.client.MemEntity;
import com.github.camille.server.client.NetEntity;
import com.github.camille.server.remote.util.HttpUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create by yster@foxmail.com 2018/12/30 0030 19:45
 */
public class CallingMethod {

    public static String getSystem(String address) throws IOException {
        String url = address + "/system";
        return connectHost(url);
    }

    public static String getVersion(String address) throws IOException {
        String url = address + "/version";
        return connectHost(url);
    }


    public static CpuEntity getCpuInfo(String address) throws IOException {
        String url = address + "/cpuLoadInfo";
        String body = connectHost(url);
        return JSON.parseObject(body, CpuEntity.class);
    }

    public static MemEntity getMemoryUsage(String address) throws IOException {
        String url = address + "/memUsage";
        String body = connectHost(url);
        return JSON.parseObject(body, MemEntity.class);
    }

    public static DiskEntity getDiskInfo(String address) throws IOException {
        String url = address + "/disk";
        String body = connectHost(url);
        return JSON.parseObject(body, DiskEntity.class);
    }


    public static NetEntity getNetInfo(String address) throws IOException {
        String url = address + "/network";
        String body = connectHost(url);
        return JSON.parseObject(body, NetEntity.class);
    }


    /**
     * 统一异常处理
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static String connectHost(String url) throws IOException {
        try {
            return HttpUtil.connect(url).execute().getBody();
        } catch (IOException e) {
            throw new IOException("连接主机异常：" + url, e);
        }
    }

}
