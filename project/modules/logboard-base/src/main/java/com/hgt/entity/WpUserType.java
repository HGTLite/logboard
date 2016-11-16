package com.hgt.entity;

public class WpUserType {
    private Integer utypeId;

    private String utypeName;

    private Integer urightsId;

    public Integer getUtypeId() {
        return utypeId;
    }

    public void setUtypeId(Integer utypeId) {
        this.utypeId = utypeId;
    }

    public String getUtypeName() {
        return utypeName;
    }

    public void setUtypeName(String utypeName) {
        this.utypeName = utypeName == null ? null : utypeName.trim();
    }

    public Integer getUrightsId() {
        return urightsId;
    }

    public void setUrightsId(Integer urightsId) {
        this.urightsId = urightsId;
    }
}