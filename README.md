
# LogBoard 日志公告牌
LogBoard是为其他系统提供统一的日志收集、存储管理、统计分析、可视化展示等功能的工具。`in progress`
### 技术路线
日志收集----flume/kafka  
日志存储----hbase  
统计分析----spark streaming  
日志展示----html5+bootstrap+jQuery+d3.js  
### 项目结构
logboard  
    |----------docs    
    |----------project   
　　　　　|----------common  
　　　　　　　　　　　|----------examples  
　　　　　　　　　　　|----------tests  
　　　　　　　　　　　　　　　　　|----------general-console  
　　　　　　　　　　　　　　　　　|----------general-web  
　　　　　　　　　　　|----------tools  
　　　　　|----------dependencies    
　　　　　|----------modules    
　　　　　　　　　　　|----------ilogger   
　　　　　　　　　　　|----------starter       
　　　　　　　　　　　|----------streaming   
　　　　　　　　　　　|----------notification   
　　　　　　　　　　　|----------hbase-helper  
　　　　　　　　　　　|----------rest-query  
　　　　　　　　　　　|----------logboard-base  
　　　　　　　　　　　|----------logboard-views  
　　　　　　　　　　　　　　　　　|----------logboard-portal  
    |----------*resources*  
　　　　　|----------*conf*  
　　　　　|----------*data*  
　　　　　|----------*scripts*  
　　　　　|----------*styles*  
　　　　　|----------*assets*  
    |----------README.md　  　　　　　　　　　　　　　　　　
### 说明  
- 测试入口为各个工程的App类main方法     
    
### Screenshots  
