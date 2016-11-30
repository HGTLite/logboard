package com.hgt.tools;

import java.util.UUID;

/**
 * README:
 * Created by Yao on 11/30/16.
 * =============================================================================
 * CHANGELOG:
 */
public class StatsIdBuilder {

    /**
     * 生成16位统计ID  4编号+12日期
     * @param strTag
     * @param strDate
     * @return
     */
    public static String build(String strTag, String strDate) {

        StringBuilder resultId = new StringBuilder();

        String tag4 = strTag.substring(0,4);
        resultId.append(tag4);

        String date12 = strDate.replace("-", "").replace(" ", "").replace(":", "").replace(",", "").substring(2);
        resultId.append(date12);

        return resultId.toString().toLowerCase();
    }

}
