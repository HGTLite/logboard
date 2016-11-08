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
//      configuration.set("hbase.zookeeper.quorum", "192.168.99.111,192.168.99.112,192.168.99.113,192.168.99.114");
        configuration.set("hbase.zookeeper.quorum", "192.168.99.141,192.168.99.142,192.168.99.143");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//      configuration.set("hbase.master", "192.168.99.111:16000");
        configuration.set("zookeeper.znode.parent", "/hbase-unsecure");

        return configuration;
    }

}
