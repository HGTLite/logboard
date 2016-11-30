package com.hgt.tools;

import java.util.UUID;

/**
 * README: 生成日志Id
 * Created by Yao on 11/30/16.
 * =============================================================================
 * CHANGELOG:
 */
public class LogIdBuilder {

    /**
     * 生成32位的日志ID  6编号+4类型+14日期+8uuid子集
     *
     * @param strAppCode
     * @param strType
     * @param strDate
     * @return
     */
    public static String build(String strAppCode, String strType, String strDate) {

        StringBuilder resultId = new StringBuilder();

        String appCode6 = strAppCode.substring(0, 6);
        resultId.append(appCode6);

        String type4 = strType.substring(0, 4);
        resultId.append(type4);

        String date14 = strDate.substring(0, 19).replace("-", "").replace(" ", "").replace(":", "").replace(",", "");
        resultId.append(date14);

        String uuid8 = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        resultId.append(uuid8);

        return resultId.toString();
    }

}
