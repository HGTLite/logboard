# 集群规划  
## 云主机 (无法使用)   
   
   |   Hostname  |                         用途描述                  |       内网IP    |       外网IP        |     配置信息    | 
   |:-----------:|:-------------------------------------------------|:--------------:|:-------------------:|:-------------------:|
   |nd09.hgt.com | 开发测试主机，灵活                                 |   192.168.100.9 |   192.168.99. X     |CPU4+RAM8+DISK80 |  
   |nd10.hgt.com | 部署测试主机，灵活                                 |   192.168.100.10 |   192.168.99. X    |CPU4+RAM8+DISK80 |  
   |nd12.hgt.com | master节点，HDFS，HBase，MySQL                    |   192.168.100.12 |   192.168.99. 152  | CPU8+RAM16+DISK160 PHP |   
   |nd13.hgt.com | slave节点，DataNode，RegionServer，Spark，es       |   192.168.100.13 |   192.168.99. 153  | CPU4+RAM8+DISK80   |  
   |nd14.hgt.com | slave节点，DataNode，RegionServer，Spark，es       |   192.168.100.14 |   192.168.99. 154  | CPU4+RAM8+DISK80   |  
   |nd15.hgt.com | slave节点，DataNode，RegionServer，Spark，es       |   192.168.100.15 |   192.168.99. 155  | CPU4+RAM8+DISK80   |  
   |nd16.hgt.com | slave节点，MySQL，Ambari前端，日志前端，云盘前端      |   192.168.100.16 |   192.168.99. 156 | CPU4+RAM8+DISK80   |  
  
  
#### 端口说明
   
   |    服务      |    端口号     |    说明         |
   |:-----------:|:-------------:|:---------------|
   |    httpd      |    80       |    简单服务     |
   |    nginx      |    8787     |    负载均衡     |
   |    tomcat     |    8780     |    业务web      |


#### Env 本地集群

- 4台虚拟机位于物理机192.168.100.4/192.168.99.80，IP地址、主机名、登陆用户、登陆密码分别为:  
- 192.168.99.140  c140 root 111111   ambari
- 192.168.99.141  c141 root 111111   各种master, hbase
- 192.168.99.142  c142 root 111111  
- 192.168.99.143  c143 root 111111  

MySQL主要数据库
- 123.206.29.126 logbase root 111111

- 测试所用kafka topic为：topic-hello，topic-hello2，返回值查看在192.168.99.112:8011/a

- hbase的表为one-log

