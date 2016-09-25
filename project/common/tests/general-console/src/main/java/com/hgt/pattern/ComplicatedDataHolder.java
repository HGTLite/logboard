package com.hgt.pattern;

/**
 * 目标：用静态内部类初始化外部类
 */
public class ComplicatedDataHolder {

    public final String data;
    public final int num;

    public ComplicatedDataHolder(String data0, int num0) {
        this.data = data0;
        this.num = num0;
    }

    // lots more fields and a constructor
    public static class Builder {
        private String data1;
        private int num1;

        public Builder data(String data2) {
            this.data1 = data2;
            return this;
        }

        public Builder num(int num2) {
            this.num1 = num2;
            return this;
        }

        public ComplicatedDataHolder build() {
            return new ComplicatedDataHolder(data1, num1); // etc
        }
    }
}
