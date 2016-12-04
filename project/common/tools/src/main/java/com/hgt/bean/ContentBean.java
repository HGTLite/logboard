package com.hgt.bean;

/**
 * README: 仅有一个属性的bean
 * Created by root on 11/25/16.
 * =============================================================================
 * CHANGELOG:
 */

public class ContentBean {

    private String content;

    public ContentBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
