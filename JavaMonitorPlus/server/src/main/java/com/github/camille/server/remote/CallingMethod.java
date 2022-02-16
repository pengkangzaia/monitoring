package com.github.camille.server.remote;

import com.alibaba.fastjson.JSON;
import com.github.camille.server.client.CpuEntityDTO;
import com.github.camille.server.client.DiskEntityDTO;
import com.github.camille.server.client.MemEntityDTO;
import com.github.camille.server.client.NetEntityDTO;
import com.github.camille.server.remote.util.HttpUtil;

import java.io.IOException;

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


    public static CpuEntityDTO getCpuInfo(String address) throws IOException {
        String url = address + "/cpuLoadInfo";
        String body = connectHost(url);
        return JSON.parseObject(body, CpuEntityDTO.class);
    }

    public static MemEntityDTO getMemoryUsage(String address) throws IOException {
        String url = address + "/memUsage";
        String body = connectHost(url);
        return JSON.parseObject(body, MemEntityDTO.class);
    }

    public static DiskEntityDTO getDiskInfo(String address) throws IOException {
        String url = address + "/disk";
        String body = connectHost(url);
        return JSON.parseObject(body, DiskEntityDTO.class);
    }


    public static NetEntityDTO getNetInfo(String address) throws IOException {
        String url = address + "/network";
        String body = connectHost(url);
        return JSON.parseObject(body, NetEntityDTO.class);
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
