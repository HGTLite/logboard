package com.hgt.entity;

import java.util.Date;

public class LogCountsStreaming {

    private String statsRid;

    private Date startTime;

    private Long counts;

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

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }
}