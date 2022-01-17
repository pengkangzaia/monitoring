package com.github.onblog.client.core.order;

import com.github.onblog.client.core.cmd.ExecuteCmd;
import com.github.onblog.client.core.entity.DiskEntity;
import com.github.onblog.client.core.entity.MemoryEntity;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-16 12:53
 **/
public class DiskInfo {

    public static DiskEntity usage() {
        // rio,wio,rkb,wkb,
        String rio = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $2}' | awk '{sum += $1};END {print sum}'"});
        rio = replaceLineBreak(rio);
        String wio = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $3}' | awk '{sum += $1};END {print sum}'"});
        wio = replaceLineBreak(wio);
        String rkb = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $4}' | awk '{sum += $1};END {print sum}'"});
        rkb = replaceLineBreak(rkb);
        String wkb = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $5}' | awk '{sum += $1};END {print sum}'"});
        wkb = replaceLineBreak(wkb);
        // r_await, w_await, svctm, util
        String rAwiat = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $10}' | awk '{sum += $1};END {print sum/(NR-1)}'"});
        rAwiat = replaceLineBreak(rAwiat);
        String wAwiat = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $11}' | awk '{sum += $1};END {print sum/(NR-1)}'"});
        wAwiat = replaceLineBreak(wAwiat);
        String svctm = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $15}' | awk '{sum += $1};END {print sum/(NR-1)}'"});
        svctm = replaceLineBreak(svctm);
        String util = ExecuteCmd.execute(new String[]{"sh","-c","iostat -d -x | awk 'NR > 3 {print $16}' | awk '{sum += $1};END {print sum/(NR-1)}'"});
        util = replaceLineBreak(util);
        return new DiskEntity(rio, wio, rkb, wkb, rAwiat, wAwiat, svctm, util);
    }

    public static String replaceLineBreak(String origin) {
        if (origin != null && origin.length() > 0) {
            origin = origin.replaceAll("\n", "");
        }
        return origin;
    }



}
