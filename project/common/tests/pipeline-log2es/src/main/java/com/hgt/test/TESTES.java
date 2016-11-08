package com.hgt.test;

import com.hgt.es.common.ESAdminOperations;
import com.hgt.es.common.QueryFromES;
import com.hgt.es.config.ESConfig;

/**
 * Created by root on 11/7/16.
 */
public class TESTES {
    public static void main(String[] args) {

        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);


//        esAdminOperations.addIndex("one-index");
        System.out.println(queryFromES.queryIndices());

//        esAdminOperations.deleteIndex("test-log");


        String oneType= "{\n" +
                "  \"properties\": {\n" +
                "    \"contents\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n " +
                "  }\n" +
                "}";

//        esAdminOperations.addType("one-index","one-type", oneType);

        //可用的测试数据
        String logOne = "{" +
                "\"contents\":  \"   NEW ip address: {USER_ID : user001 , USER_IP:210.37.140.90} \" " +
                "}";

//        String d = "{\"appCode\":\"hello1\"," +
//                "\"logType\":\"LOGIN\"," +
//                "\"logMsg\":\"这是第 1 条消息！！！\"," +
//                "\"logOptions\":{\"USER_ID\":\"user001\",\"USER_IP\":\"210.37.140.90\"}}"; //comma报错
        esAdminOperations.indexingDataByPureJson("one-index", "one-type", logOne);


        queryFromES.queryAllByType("one-index","one-type");
        //
        queryFromES.close();
        esAdminOperations.close();

    }

}
