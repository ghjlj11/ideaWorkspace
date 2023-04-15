package com.ghj.es.test;


import com.ghj.es.util.EsClientUtil;
import com.ghj.es.util.EsSearchUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;


/**
 * 条件查询
 * @author 86187
 */
public class EsSearchTest {
    public static void main(String[] args) throws Exception {
        EsClientUtil.connect(esClient -> {
            // 全量查询
            queryAll(esClient);
            // 条件查询
            queryCondition(esClient);
            // 分页
            queryLimit(esClient);
            // 排序
            querySort(esClient);
            // 分组查询
            queryUnit(esClient);
            // 范围查询
            queryRange(esClient);
        });
    }

    /**
     * 查询全部文档
     * @param esClient
     * @throws Exception
     */
    public static void queryAll(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 查询全部
            builder.query(QueryBuilders.matchAllQuery());
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 条件查询
     * @param esClient
     * @throws Exception
     */
    public static void queryCondition (RestHighLevelClient esClient) throws Exception {
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 条件查询
            builder.query(QueryBuilders.termQuery("age", 22));
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 分页查询
     * @param esClient
     * @throws Exception
     */
    public static void queryLimit(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 分页
            builder.query(QueryBuilders.termQuery("age", 22));
            builder.from(0);
            builder.size(2);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 排序
     * @param esClient
     * @throws Exception
     */
    public static void querySort(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            builder.query(QueryBuilders.termQuery("sex", "man"));
            // 排序
            builder.sort("age", SortOrder.DESC);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 多个条件组合查询
     * @param esClient
     * @throws Exception
     */
    public static void queryUnit(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 构建请求体
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            // 必须包含
            boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "man"));
            // 一定不包含
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", 22));
            // 可能包含
            boolQueryBuilder.should(QueryBuilders.matchQuery("name", "kk"));
            // 将BoolQueryBuilder设置为builder的查询条件
            builder.query(boolQueryBuilder);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 范围查询
     * @param esClient
     * @throws Exception
     */
    public static void queryRange(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 构建请求体
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
            // 小于44
            rangeQuery.lte(44);
            // 大于22
            rangeQuery.gte(22);
            // 将RangeQueryBuilder设置为builder的查询条件
            builder.query(rangeQuery);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }


}
