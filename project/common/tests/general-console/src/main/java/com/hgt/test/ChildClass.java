package com.hgt.test;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:
 * ============================================================================
 * CHANGELOG：结论是子类变量屏蔽父类变量，可通过super.调用父类变量
 ******************************************************************************/
public class ChildClass extends ParentClass {
    public  int i=30;

    public  static void main(String[] args ){
        ParentClass parentClass = new ParentClass();
        ChildClass childClass = new ChildClass();
        System.out.println(parentClass.i+childClass.i);
    }
}
