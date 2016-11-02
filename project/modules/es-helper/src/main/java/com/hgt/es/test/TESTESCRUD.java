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
//        QueryFromES queryFromES = new QueryFromES(esConfig);
        ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);

//        System.out.println(queryFromES.queryIndices());

//        esAdminOperations.addIndex("test-index");

        esAdminOperations.deleteIndex("test-index");

    }

}
