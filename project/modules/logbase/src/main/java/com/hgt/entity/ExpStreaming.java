package com.hgt.entity;

import java.util.Date;

public class ExpStreaming {
    private String statsRid;

    private Date startTime;

    private String appCode;

    private String logTime;

    private String logId;

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

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime == null ? null : logTime.trim();
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }
}