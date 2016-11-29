package com.hgt.logger;

import com.hgt.logger.formats.LogOptions;

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
public class StandardLogger extends BasicLogger {

    //日志可选内容
    private EnumMap<LogOptions, String> options = new EnumMap<>(LogOptions.class);


    public StandardLogger(Class c, String strAppCode) {
        super(c, strAppCode);
    }

    public StandardLogger(Class c, String strAppCode, EnumMap<LogOptions, String> map) {
        super(c, strAppCode);
        super.logOptions((HashMap) convertEnumMap2HashMap(map));
    }

    public StandardLogger(Class c, String strAppCode, String type, EnumMap<LogOptions, String> map) {
        super(c, strAppCode, type);
        super.logOptions((HashMap) convertEnumMap2HashMap(map));
    }

    public StandardLogger(Class c, String strAppCode, String type, String msg, EnumMap<LogOptions, String> map) {
        super(c, strAppCode, type, msg);
        super.logOptions((HashMap) convertEnumMap2HashMap(map));
    }

    /**
     * 将EnumMap转化成HashMap
     *
     * @param em
     * @return
     */
    public Map<String, String> convertEnumMap2HashMap(EnumMap<LogOptions, String> em) {
        HashMap<String, String> retMap = new HashMap<>();
        for (Map.Entry<LogOptions, String> entry : em.entrySet()
                ) {
            retMap.put(entry.getKey().name(), entry.getValue());
        }
        return retMap;
    }

    public StandardLogger options(LogOptions options1, String contents) {
        options.put(options1, contents);
        super.logOptions((HashMap) convertEnumMap2HashMap(options));
        return this;
    }


}
