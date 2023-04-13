package com.ghj.es.test;


import com.ghj.es.constant.EsClientUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 查询索引下全部数据
 * @author 86187
 */
public class EsAllSearchTest {
    public static void main(String[] args) throws Exception {
        EsClientUtil.connect(esClient -> {
            // 搜索请求对象
            SearchRequest request = new SearchRequest();
            // 可以查询多个索引下
            request.indices("user2", "user");
            // 搜索请求体构建
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // 全部查询
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            request.source(sourceBuilder);
            // 响应
            SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

            // 搜索返回的数据
            SearchHits hits = response.getHits();
            System.out.println(hits.getTotalHits());
            for (SearchHit hit : hits) {
                System.out.println("source: " + hit.getSourceAsString());
            }

            System.out.println(response);
        });
    }
}
