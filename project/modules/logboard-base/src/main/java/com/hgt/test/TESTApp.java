package com.hgt.test;


import com.hgt.common.utils.SecurityUtil;

/******************************************************************************
 * Created by  Yao  on  2016/7/24
 * README:测试入口类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTApp {

    public static void main(String[] args) {

//    System.out.println();

    System.out.println(new SecurityUtil().SHA256String("111111"));


    }

}
