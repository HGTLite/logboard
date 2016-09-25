//package com.hgt.logger.info;
//
//
//
///******************************************************************************
// * Created by  Yao  on  2016/7/4
// * README:日志内容格式拼凑类
// * ============================================================================
// * CHANGELOG：
// ******************************************************************************/
//@SuppressWarnings("unused")
//public class LogBean {
//
//    //应用ID，默认为0，表示应用未知
//    private int appCode;
//    //用户ID，默认为空
//    private String userId;
//    //用户IP，默认为空
//    private String userIp;
//    //日志类别标签，默认为普通
//    private LogType logTags;
//    //特殊日志内容
//    private String msg;
//    //备用字段，默认为空，可填入输入参数等，用户位置等
//    private String commentsField;
//
//    //无参构造函数
//    public LogBean() {
//        this.appCode = 0;
//        this.userId = "";
//        this.userIp = "";
//        this.logTags = GENERAL;
//        this.msg = "";
//        this.commentsField = "";
//    }
//
//    //指定日志类别标签的构造函数，输入值参考LogTags枚举
//    public LogBean(String type) {
//        this.appCode = 0;
//        this.userId = "";
//        this.userIp = "";
//        this.logTags = LogTags.valueOf(type.toUpperCase());
//        this.msg = "";
//        this.commentsField = "";
//    }
//
//    //指定日志类别标签和特殊内容的构造函数，输入值参考LogTags枚举
//    public LogBean(String type, String m) {
//        this.appCode = 0;
//        this.userId = "";
//        this.userIp = "";
//        this.logTags = LogTags.valueOf(type.toUpperCase());
//        this.msg = m;
//        this.commentsField = "";
//    }
//
//    //指定所有属性的构造函数
//    public LogBean(int intAppcode, String strUserId, String strUserIp, String strType, String m, String strCommentsField) {
//        this.appCode = intAppcode;
//        this.userId = strUserId;
//        this.userIp = strUserIp;
//        this.logTags = LogTags.valueOf(strType.toUpperCase());
//        this.msg = m;
//        this.commentsField = strCommentsField;
//    }
//
//    public int getAppCode() {
//        return appCode;
//    }
//
//    public void setAppCode(int appCode) {
//        this.appCode = appCode;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUserIp() {
//        return userIp;
//    }
//
//    public void setUserIp(String userIp) {
//        this.userIp = userIp;
//    }
//
//    public LogTags getLogTags() {
//        return logTags;
//    }
//
//    public void setLogTags(LogTags logTags) {
//        this.logTags = logTags;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getCommentsField() {
//        return commentsField;
//    }
//
//    public void setCommentsField(String commentsField) {
//        this.commentsField = commentsField;
//    }
//
//    @Override
//    public String toString() {
//        return "LogBean{" +
//                "appCode=" + appCode +
//                ", userId='" + userId + '\'' +
//                ", userIp='" + userIp + '\'' +
//                ", logTags=" + logTags +
//                ", msg='" + msg + '\'' +
//                ", commentsField='" + commentsField + '\'' +
//                '}';
//    }
//}
