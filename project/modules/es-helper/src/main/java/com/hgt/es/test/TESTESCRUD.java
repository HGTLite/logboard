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

        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);


//        esAdminOperations.addIndex("one-index");
//        System.out.println(queryFromES.queryIndices());

//        esAdminOperations.deleteIndex("test-log");
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

        String oneTypeDesc = "{\n" +
                "  \"properties\": {\n" +
                "    \"contents\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n " +
                "  }\n" +
                "}";

//        esAdminOperations.addType("one-index","one-type", oneTypeDesc);

        //可用的测试数据
        String log = "{\"appCode\":\"hello1\"," +
                "\"logType\":\"LOGIN\"," +
                "\"logMsg\":\"这是第 1 条消息！！！\"," +
                "\"logOptions\":  \"   {USER_ID : user001 , USER_IP:210.37.140.90} \" " +
                "}";

        String logOne = "{" +
                "\"contents\":  \"   {USER_ID : user001 , USER_IP:210.37.140.90} \" " +
                "}";

//        String d = "{\"appCode\":\"hello1\"," +
//                "\"logType\":\"LOGIN\"," +
//                "\"logMsg\":\"这是第 1 条消息！！！\"," +
//                "\"logOptions\":{\"USER_ID\":\"user001\",\"USER_IP\":\"210.37.140.90\"}}"; //comma报错
        esAdminOperations.indexingDataByPureJson("one-index", "one-type", logOne);


        queryFromES.queryAllByType("one-index","one-type");
        queryFromES.close();
        esAdminOperations.close();

    }

}
