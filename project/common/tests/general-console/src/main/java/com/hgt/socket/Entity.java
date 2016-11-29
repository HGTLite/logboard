package com.hgt.socket;

import java.io.Serializable;


/**
 * 实体类
 *
 * @author Administrator
 */
public class Entity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Entity [name=" + name + ", gender=" + sex + "]";
    }
}