package com.hgt.serialize;

public class TestSerialize {

    public static void main(String[] args) {

        T1 t1 = new T1();
        T2 t2 = new T2();

        System.out.println("t1: " + (t1 instanceof java.io.Serializable));
        System.out.println("t1: " + (t2 instanceof java.io.Serializable));
    }
}

class T1 {

}

class T2 implements java.io.Serializable {

}
