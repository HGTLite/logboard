package com.hgt.converter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * MapJsonConverter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 2, 2016</pre>
 */
public class MapJsonConverterTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: simpleMapToJsonStr(Map<String, String> map)
     */
    @Test
    public void testSimpleMapToJsonStr() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: simpleJsonStrToMap(String str)
     */
    @Test
    public void testSimpleJsonStrToMap() throws Exception {
        String str = "{\"passwd\":\"123456\",\"name\":\"wang\",\"tel\":\"15527519293\"}";
        System.out.print(MapJsonConverter.simpleJsonStrToMap(str));

    }


} 
