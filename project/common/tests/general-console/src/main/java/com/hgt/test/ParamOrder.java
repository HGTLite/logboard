package com.hgt.test;

/******************************************************************************
 * Created by  Yao  on  2016/10/8
 * README: 函数重载时，参数顺序起作用
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class ParamOrder {

    public void getKV(String ss, int ii) {

        System.out.println("hello");
    }

    public void getKV(int ii, String ss) {
        System.out.println("hi");

    }

}
