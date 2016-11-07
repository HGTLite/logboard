package com.hgt.es;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.search.SearchHit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * INTRO:ES与查询相关的操作
 * <p>
 * Created by root on 11/2/16.
 * =============================================================================
 * CHANGELOG:
 */
public class QueryFromES implements Serializable {

    public String hostList = "";
    public TransportClient client = null;
    IndicesAdminClient indicesAdminClient = null;


    public QueryFromES(ESConfig esConfig) {
        this.client = esConfig.createESClient();

        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();
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

    /**
     * 简单查询某一类型的文档
     *
     * @param strIndex
     * @param strType
     */
    public void queryAllByType(String strIndex, String strType) {

        SearchResponse sr = client.prepareSearch(strIndex)
                .setTypes(strType)
                .execute()
                .actionGet();

        SearchHit[] hits = sr.getHits().getHits();

        for (SearchHit hit : hits) {

            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {

                System.out.println(sourceAsString);
            }
        }


    }

    ///TO-DO:查询某个字段
    public void queryAllByField(String strIndex, String strType, String strField) {

    }


    ///TO-DO:自动关闭
    public void close() {
        this.client.close();
    }

}
