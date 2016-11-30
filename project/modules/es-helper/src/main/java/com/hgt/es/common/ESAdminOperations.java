package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import com.hgt.utils.DateHelper;
import com.hgt.utils.StringHelper;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import java.io.Serializable;
import java.util.*;

/**
 * README: es与创建和管理相关的操作，如创建index, type, document
 * Created by root on 11/2/16.
 * ============================================================================
 * CHANGELOG:
 */
public class ESAdminOperations implements Serializable {

    private String hostList = "";
    private TransportClient client = null;
    IndicesAdminClient indicesAdminClient = null;

    public ESAdminOperations(ESConfig esConfig) {
        this.client = esConfig.createESClient();

        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();

    }

    public ESAdminOperations(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, hostList).createESClient();

        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();
    }

    /**
     * 简单添加索引
     * @param strIndexName
     */
    public void addIndex(String strIndexName) {
        indicesAdminClient.prepareCreate(strIndexName).get();
    }

    /**
     * 高级添加索引
     * @param strIndexName
     * @param intNumberOfShards
     * @param intNumberOfReplicas
     */
    public void addIndex(String strIndexName, int intNumberOfShards, int intNumberOfReplicas) {
        indicesAdminClient
                .prepareCreate(strIndexName)
                .setSettings(Settings.builder()
                        .put("index.number_of_shards", intNumberOfShards)
                        .put("index.number_of_replicas", intNumberOfReplicas)
                )
                .get();
    }

    /**
     * 添加以JSON形式表示的type类型到已有索引
     *
     * @param strTypeName
     * @param strTypeJson
     */
    public void addType(String strIndexName, String strTypeName, String strTypeJson) {

        indicesAdminClient.preparePutMapping(strIndexName)
                .setType(strTypeName)
                .setSource(strTypeJson)
                .get();
    }

    /**
     * 添加简单KV形式表示type类型到已有索引
     *
     * @param strIndexAndType 用逗号分隔，作为一个参数是为了能够重载
     * @param strFieldKV
     */
    public void addType(String strIndexAndType, String strFieldKV) {

        List<HashMap<String, String>> tlist = StringHelper.getListFromString(strFieldKV);
        String typeKV = "";

        for (HashMap<String, String> t : tlist) {
            typeKV += "\"" + t.get("kk") + "\":{\"type\":\"" + t.get("vv") + "\"},";
        }
        typeKV = typeKV.substring(0, typeKV.length() - 1);

        String[] indexAndType = strIndexAndType.split(",");
        indicesAdminClient.preparePutMapping(indexAndType[0])
                .setType(indexAndType[1])
                .setSource("{\n" +
                        "  \"properties\": {\n" +
                        typeKV +
                        "  }\n" +
                        "}")
                .get();
    }

    /**
     * 通过纯JSON与type匹配的方式建立索引，数据源格式必须固定
     *
     * @param strIndexName
     * @param strTypeName
     * @param strDataJson
     */
    public void indexingDataByPureJson(String strIndexName, String strTypeName, String strDataJson) {

        String dataId = DateHelper.getSimpleDate().replace(" ", "").replace("-", "").replace(":", "");
        IndexResponse response = client.prepareIndex(strIndexName, strTypeName, dataId)
                .setSource(strDataJson)
                .get();
    }

    /**
     * 通过HashMap与type匹配的方式建立索引
     *
     * @param strIndexName
     * @param strTypeName
     * @param mData
     */
    public void indexingDataByMap(String strIndexName, String strTypeName, String strId, HashMap<String, String> mData) {
        Map<String, String> json = new HashMap<String, String>();
        json = mData;
//        String dataId = DateHelper.getSimpleDate().replace(" ", "").replace("-", "").replace(":", "");
        String dataId = strId;
        IndexResponse response = client.prepareIndex(strIndexName, strTypeName, dataId)
                .setSource(json)
                .get();
    }

    /**
     * 删除索引及数据
     * @param strIndexName
     */
    public void deleteIndex(String strIndexName) {
        indicesAdminClient.prepareDelete(strIndexName).get();
    }

    /**
     * 删除某一类型的文档，本质是先删除索引再建立索引和文档
     * 会同时删除同一索引的其他文档，此方法```慎用```
     * @param strIndexName
     * @param strTypeName
     */
    public void deleteType(String strIndexName, String strTypeName) {
        AdminClient adminClient = client.admin();
        ClusterAdminClient clusterAdminClient = adminClient.cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        List<String> indices=(ArrayList)healths.getIndices().values();
        if (indices.contains(strIndexName)) {
            // TODO: 11/8/16 空索引直接保留  
            ///删除已有索引，再建立同名索引
            indicesAdminClient
                    .prepareDelete(strIndexName).get();

            indicesAdminClient
                    .prepareCreate(strIndexName)
                    .setSettings(Settings.builder())
                    .get();
        } else {
            ///新建索引
            indicesAdminClient
                    .prepareCreate(strIndexName)
                    .setSettings(Settings.builder())
                    .get();
        }
    }

    ///TODO:动态添加新字段
    public void addNewField(String strIndex, String strType, String strNewField) {
    }

    ///TODO:自动关闭
    public void close() {
        this.client.close();
    }

}
