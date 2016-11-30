package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ESQueryAll Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 1, 2016</pre>
 */
public class ESQueryAllTest {

    ESQueryAll esQueryAll;

    @Before
    public void before() throws Exception {
        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        esQueryAll = new ESQueryAll(esConfig);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryAllByType(String strIndex, String strType)
     */
    @Test
    public void testQueryAllByType() throws Exception {
        esQueryAll.queryAllByType("hgt-logs", "common");
    }

    /**
     * Method: queryAllByField(String strIndex, String strType, String strField)
     */
    @Test
    public void testQueryAllByField() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: close()
     */
    @Test
    public void testClose() throws Exception {
//TODO: Test goes here... 
    }


} 
