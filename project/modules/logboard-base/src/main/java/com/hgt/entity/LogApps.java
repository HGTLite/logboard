package com.hgt.entity;

public class LogApps {

    private String laId;

    private String appName;

    private String appCode;

    private String appIp;

    private String appDesc;

    public String getLaId() {
        return laId;
    }

    public void setLaId(String laId) {
        this.laId = laId == null ? null : laId.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp == null ? null : appIp.trim();
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc == null ? null : appDesc.trim();
    }
}