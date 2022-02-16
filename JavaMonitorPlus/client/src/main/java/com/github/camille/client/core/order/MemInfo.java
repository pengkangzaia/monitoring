package com.github.camille.client.core.order;

import com.github.camille.client.core.entity.MemEntity;
import com.github.camille.client.core.cmd.ExecuteCmd;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-10-31 15:32
 **/
public class MemInfo {

    public static MemEntity usage() {
        String memUsedPercent = ExecuteCmd.execute(new String[]{"sh","-c","free -m | grep Mem | awk '{print $3 / $2}'"});
        if (memUsedPercent != null) {
            memUsedPercent = memUsedPercent.replaceAll("\n", "");
        }
        String memUsed = ExecuteCmd.execute(new String[]{"sh","-c","free -m | grep Mem | awk '{print $3}'"});
        if (memUsed != null) {
            memUsed = memUsed.replaceAll("\n", "");
        }
        return new MemEntity(memUsed, memUsedPercent);
    }

    public static void main(String[] args) {
        MemEntity usage = usage();
        System.out.println(usage);
    }


}
