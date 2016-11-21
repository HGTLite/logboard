package com.hgt.test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * README:
 * Created by root on 11/21/16.
 * =============================================================================
 * CHANGELOG:
 */
public class TESTProperties {

    public static void main(String[] args){

        Properties prop = new Properties();
        try {
//            FileInputStream in = new FileInputStream(new File("/config.properties"));

//            prop.load(in);
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            List<String> items = new ArrayList<>();

            prop.list(System.out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
