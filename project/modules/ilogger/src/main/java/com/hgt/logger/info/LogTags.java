package com.hgt.logger.info;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:日志类型标签枚举, 共9类
 * GENERAL--一般类
 * SEARCH--用户搜索关键词类
 * GUI--用户界面点击交互类
 * ACCOUNT--账户资料修改类
 * LOGIN--用户登陆退出类
 * SYSTEM--系统日志
 * DELETE--数据删除类
 * ADD--数据库或表创建、数据插入
 * UPDATE--数据库修改或更新
 * TEST--测试日志
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@SuppressWarnings("unused")
public enum LogTags {
    GENERAL,
    SEARCH,
    GUI,
    ACCOUNT,
    LOGIN,
    SYSTEM,
    DELETE,
    ADD,
    UPDATE,
    TEST
}
