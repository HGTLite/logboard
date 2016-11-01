package com.hgt.test;


import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TESTQuery {

    public static void main(String[] args) {

        Settings settings = Settings.builder()
                .put("cluster.name", "es-log")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.240"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.241"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.243"), 9300));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        AdminClient adminClient = client.admin();
//        IndicesAdminClient indicesAdminClient=adminClient.indices();

        ClusterAdminClient clusterAdminClient = adminClient.cluster();

        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        String clusterName = healths.getClusterName();
        int numberOfDataNodes = healths.getNumberOfDataNodes();
        int numberOfNodes = healths.getNumberOfNodes();
        System.out.println("clusterName-" + clusterName + " ;  numberOfDataNodes-" + numberOfDataNodes + " ;  numberOfNodes-" + numberOfNodes);

        for (ClusterIndexHealth health : healths.getIndices().values()) {
            String index = health.getIndex();
            int numberOfShards = health.getNumberOfShards();
            int numberOfReplicas = health.getNumberOfReplicas();

            System.out.println(" index-" + index + " ; numberOfShards-" + numberOfShards + " ; numberOfReplicas-" + numberOfReplicas);
        }

        client.close();

    }

}
