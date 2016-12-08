package com.hgt.entity;

import java.util.Date;

public class LogBeatsLost {
    private String beatId;

    private Date lastTime;

    private String appCode;

    private Long lostInterval;

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId == null ? null : beatId.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Long getLostInterval() {
        return lostInterval;
    }

    public void setLostInterval(Long lostInterval) {
        this.lostInterval = lostInterval;
    }
}