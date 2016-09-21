package com.hgt.txt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 * Created by  Yao  on  2016/7/15
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTRegularExpression {
    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT = "cat1 cat cat cat cattie cat";

    public static void main(String args[]) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number is " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }
}
