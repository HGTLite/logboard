package com.hgt.grammar;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/18
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTList {

    public static void main(String[] args) {

        System.out.println("======BEGIN=====");

        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("");
        list.add("hi");
        list.add(null);
        list.add("you");

        System.out.println("list的长度是："+list.size());

        for (String s:list){
            System.out.println(s);
        }


    }

}
