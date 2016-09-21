package com.hgt.hbase.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:配置文件管理类 for HBase
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class HBaseConfig {


    public Configuration createHBaseConfiguration() {
        Configuration configuration;
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.100.121,192.168.100.122,192.168.100.123");
        configuration.set("hbase.master", "192.168.100.121:60000");
        return configuration;
    }

}
