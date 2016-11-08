package com.hgt.es.test;

import com.hgt.es.common.ESAdminOperations;
import com.hgt.es.common.QueryFromES;
import com.hgt.es.config.ESConfig;

public class TESTESCRUD {

    public static void main(String[] args) {

        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);

//        esAdminOperations.addIndex("test-log");
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

//        esAdminOperations.addType("test-log","log-type", oneTypeDesc);


        String typeList = "logLevel:text,logTime:text,codeClass:text,codeFile:text,lineNumber:text,appCode:text,logType:text,logMsg:text,logOptions:text";


        esAdminOperations.addType("test-log,log9type", typeList);

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

//        String logText="{\"logLevel\":\"WARN\",\"logTime\":\"2016-11-08 16:21:01,306\",\"codeClass\":\"com.hgt.App\",\"codeFile\":\"BasicLogger.java\",\"lineNumber\":\"163\",\"appCode\":\"hello1\",\"logType\":\"LOGIN\",\"logMsg\":\"这是第 788 条消息！！！\",\"logOptions\":\":{\"USER_ID\":\"user001\",\"USER_IP\":\"210.41.247.199\"}\"}";
//        String logText = "{\"logLevel\":\"WARN\",\"logTime\":\"2016-11-08 16:21:01,306\",\"codeClass\":\"com.hgt.App\",\"codeFile\":\"BasicLogger.java\",\"lineNumber\":\"163\",\"appCode\":\"hello1\",\"logType\":\"LOGIN\",\"logMsg\":\"这是第 788 条消息！！！\",\"logOptions\":\"{USER_ID: user001 }\"}";

//        esAdminOperations.indexingDataByPureJson("one-index", "one-type", logOne);
//        esAdminOperations.indexingDataByPureJson("test-log", "log9type", logText);


//        queryFromES.queryAllByType("one-index", "one-type");
        queryFromES.close();
        esAdminOperations.close();

    }

}
