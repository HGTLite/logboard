package com.hgt.entity;

import java.util.Date;

public class LogBeatsNormal {

    private String beatId;

    private Date beatTime;

    private String appCode;

    private String beatMsg;

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId == null ? null : beatId.trim();
    }

    public Date getBeatTime() {
        return beatTime;
    }

    public void setBeatTime(Date beatTime) {
        this.beatTime = beatTime;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getBeatMsg() {
        return beatMsg;
    }

    public void setBeatMsg(String beatMsg) {
        this.beatMsg = beatMsg == null ? null : beatMsg.trim();
    }
}