package com.hgt.logger.validator;

/******************************************************************************
 * Created by  Yao  on  2016/9/26
 * README: 验证AppCode信息
 *         暂时只验证AppCode长度，作为示例
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public  class AppInfoValidator {

    /**
     * 验证应用代码
     * @param strCode
     * @return
     */
    public static boolean isAppCodeValid(String strCode) {

        boolean ret = false;
        if (strCode.length() == 8) {
            ret = true;
        }

        //TO-DO:验证APP代码是否域数据库的相匹配

        return ret;
    }

}
