package com.hgt.es.common;

import com.hgt.es.config.ESConfig;
import com.hgt.utils.StringHelper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * INTRO: ES与创建与管理相关的操作，如创建index, type, document
 * Created by root on 11/2/16.
 * ============================================================================
 * CHANGELOG:
 */
public class ESAdminOperations {

    private String hostList = "";
    private TransportClient client = null;

    public ESAdminOperations(ESConfig esConfig) {
        this.client = esConfig.createESClient();
    }

    public ESAdminOperations(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, hostList).createESClient();
    }


    public void addIndex(String strIndexName) {
        AdminClient adminClient = client.admin();
        IndicesAdminClient indicesAdminClient = adminClient.indices();
        indicesAdminClient.prepareCreate(strIndexName).get();
    }

    public void addIndex(String strIndexName, int intNumberOfShards, int intNumberOfReplicas) {
        AdminClient adminClient = client.admin();
        IndicesAdminClient indicesAdminClient = adminClient.indices();
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

        AdminClient adminClient = client.admin();
        IndicesAdminClient indicesAdminClient = adminClient.indices();
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
        AdminClient adminClient = client.admin();
        IndicesAdminClient indicesAdminClient = adminClient.indices();
        String typeKV = "";

        for (HashMap<String, String> t : tlist) {
            typeKV += t.get("fname") + ":{\"type\":" + t.get("ftype") + "},";
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

    public void indexingDataTOType(String strDataJson, String strIndexName, String strTypename) {

        String did = "a" + Math.random();
        IndexResponse response = client.prepareIndex(strIndexName, strTypename, did)
                .setSource(strDataJson)
                .get();
    }

    public void deleteIndex(String strIndexName) {
        AdminClient adminClient = client.admin();
        IndicesAdminClient indicesAdminClient = adminClient.indices();
        indicesAdminClient.prepareDelete(strIndexName).get();
    }

    ///TO-DO
    public void addNewField(String strIndex, String strType, String strNewField) {

    }

    ///TO-DO:自动关闭
    public void close() {
        this.client.close();
    }

}
