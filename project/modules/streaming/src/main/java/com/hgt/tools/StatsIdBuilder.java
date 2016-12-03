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
     * 生成32位统计ID  4编号+12日期+16uuid
     * @param strTag
     * @param strDate
     * @return
     */
    public static String build(String strTag, String strDate) {

        StringBuilder resultId = new StringBuilder();

        String tag4 = strTag.substring(0,4);
        resultId.append(tag4);

        String date12 = strDate.replace("-", "").replace(" ", "").replace(":", "").replace(",", "").substring(2,14);
        resultId.append(date12);

        String uuid16 = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        resultId.append(uuid16);

        return resultId.toString().toLowerCase();
    }

}
