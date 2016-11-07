//package com.hgt.test;
//
//
//import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.AdminClient;
//import org.elasticsearch.client.ClusterAdminClient;
//import org.elasticsearch.client.IndicesAdminClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.cluster.health.ClusterHealthStatus;
//import org.elasticsearch.cluster.health.ClusterIndexHealth;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.sort.FieldSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Date;
//
//import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
//import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
//
//public class TESTQuery {
//
//    public static void main(String[] args) {
//
//        Settings settings = Settings.builder()
//                .put("cluster.name", "es-log")
//                .put("client.transport.sniff", true)
//                .build();
//        TransportClient client = null;
//
//        try {
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.240"), 9300))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.241"), 9300))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.243"), 9300));
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//        AdminClient adminClient = client.admin();
//        IndicesAdminClient indicesAdminClient = adminClient.indices();
//
//
//        //region 查询集群健康状态
//        //        ClusterAdminClient clusterAdminClient = adminClient.cluster();
////
////        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
////        String clusterName = healths.getClusterName();
//
////        int numberOfDataNodes = healths.getNumberOfDataNodes();
////        int numberOfNodes = healths.getNumberOfNodes();
////        System.out.println("clusterName-" + clusterName + " ;  numberOfDataNodes-" + numberOfDataNodes + " ;  numberOfNodes-" + numberOfNodes);
////
////        for (ClusterIndexHealth health : healths.getIndices().values()) {
////            String index = health.getIndex();
////            int numberOfShards = health.getNumberOfShards();
////            int numberOfReplicas = health.getNumberOfReplicas();
////
////            System.out.println(" index-" + index + " ; numberOfShards-" + numberOfShards + " ; numberOfReplicas-" + numberOfReplicas);
////        }
//        //endregion
//
//
//        //新建index
////        indicesAdminClient.prepareCreate("twitter")
////                .setSettings(Settings.builder()
////                        .put("index.number_of_shards", 3)
////                        .put("index.number_of_replicas", 1)
////                )
////                .get();
//
//
//        //新建类型1：添加新类型
////        indicesAdminClient.prepareCreate("twitter")
////                .addMapping("tweet", "{\n" +
////                        "    \"tweet\": {\n" +
////                        "      \"properties\": {\n" +
////                        "        \"message\": {\n" +
////                        "          \"type\": \"string\"\n" +
////                        "        }\n" +
////                        "      }\n" +
////                        "    }\n" +
////                        "  }")
////                .get();
//
//
//        indicesAdminClient.preparePutMapping("hgt-logs")
//                .setType("loginfo")
//                .setSource("{\n" +
//                        "  \"appCode\": {\n" +
//                        "    \"logType\": {\n" +
//                        "      \"logOptions\": \"string\"\n" +
//                        "    }\n" +
//                        "  }\n" +
//                        "}")
//                .get();
//
//
//        //数据索引并搜索
//        String jsonT = "{" +
//                "\"user\":\"jolin\"," +
//                "\"postDate\":\"2016-11-01\"," +
//                "\"message\":\"hello, this is the gift from outter world called 潘多拉\"" +
//                "}";
////        try {
////            IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
////                    .setSource(jsonBuilder()
////                            .startObject()
////                            .field("user", "ego")
////                            .field("postDate", new Date())
////                            .field("message", "trying out Elasticsearch")
////                            .endObject()
////                    )
////                    .get();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
////        IndexResponse response = client.prepareIndex("twitter", "tweet")
////                .setSource(jsonT)
////                .get();
////        System.out.println(response.getId());
//
//        //查询数据
//        QueryBuilder qb = matchAllQuery();
//        SearchResponse scrollResp = client.prepareSearch("twitter")
////                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
////                .setScroll(new TimeValue(5000))
//                .setQuery(qb)
//                .setSize(100).execute().actionGet();
//
//        for (SearchHit hit : scrollResp.getHits().getHits()) {
//            System.out.println(hit.field("_id"));
//        }
//        client.close();
//    }
//
//}
