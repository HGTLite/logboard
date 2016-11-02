package com.hgt.es.config;

import com.hgt.utils.StringHelper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * INTRO: elasticsearch 配置类
 * Created by root on 11/2/16.
 * ============================================================================
 * CHANGELOG:
 */
public class ESConfig {

    private String clusterName = "";
    private String hostString = "";

    public ESConfig() {
    }

    public ESConfig(String strClusterName, String strHostList) {
        this.clusterName = strClusterName;
        this.hostString = strHostList;
    }

    /**
     * 声明并初始化ES连接client
     * <p>
     * strClusterName es集群名称
     * strHostList  es集群主机host列表，形如192.168.100.240:9300,192.168.100.241:9300
     *
     * @return 操作可用的client
     */
    public TransportClient createESClient() {

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .build();

        TransportClient client = null;
        client = new PreBuiltTransportClient(settings);

        List<HashMap<String, String>> hostList = StringHelper.getListFromString(hostString);

        try {
            for (HashMap<String, String> ipAndPorts : hostList) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAndPorts.get("kk")), Integer.parseInt(ipAndPorts.get("vv"))));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }


}
