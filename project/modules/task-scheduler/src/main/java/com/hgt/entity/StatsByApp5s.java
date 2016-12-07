package com.hgt.entity;

import java.util.Date;

public class StatsByApp5s {
    private String statsRid;

    private Date startTime;

    private String appCode;

    private Integer logCounts;

    public String getStatsRid() {
        return statsRid;
    }

    public void setStatsRid(String statsRid) {
        this.statsRid = statsRid == null ? null : statsRid.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getLogCounts() {
        return logCounts;
    }

    public void setLogCounts(Integer logCounts) {
        this.logCounts = logCounts;
    }
}