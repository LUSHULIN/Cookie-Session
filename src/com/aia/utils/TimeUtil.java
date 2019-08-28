package com.aia.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2019/8/28.
 */
public class TimeUtil {
    public static  String getTimeFommat(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(date);
    }
}
