package com.hgt.hbase.keys;

import java.util.Random;

/******************************************************************************
 * Created by  Yao  on  2016/7/20
 * README:rowkey工具类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class RowkeyFactory {

    /**
     * 产生的RowKey定长16位，3位tags，10位日期，3位随机
     *
     * @param strTags 日志类别标签
     * @param strDate 日志产生日期时间
     * @return rowkey，示例GEN0720145017809
     */
    public static String build(String strTags, String strDate) {
        String rowkey = "";
        rowkey = strTags.substring(0, 3);
        strDate = strDate.substring(4);
        strDate = strDate.replace("-", "").replace(" ", "").replace(":", "").replace(",", "");
        rowkey += strDate;

        return rowkey;
    }


    /**
     * 产生一个随机3位数,001-999
     *
     * @return
     */
    public String create3Numbers() {
        String finalNumber;
        Random r = new Random();
        int n = r.nextInt(1000);

        if (n >= 0 && n <= 9) {
            finalNumber = "00" + n;
        } else if (n >= 10 && n <= 99) {
            finalNumber = "0" + n;
        } else {
            finalNumber = Integer.toString(n);
        }

        return finalNumber;
    }


}
