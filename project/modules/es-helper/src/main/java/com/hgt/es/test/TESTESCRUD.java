package com.hgt.es.test;

import com.hgt.es.common.ESAdminOperations;
import com.hgt.es.common.QueryFromES;
import com.hgt.es.config.ESConfig;

/**
 * INTRO:
 * <p>
 * Created by root on 11/2/16.
 * ============================================================================
 * CHANGELOG:
 */
public class TESTESCRUD {

    public static void main(String[] args) {

        ESConfig esConfig = new ESConfig("es-log", "192.168.100.240:9300,192.168.100.240:9300,192.168.100.240:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
//        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);

//        System.out.println(queryFromES.queryIndices());

//        esAdminOperations.addIndex("test-log");

//        esAdminOperations.deleteIndex("test-index");
        String typeDesc = "{\n" +
                "  \"properties\": {\n" +
                "    \"appCode\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n ," +
                "    \"logType\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n ," +
                "    \"logMsg\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n ," +
                "    \"logOptions\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n " +
                "  }\n" +
                "}";

//        esAdminOperations.addType("test-log","logmeta1", typeDesc);

        //可用的测试数据
        String d = "{\"appCode\":\"hello1\"," +
                "\"logType\":\"LOGIN\"," +
                "\"logMsg\":\"这是第 1 条消息！！！\"," +
                "\"logOptions\":  \"   {USER_ID : user001 , USER_IP:210.37.140.90} \" " +
                "}";


//        String d = "{\"appCode\":\"hello1\"," +
//                "\"logType\":\"LOGIN\"," +
//                "\"logMsg\":\"这是第 1 条消息！！！\"," +
//                "\"logOptions\":{\"USER_ID\":\"user001\",\"USER_IP\":\"210.37.140.90\"}}";
//        esAdminOperations.indexingDataByPureJson("test-log", "logmeta1", d);

//        esAdminOperations.close();


        queryFromES.queryAllByType("test-log","logmeta1");
        queryFromES.close();

    }

}
