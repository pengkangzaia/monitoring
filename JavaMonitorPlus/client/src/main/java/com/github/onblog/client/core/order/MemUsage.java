package com.github.onblog.client.core.order;

import com.github.onblog.client.core.cmd.ExecuteCmd;
import com.github.onblog.client.core.entity.MemoryEntity;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-10-31 15:32
 **/
public class MemUsage {

    public static MemoryEntity usage() {
        String memUsedPercent = ExecuteCmd.execute(new String[]{"sh","-c","free -m | grep Mem | awk '{print $3 / $2}'"});
        if (memUsedPercent != null) {
            memUsedPercent = memUsedPercent.replaceAll("\n", "");
        }
        String memUsed = ExecuteCmd.execute(new String[]{"sh","-c","free -m | grep Mem | awk '{print $3}'"});
        if (memUsed != null) {
            memUsed = memUsed.replaceAll("\n", "");
        }
        return new MemoryEntity(memUsed, memUsedPercent);
    }

    public static void main(String[] args) {
        MemoryEntity usage = usage();
        System.out.println(usage);
    }


}
