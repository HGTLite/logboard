package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * ESBasicQuery Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Nov 28, 2016</pre>
 */
public class ESBasicQueryTest {
    ESBasicQuery esBasicQuery;

    @Before
    public void before() throws Exception {

        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        esBasicQuery = new ESBasicQuery(esConfig);

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: queryAllIndices()
     */
    @Test
    public void testQueryAllIndices() throws Exception {
        List<String> list = new ArrayList<>();
        list = esBasicQuery.queryAllIndices();
        assertEquals(3, list.size());
    }

    /**
     * Method: queryAllTypes(String strIndex)
     */
    @Test
    public void testQueryAllTypes() throws Exception {
        List<String> list = new ArrayList<>();
        list = esBasicQuery.queryAllTypes("test-log");
        for (String s : list) {
            System.out.println(s);
        }
        assertEquals(1, list.size());

    }

    /**
     * Method: queryAllFields(String strIndex, String strType)
     */
    @Test
    public void testQueryAllFields() throws Exception {
        List<String> list = new ArrayList<>();
        list = esBasicQuery.queryAllFields("test-log","log9type");
        for (String s : list) {
            System.out.println(s);
        }
        assertEquals(9, list.size());
    }


    /**
     * Method: getList(String fieldName, Map<String, Object> mapProperties)
     */
    @Test
    public void testGetList() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ESBasicQuery.getClass().getMethod("getList", String.class, Map<String,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
