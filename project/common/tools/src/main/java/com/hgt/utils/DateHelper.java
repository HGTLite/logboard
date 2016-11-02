package com.hgt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * INTRO:时间日期助手类
 * Created by root on 11/2/16.
 * =============================================================================
 * CHANGELOG:
 */
public class DateHelper {


    public static String getSimpleDate(){
        String result="";
        SimpleDateFormat formatter   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate   =   new   Date(System.currentTimeMillis());
        result = formatter.format(curDate);
        return result;
    }

}
