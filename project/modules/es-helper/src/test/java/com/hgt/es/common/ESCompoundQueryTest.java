package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import com.hgt.es.tools.ESLogBean;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.omg.CORBA.Object;

import java.util.List;
import java.util.Map;

/**
 * ESCompoundQuery Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 1, 2016</pre>
 */
public class ESCompoundQueryTest {

    ESCompoundQuery esCompoundQuery;

    @Before
    public void before() throws Exception {
        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        esCompoundQuery = new ESCompoundQuery(esConfig);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryDocById(String strIndex, String strType, String strId)
     */
    @Test
    public void testQueryDocById() throws Exception {
        ESLogBean ret = esCompoundQuery.queryDocById("hgt-logs", "common", "hello0logi201611301411349cee6086");
        System.out.println(ret);
    }

    /**
     * Method: executeQuery(String strIndex, String strType, String strBody)
     */
    @Test
    public void testExecuteQuery() throws Exception {
       List<ESLogBean> list = esCompoundQuery.simpleStringQuery("hgt-logs", "common", "");
        System.out.println(list.toString());
    }


} 
