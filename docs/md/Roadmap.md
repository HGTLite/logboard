# LogBoard Dev Plan
## Bugs 不能用的  
- 打日志的类默认是BasicLogger、行号默认是
- bug2

## TO-DO 能用但不好用的
- redis订阅者不在线，消息会消失  
- streaming双写hdfs和elasticsearch拆分解耦，提高容错性
- IP分布图自动调整地图范围
- 依赖去重，如spark_core中的hadoop client_2.2

## RoadMap 更好用的发展方向
- 常用日志格式正则规则整理，方便接入
- 使用Redis结合MySQL提高实时性
- 日志可视化维度增加
- 消息通知
