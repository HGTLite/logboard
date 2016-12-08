package com.hgt.entity;

import java.util.Date;

public class StatsByType5s {
    private String statsRid;

    private Date startTime;

    private String logType;

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

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public Integer getLogCounts() {
        return logCounts;
    }

    public void setLogCounts(Integer logCounts) {
        this.logCounts = logCounts;
    }
}