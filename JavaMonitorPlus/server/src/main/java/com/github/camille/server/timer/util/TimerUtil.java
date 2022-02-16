package com.github.camille.server.timer.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Create by yster@foxmail.com 2018/11/14 0014 21:02
 */
public class TimerUtil {
    /**
     * 现在时间
     * @return
     */
    public static String now(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }



    /**
     * Instant转String
     * @return
     */
    public static String InstantToDate(Instant instant){
        Date date = Date.from(instant);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
