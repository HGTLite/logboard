package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * README:es与查询所有相关的操作
 *
 * Created by root on 11/2/16.
 * =============================================================================
 * CHANGELOG:
 */
public class ESQueryAll implements Serializable {

    public String hostList = "";
    public TransportClient client = null;
    IndicesAdminClient indicesAdminClient = null;


    public ESQueryAll(ESConfig esConfig) {
        this.client = esConfig.createESClient();

        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();
    }

    public ESQueryAll(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, strHost).createESClient();
    }



    /**
     * 简单查询某一类型的文档，默认按时间将序排列
     *
     * @param strIndex
     * @param strType
     */
    public String queryAllByType(String strIndex, String strType) {

        String resultStr = "";

        QueryBuilder qb = matchAllQuery();

        SearchResponse sr = client.prepareSearch(strIndex)
                .setTypes(strType)
                .setQuery(qb)
                .addSort("logTime", SortOrder.DESC)
                .execute()
                .actionGet();


        SearchHit[] hits = sr.getHits().getHits();

        for (SearchHit hit : hits) {

            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                System.out.println(sourceAsString);
                resultStr += sourceAsString;
            }
        }
        return resultStr;
    }

    ///TO-DO:查询某个字段
    public void queryAllByField(String strIndex, String strType, String strField) {

    }


    ///TO-DO:自动关闭
    public void close() {
        this.client.close();
    }

}
