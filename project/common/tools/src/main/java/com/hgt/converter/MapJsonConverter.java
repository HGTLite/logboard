package com.hgt.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 11/6/16.
 */
public class MapJsonConverter {

    /**
     * 简单map转换成json
     * @param map
     * @return
     */
    public static String simpleMapToJsonStr(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
        String jsonStr = "{";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
        }
        jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
        jsonStr += "}";
        return jsonStr;
    }

    /**
     * 简单json转换成map
     * @param str
     * @return
     */
    public static Map simpleJsonStrToMap(String str) {
        String sb = str.substring(1, str.length() - 1);
        String[] name = sb.split("\\\",\\\"");
        String[] nn = null;
        Map map = new HashMap();
        for (int i = 0; i < name.length; i++) {
            nn = name[i].split("\\\":\\\"");
            map.put(nn[0].replace("\"",""), nn[1].replace("\"",""));
        }
        return map;
    }


}
