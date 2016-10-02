package com.hgt.logger;

/******************************************************************************
 * Created by  Yao  on  2016/9/27
 * README: 日志接口抽象类基类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/

public abstract class ILogger {

    protected int VERBOSE = 1;
    protected int DEBUG = 2;
    protected int INFO = 3;
    protected int WARN = 4;
    protected int ERROR = 5;
    //定义不输出日志的级别
    protected int NOTHING = 6;

    /**
     * 设置日志当前打印级别
     */
    protected int LEVEL = VERBOSE;

    abstract void d(String strD);

    abstract void i(String strI);

    abstract void w(String strW);

    abstract void e(String strE, Exception ee);

}
