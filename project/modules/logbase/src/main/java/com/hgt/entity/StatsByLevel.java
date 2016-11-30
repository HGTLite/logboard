package com.hgt.entity;

import java.util.Date;

public class StatsByLevel {
    private String statsRid;

    private Date startTime;

    private String logLevel;

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

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel == null ? null : logLevel.trim();
    }

    public Integer getLogCounts() {
        return logCounts;
    }

    public void setLogCounts(Integer logCounts) {
        this.logCounts = logCounts;
    }
}