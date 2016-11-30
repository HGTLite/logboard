package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * README: es集群基本查询，如index、type、fields
 * Created by Yao on 11/28/16.
 * =============================================================================
 * CHANGELOG:
 */
public class ESBasicQuery {

    public String hostList = "";
    public TransportClient client = null;
    IndicesAdminClient indicesAdminClient = null;

    public ESBasicQuery(ESConfig esConfig) {
        this.client = esConfig.createESClient();

        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();
    }

    public ESBasicQuery(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, strHost).createESClient();
    }

    /**
     * 查询所有索引名
     *
     * @return
     */
    public List<String> queryAllIndices() {

        List<String> resultList = new ArrayList<>();
        AdminClient adminClient = client.admin();
        ClusterAdminClient clusterAdminClient = adminClient.cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        for (ClusterIndexHealth health : healths.getIndices().values()) {
            String index = health.getIndex();
            resultList.add(index);
//            System.out.println(" index - " + index);
        }
        client.close();
        return resultList;
    }


    /**
     * 查询所有类型名
     *
     * @return
     */
    public List<String> queryAllTypes(String strIndex) {

        List<String> resultList = new ArrayList<>();
        GetMappingsResponse res = null;
        try {
            res = indicesAdminClient.getMappings(new GetMappingsRequest().indices(strIndex)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ImmutableOpenMap<String, MappingMetaData> mapping = res.mappings().get(strIndex);
        for (ObjectObjectCursor<String, MappingMetaData> c : mapping) {
//            System.out.println(c.key + " = " + c.value.source());
            resultList.add(c.value.source().toString());
        }
        client.close();
        return resultList;
    }

    /**
     * 查询所有字段名
     *
     * @return
     */
    public List<String> queryAllFields(String strIndex, String strType) {

        List<String> resultList = new ArrayList<>();
        AdminClient adminClient = client.admin();
        ClusterAdminClient clusterAdminClient = adminClient.cluster();
        ClusterState cs = clusterAdminClient.prepareState().setIndices(strIndex).execute().actionGet().getState();
        IndexMetaData imd = cs.getMetaData().index(strIndex);
        MappingMetaData mdd = imd.mapping(strType);
        Map<String, Object> map = null;

        try {
            map = mdd.getSourceAsMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultList = getList("", map);

//        for (String field : resultList) {
//            System.out.println(field);
//        }
        client.close();
        return resultList;
    }

    /**
     * 获取类型名
     *
     * @param fieldName
     * @param mapProperties
     * @return
     */
    private static List<String> getList(String fieldName, Map<String, Object> mapProperties) {
        List<String> fieldList = new ArrayList<String>();
        Map<String, Object> map = (Map<String, Object>) mapProperties.get("properties");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (((Map<String, Object>) map.get(key)).containsKey("type")) {
                fieldList.add(fieldName + "" + key);
            } else {
                List<String> tempList = getList(fieldName + "" + key + ".", (Map<String, Object>) map.get(key));
                fieldList.addAll(tempList);
            }
        }
        return fieldList;
    }


}
