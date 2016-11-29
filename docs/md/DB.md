# 数据库逻辑设计
落地在系统中的数据分为3大类：  
用于日志备份和历史搜索的日志存放在HBase中，与原始日志最相近，存放时间暂定1年；  
用于加速搜索的日志放在elasticsearch中，方便对自定义logOptions内容进行搜索，存放时间暂定3个月；  
用于前端绘图的日志统计结果存放在Redis和MySQL中，Redis只存当天的统计结果，存放时间暂定24h，MySQL存放历史统计结果，存放时间暂定5年；  

##HBase表设计
表名 LOG_RECORDS
字段 8个固定字段+N个灵活字段

|Fields               |      DESC            |
|:--------------------|:---------------------|
| logLevel            |日志级别               |
| logTime             |日志时间               |
| codeClass           |类名                    |
| codeFile            |文件名                  |
| lineNumber          |行号                    |
| appCode             |应用编号                |
| logType             |日志类别                |
| logMsg              |自定义日志消息内容        |

其他自定义字段如：  
 UserIp, UserId, UserPlatform, LogTag,
 AppModule, AppThread, LogExp, LogLocation,
 Inputs, Outputs, CoderComments, LogTopic

## elasticsearch搜索数据结构设计
8个固定字段+1个大字段     

|Fields               |      DESC            |
|:--------------------|:---------------------|
| logLevel            |日志级别               |
| logTime             |日志时间               |
| codeClass           |类名                    |
| codeFile            |文件名                  |
| lineNumber          |行号                    |
| appCode             |应用编号                |
| logType             |日志类别                |
| logMsg              |自定义日志消息内容        |
| logOptions          |自定义日志字段           |

重点是拼凑查询条件，已走通技术
搜索接口维度设计：
- 时间
- 应用名
- 类别

- 综合搜索会较慢


## MySQL统计结果表设计
统计的最小粒度为5s，5s的统计结果存放在redis    
统计的指标包括：各应用的日志APPS_DAILY、各类别的日志、异常日志ID

**MySQL的表结构转化为Redis的List数据结构**

暂不考虑的指标：在线系统心跳检测

MySQL流水测试表 REAL_TIME_LOGS_5S

|Fields               |      DESC            |
|:--------------------|:---------------------|
| startTime           |日志时段，最小粒度30s |
| logIDs              |日志类别               |
| counts              |日志数量               |


计数统计表--按类别

|Fields               |      DESC            |
|:--------------------|:---------------------|
| timePeriod          |日志时段，最小粒度30s |
| logType             |日志类别               |
| counts              |日志数量               |
| logIDs              |日志ID集合             |


计数统计表--按应用  

|Fields               |      DESC            |
|:--------------------|:---------------------|
| timePeriod          |日志时段，最小粒度30s|
| appCode             |应用系统               |
| counts              |日志数量               |
| logIDs              |日志ID集合             |

异常统计表--计数

|Fields               |      DESC            |
|:--------------------|:---------------------|
| timePeriod          |日志时段，最小粒度30s |
| appCode             |应用系统               |
| counts              |日志数量               |
| logIDs              |日志ID集合             |

预警通知表   

|Fields               |      DESC                   |
|:--------------------|:----------------------------|
| notifyTime          |通知时间                      |
| notifyTo            |通知对象 ：所有用户/单个用户    |
| notifyContents      |通知内容                      |





