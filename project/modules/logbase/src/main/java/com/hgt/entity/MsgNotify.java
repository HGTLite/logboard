package com.hgt.entity;

import java.util.Date;

public class MsgNotify {
    private String msgRid;

    private Date msgTime;

    private String msgFrom;

    private String msgTo;

    private String msgType;

    private String msgContents;

    public String getMsgRid() {
        return msgRid;
    }

    public void setMsgRid(String msgRid) {
        this.msgRid = msgRid == null ? null : msgRid.trim();
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom == null ? null : msgFrom.trim();
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo == null ? null : msgTo.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgContents() {
        return msgContents;
    }

    public void setMsgContents(String msgContents) {
        this.msgContents = msgContents == null ? null : msgContents.trim();
    }
}