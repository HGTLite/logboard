package com.hgt.ienum;

public class EnumTest {
    //======然后执行main方法
    public static void main(String[] args) {
        WeekDay weekday = WeekDay.FRI;
        System.out.println(weekday);
        System.out.println(weekday.name());
        System.out.println(weekday.ordinal());
        System.out.println(weekday.valueOf("SUN"));
        System.out.println(weekday.values().length);

//        second
//        first
//        first
//        first
//        first
//        first
//        first
//        FRI
//        FRI
//        5
//        SUN
//        7

    }

    //=====首先实例化所有枚举实例=====
    public enum WeekDay {
        SUN(1), MON(), TUE, WED, THI, FRI, SAT;

        private WeekDay() {
            System.out.println("first");
        }

        private WeekDay(int day) {
            System.out.println("second");
        }
    }
}