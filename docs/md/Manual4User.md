# User Manual 日志系统使用手册

### 使用概述

### 日志接入

### 日志可视化

### 接口汇总   
  
|   services    |       host     |    port    |      desc      |
|:-------------:|:--------------:|:----------:|:---------------|
|logboot webpage| 192.168.99.140 |     8701   | 日志系统主页    |
| logbase  rest | 192.168.99.143 |     8702   | 日志系统后台服务 |
| logbase  db   | 123.206.19.126 |     3306   | root/111111    |

- 日志接入系统操作类 
  - 查询  
   id
  - 所有 
  

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

    