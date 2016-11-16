package com.hgt.domain;

/******************************************************************************
 * Created by  Yao  on  2016/7/27
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class SimpleStringBean {

    private String contents;

    public SimpleStringBean(String contents) {
        this.contents = contents;
    }

    public SimpleStringBean() {
        contents = "default result data is this - 这是默认返回内容 ";
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
