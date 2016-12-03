# LogBoard Dev Plan
## Bugs 不能用的  
- 发心跳时阻塞spring的启动
- 日志输出的类不是实际类，默认是BasicLogger，行号默认固定，可以考虑重新封装log4j
- 日志工具使用的是log4j，可能与应用系统本身日志记录工具冲突，如es程序默认日志是log4j2，可以考虑从底层自己构建logger

## TO-DO 能用但不好用的
- spark计数异常日志，取不到明细信息，无解
- redis订阅者不在线，消息会消失，可以考虑更换消息队列
- streaming任务过多，可以考虑把双写hdfs和elasticsearch拆分解耦，提高容错性
- UI完善，IP分布图自动调整地图范围
- 依赖去重，如spark_core中的hadoop client_2.2

## RoadMap 更好用的发展方向
- 常用日志格式正则规则整理，方便接入
- 日志可视化维度增加
- 消息通知
