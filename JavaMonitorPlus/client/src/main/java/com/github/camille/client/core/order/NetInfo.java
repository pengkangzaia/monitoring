package com.github.camille.client.core.order;

import com.github.camille.client.core.cmd.ExecuteCmd;
import com.github.camille.client.core.entity.NetEntityDTO;

import java.util.ArrayList;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-15 20:10
 **/
public class NetInfo {

    public static NetEntityDTO info() {
        String traffic = ExecuteCmd.execute(new String[]{"sh", "-c", "sar -n DEV 1 1 | grep eth0 | awk 'NR==1 {for(i=4;i<=7;i++) {print $i}}'"});
        String[] trafficInfo = traffic.split("\n");
        ArrayList<Double> values = new ArrayList<>();
        for (String s : trafficInfo) {
            values.add(Double.valueOf(s));
        }
        String tcpCount = ExecuteCmd.execute(new String[]{"sh", "-c", "netstat -n | grep tcp | wc -l"});
        tcpCount = tcpCount.replace("\n", "");
        return new NetEntityDTO(values.get(0), values.get(1), values.get(2), values.get(3), Integer.parseInt(tcpCount));
    }

}
