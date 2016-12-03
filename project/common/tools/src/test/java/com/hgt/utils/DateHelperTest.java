package com.hgt.utils;

import com.hgt.utils.DateHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * DateHelper Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 3, 2016</pre>
 */
public class DateHelperTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSimpleDate()
     */
    @Test
    public void testGetSimpleDate() throws Exception {
        String dateStr = DateHelper.getSimpleDate();
        System.out.println(dateStr);
    }

    /**
     * Method: getFullStandardDate()
     */
    @Test
    public void testGetFullStandardDate() throws Exception {
        String dataStr = DateHelper.getFullStandardDate();
        System.out.println(dataStr);
    }


} 
