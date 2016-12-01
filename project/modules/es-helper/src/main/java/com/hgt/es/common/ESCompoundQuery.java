package com.hgt.es.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgt.converter.BeanMapConverter;
import com.hgt.es.config.ESConfig;
import com.hgt.es.tools.ESLogBean;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * README: es集群组合条件查询
 * Created by Yao on 11/28/16.
 * =============================================================================
 * CHANGELOG:
 */
public class ESCompoundQuery {

    public String hostList = "";
    public TransportClient client = null;
    IndicesAdminClient indicesAdminClient = null;

    public ESCompoundQuery(ESConfig esConfig) {
        this.client = esConfig.createESClient();
        AdminClient adminClient = client.admin();
        this.indicesAdminClient = adminClient.indices();
    }

    public ESCompoundQuery(String strClusterName, String strHost) {
        this.hostList = strHost;
        this.client = new ESConfig(strClusterName, strHost).createESClient();
    }

    /**
     * 根据ID查文档
     *
     * @param strIndex
     * @param strType
     * @param strId
     * @return
     */
    public ESLogBean queryDocById(String strIndex, String strType, String strId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        GetResponse response = client.prepareGet(strIndex, strType, strId).execute().actionGet();
        resultMap = response.getSourceAsMap();
        ESLogBean esLogBean = new ESLogBean();
        try {
            esLogBean = (ESLogBean) BeanMapConverter.convertMap2Bean(resultMap, ESLogBean.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //result.entrySet().stream().forEach(s -> {
        //System.out.println(s.getKey() + ":" + s.getValue());
        //});
//        System.out.println(esLogBean.toString());

        return esLogBean;
    }

    /**
     * 输入字符串全文搜索
     *
     * @param strIndex
     * @param strType
     * @param strWord
     * @return
     */
    public List<ESLogBean> simpleStringQuery(String strIndex, String strType, String strWord) {

        List<ESLogBean> resultList = new LinkedList<ESLogBean>();
        SearchRequestBuilder searchBuilder = client.prepareSearch(strIndex)
                .setTypes(strType)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setExplain(true);

        QueryBuilder qb;
        qb = queryStringQuery(strWord);
        searchBuilder.setQuery(qb);
        String qBody = strWord;

//        if (qBody.contains("queryContext")) {
//            qb = matchQuery(strBody, strBody);
//            searchBuilder.setQuery(qb);
//        }
//        if (qBody.contains("filterContext")) {
//            qb = matchQuery(strBody, strBody);
//            searchBuilder.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18));
//        }
//
//        if (qBody.contains("sizes")) {
//            searchBuilder.setFrom(0).setSize(60).setExplain(true);
//        }

        SearchResponse response = searchBuilder.execute().actionGet();
        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();

        for (SearchHit hit : hits) {
            String hitString = hit.getSourceAsString();
            if (hitString != null) {
                try {
                    ESLogBean esLogBean = mapper.readValue(hitString, ESLogBean.class);
                    resultList.add(esLogBean);
                    esLogBean = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    public List<ESLogBean> simpleStringQueryByPages(String strIndex, String strType, String strWord,int pageNum,int pageSize) {

        List<ESLogBean> resultList = new LinkedList<ESLogBean>();
        SearchRequestBuilder searchBuilder = client.prepareSearch(strIndex)
                .setTypes(strType)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(pageNum)
                .setSize(pageSize)
                .setExplain(true);

        QueryBuilder qb;
        qb = queryStringQuery(strWord);
        searchBuilder.setQuery(qb);
//        String qBody = strWord;

//        if (qBody.contains("queryContext")) {
//            qb = matchQuery(strBody, strBody);
//            searchBuilder.setQuery(qb);
//        }
//        if (qBody.contains("filterContext")) {
//            qb = matchQuery(strBody, strBody);
//            searchBuilder.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18));
//        }
//
//        if (qBody.contains("sizes")) {
//            searchBuilder.setFrom(0).setSize(60).setExplain(true);
//        }

        SearchResponse response = searchBuilder.execute().actionGet();
        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();

        for (SearchHit hit : hits) {
            String hitString = hit.getSourceAsString();
            if (hitString != null) {
                try {
                    ESLogBean esLogBean = mapper.readValue(hitString, ESLogBean.class);
                    resultList.add(esLogBean);
                    esLogBean = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

}
