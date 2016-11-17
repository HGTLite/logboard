package com.hgt.common.utils;

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 随机生成字符串
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static boolean isMobile(String mobile) {
        String pMobile = "^(1(([34578][0-9])))\\d{8}$";
        return Pattern.matches(pMobile, mobile);
    }




}
