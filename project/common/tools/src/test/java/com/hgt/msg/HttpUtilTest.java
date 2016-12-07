package com.hgt.msg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgt.bean.DataResult;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 7, 2016</pre>
 */
public class HttpUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: post(String url, Map params, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testPostForUrlParamsConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: post(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testPostForUrlParamsHeadersConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get(String url, Map params, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testGetForUrlParamsConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testGetForUrlParamsHeadersConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: put(String url, Map params, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testPutForUrlParamsConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: put(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testPutForUrlParamsHeadersConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: delete(String url, Map params, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testDeleteForUrlParamsConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: delete(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testDeleteForUrlParamsHeadersConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: head(String url, Map params, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testHeadForUrlParamsConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: head(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset)
     */
    @Test
    public void testHeadForUrlParamsHeadersConnectTimeoutReadTimeoutCharset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: postJson(String url, String param)
     */
    @Test
    public void testPostJson() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {

        String STATS_HOST_ENDPOINT = "http://localhost:8702";

        String targetGetAllAppsURL = STATS_HOST_ENDPOINT + "/logb/apps/code/list";
        Map paramsMap = new HashMap();
        String existingAppCodes = HttpUtil.get(targetGetAllAppsURL, paramsMap, 2500, 2500, "UTF-8");

        System.out.println(existingAppCodes);
        ObjectMapper mapper = new ObjectMapper();
        DataResult result =mapper.readValue(existingAppCodes,DataResult.class);
        List<String> appCodeList = (ArrayList)result.getData();
        System.out.println(appCodeList.size());

    }


    /**
     * Method: invokeUrl(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String encoding, HttpMethod method)
     */
    @Test
    public void testInvokeUrl() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = HttpUtil.getClass().getMethod("invokeUrl", String.class, Map.class, Map<String,.class, int.class, int.class, String.class, HttpMethod.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
