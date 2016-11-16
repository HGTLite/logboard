package com.hgt.entity;

public class WpUserRights {
    private Integer rightsId;

    private String rightsType;

    private String rightsDetails;

    public Integer getRightsId() {
        return rightsId;
    }

    public void setRightsId(Integer rightsId) {
        this.rightsId = rightsId;
    }

    public String getRightsType() {
        return rightsType;
    }

    public void setRightsType(String rightsType) {
        this.rightsType = rightsType == null ? null : rightsType.trim();
    }

    public String getRightsDetails() {
        return rightsDetails;
    }

    public void setRightsDetails(String rightsDetails) {
        this.rightsDetails = rightsDetails == null ? null : rightsDetails.trim();
    }
}