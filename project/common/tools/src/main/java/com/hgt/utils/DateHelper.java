package com.hgt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * INTRO:时间日期助手类
 * Created by root on 11/2/16.
 * =============================================================================
 * CHANGELOG:
 */
public class DateHelper {

    /**
     * 返回简单时间日期格式，如2016-12-03 09:33:41
     *
     * @return
     */
    public static String getSimpleDate() {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        result = formatter.format(curDate);
        return result;
    }

    /**
     * 返回标准化且完整的日期时间格式，如2016-12-03T09:33:41.073+0800
     *
     * @return
     */
    public static String getFullStandardDate() {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date curDate = new Date(System.currentTimeMillis());
        result = formatter.format(curDate);
        return result;
    }

    /**
     * 日期按小时加减
     *
     * @param strBaseDate
     * @param delta
     * @return
     */
    public static String operateDateByHour(String strBaseDate, int delta) {
        String resultStr = "";
        Date baseDate = new Date();
        Date postDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            baseDate = simpleDateFormat.parse(strBaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        postDate = new Date(baseDate.getTime() + delta * 60 * 60 * 1000);

        resultStr = simpleDateFormat.format(postDate);

        return resultStr;
    }

}
