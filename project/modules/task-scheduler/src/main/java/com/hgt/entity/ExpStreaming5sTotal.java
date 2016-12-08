package com.hgt.entity;

import java.util.Date;

public class ExpStreaming5sTotal {

    private String statsRid;

    private Date startTime;

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

    public Integer getLogCounts() {
        return logCounts;
    }

    public void setLogCounts(Integer logCounts) {
        this.logCounts = logCounts;
    }
}