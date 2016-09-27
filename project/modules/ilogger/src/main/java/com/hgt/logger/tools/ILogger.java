package com.hgt.logger.tools;

/******************************************************************************
 * Created by  Yao  on  2016/9/27
 * README: 日志接口定义
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/

public interface ILogger {

    int VERBOSE = 1;
    int DEBUG = 2;
    int INFO = 3;
    int WARN = 4;
    int ERROR = 5;
    //控制日志
    int NOTHING = 6;

    int LEVEL = VERBOSE;

    void d(String strD);

    void i(String strI);

    void w(String strW);

    void e(String strE,Exception ee);

}
