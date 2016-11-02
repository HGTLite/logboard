package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterIndexHealth;

import java.util.ArrayList;
import java.util.List;

/**
 * INTRO:ES与查询相关的操作
 * <p>
 * Created by root on 11/2/16.
 * =============================================================================
 * CHANGELOG:
 */
public class QueryFromES {

    public String hostList = "";
    public TransportClient client = null;

    public QueryFromES(ESConfig esConfig) {
        this.client = esConfig.createESClient();
    }

    public QueryFromES(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, strHost).createESClient();
    }


    /**
     * 查询集群的所有索引
     *
     * @return
     */
    public List<String> queryIndices() {
        List<String> indices = new ArrayList<String>();
        AdminClient adminClient = client.admin();
        ClusterAdminClient clusterAdminClient = adminClient.cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        for (ClusterIndexHealth health : healths.getIndices().values()) {
            String index = health.getIndex();
            indices.add(index);
//            System.out.println(" index - " + index);
        }
        return indices;
    }

    public void queryAllByType(String strIndex, String strType) {

    }

    public void queryAllByField(String strIndex, String strType, String strField) {

    }


    ///TO-DO:自动关闭
    public void close() {
        this.client.close();
    }

}
