package com.hgt.serialize;

/******************************************************************************
 * Created by  Yao  on  2016/7/12
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class PersonApp {

    public static void main(String[] args){
        new PersonSerializer().serialize2file();
        new PersonSerializer().deserialize2bean("person.txt");
    }
}
