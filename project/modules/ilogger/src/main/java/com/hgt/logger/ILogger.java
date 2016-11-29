package com.hgt.logger;

/******************************************************************************
 * Created by  Yao  on  2016/9/27
 * README: 日志接口抽象类基类
 * ============================================================================
 * CHANGELOG：161129添加日志开关存取器
 ******************************************************************************/

public abstract class ILogger {

    protected int VERBOSE = 0;
    protected int DEBUG = 1;
    protected int INFO = 2;
    protected int WARN = 3;
    protected int ERROR = 4;
    //定义不输出日志的级别
    protected int NOTHING = 5;

    /**
     * 设置日志当前打印级别，可作为日志开关
     */
//    protected int LEVEL = VERBOSE;
    protected int LEVEL ;

    abstract void d(String strD);

    abstract void i(String strI);

    abstract void w(String strW);

    abstract void e(String strE, Exception ee);

    public int getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(int LEVEL) {
        this.LEVEL = LEVEL;
    }
}
