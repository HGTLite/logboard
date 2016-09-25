package com.hgt.logger.info;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:日志类型枚举, 共10类
 * 1-GENERAL--一般类
 * 2-QUERY--用户搜索关键词类
 * 3-GUI--用户界面点击交互类
 * 4-ACCOUNT--账户资料修改类
 * 5-LOGIN--用户登陆退出类
 * 6-SYSTEM--系统日志
 * 7-DELETE--数据删除类
 * 8-ADD--数据库或表创建、数据插入
 * 9-UPDATE--数据库修改或更新
 * 10-TEST--测试日志
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@SuppressWarnings("unused")
public enum LogType {
    GENERAL,
    QUERY,
    GUI,
    ACCOUNT,
    LOGIN,
    SYSTEM,
    DBDELETE,
    DBADD,
    DBUPDATE,
    TEST
}
