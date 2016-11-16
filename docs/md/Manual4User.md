# User Manual 日志系统使用手册

### 使用概述

### 日志接入

### 日志可视化

### 接口汇总 


###Requirements 前提

- 打日志前要选择合适的工具类,BasicLogger快速打日志，AdanvancedLogger设置日志详细参数；
- BasicLogger 可设置3项：AppCode,  LogType, LogMsg
- AdvancedLogger 可设置：
      AppCode,  LogType, LogMsg, 
      UserIp, UserId, UserPlatform, LogTag,
      AppModule, AppThread, LogExp, LogLocation,
      Inputs, Outputs, CoderComments, LogTopic
- steraming日志必有8项
      logLevel, logTime, codeClass, codeFile, lineNumber, appCode, logType, logMsg, logOptions

    