package com.hgt.logger.formats;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:日志类型枚举, 共11类
 *        01-GENERAL--一般类
 *        02-QUERY--用户搜索关键词类
 *        03-GUI--用户界面点击交互类
 *        04-ACCOUNT--账户资料修改类
 *        05-LOGIN--用户登陆退出类
 *        06-SYSTEM--系统日志
 *        07-DELETE--数据删除类
 *        08-DBADD--数据库或表创建、数据插入
 *        09-DBUPDATE--数据库修改或更新
 *        10-FLLOWS--值得关注的特殊类别
 *        11-TEST--测试日志
 * ============================================================================
 * CHANGELOG：160926类别重构
 ******************************************************************************/
@SuppressWarnings("unused")
public enum LogType {
    GENERAL,
    QUERY,
    GUI,
    ACCOUNT,
    LOGIN,
    SYSTEM,
    DELETE,
    DBADD,
    DBUPDATE,
    FLLOWS,
    TEST
}
