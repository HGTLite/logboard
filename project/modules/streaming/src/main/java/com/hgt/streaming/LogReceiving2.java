package com.hgt.streaming;


import com.hgt.es.ESAdminOperations;
import com.hgt.es.ESConfig;
import com.hgt.es.QueryFromES;

public class LogReceiving2 {

    public static void main(String[] args) {
        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.41:9300,192.168.99.42:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);

        System.out.println(queryFromES.queryIndices());


        queryFromES.close();
        esAdminOperations.close();


    }

}
