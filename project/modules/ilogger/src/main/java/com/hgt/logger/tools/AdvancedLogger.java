package com.hgt.logger.tools;


import com.hgt.logger.formats.LogKeyInfo;
import com.hgt.logger.formats.LogOptions;
import com.hgt.utils.JsonHelper;
import org.slf4j.Logger;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/******************************************************************************
 * Created by  Yao  on  2016/9/27
 * README: 高级日志打印工具类
 *         支持设置各种字段
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class AdvancedLogger extends BasicLogger {

    //日志可选内容
    private EnumMap<LogOptions, String> logOptions;


    public AdvancedLogger(Class c, String strAppCode) {
        super(c, strAppCode);
    }

    public AdvancedLogger(Class c, String strAppCode, EnumMap options) {
        super(c, strAppCode);


            super.logKeyInfo.setLogOptions((HashMap)convertEnumMap2HashMap(options));

    }

    public AdvancedLogger(Class c, String strAppCode, String type) {
        super(c, strAppCode, type);
    }

    public AdvancedLogger(Class c, String strAppCode, String type, String msg) {
        super(c, strAppCode, type, msg);
    }

    public void d(String strD, EnumMap options) {
        super.d(strD);
    }

    public void i(String strI) {
        super.i(strI);
    }

    public void w(String strW) {
        super.w(strW);
    }

    public void e(String strE, Exception e) {
        super.e(strE, e);
    }


    public Map<String, String> convertEnumMap2HashMap(EnumMap<LogOptions, String> em) {
        HashMap<String, String> retMap = new HashMap<>();
        for (Map.Entry<LogOptions, String> entry : em.entrySet()
                ) {
            retMap.put(entry.getKey().name(), entry.getValue());
        }
        return retMap;
    }

}
