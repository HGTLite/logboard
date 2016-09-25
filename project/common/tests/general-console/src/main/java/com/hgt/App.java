package com.hgt;

import com.hgt.pattern.ComplicatedDataHolder;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        final ComplicatedDataHolder cdh = new ComplicatedDataHolder.Builder()
                .data("HelloData")
                .num(10086)
                .build();
    }
}
