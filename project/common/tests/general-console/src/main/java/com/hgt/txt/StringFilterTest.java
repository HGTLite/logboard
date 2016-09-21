package com.hgt.txt;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFilterTest {

    public static void main(String[] args) {
        testStringFilter();
    }

    // 过滤特殊字符
    public static String StringFilter(String str) {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void testStringFilter() {
        String str = "2016-07-20 16:13:28,897";
        System.out.println(str);
        System.out.println(StringFilter(str));
    }



}