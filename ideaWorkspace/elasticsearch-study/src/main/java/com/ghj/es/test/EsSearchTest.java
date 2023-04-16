package com.ghj.es.test;


import com.ghj.es.util.EsClientUtil;
import com.ghj.es.util.EsSearchUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.Min;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
import java.util.Map;


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
            // 模糊查询
            queryLike(esClient);
            // 高亮查询
            queryHighlight(esClient);
            // 聚合查询 最值，平均值
            queryMax(esClient);
            // 分组查询
            queryGroup(esClient);
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


    /**
     * 模糊查询，与MySQL不一样的是 模糊查询 mun 也可以查出 man
     * @param esClient
     * @throws Exception
     */
    public static void queryLike(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 构建模糊查询请求体
            FuzzyQueryBuilder fuzzyQueryBuilder = new FuzzyQueryBuilder("sex", "mun");
            // 设置允许有几个字符有差别
            fuzzyQueryBuilder.fuzziness(Fuzziness.TWO);
            builder.query(fuzzyQueryBuilder);
            // 设置查询指定字段，排除字段
            String[] includes = {"name"};
            String[] excludes = {};
            builder.fetchSource(includes, excludes);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }

    /**
     * 高亮查询
     * @param esClient
     * @throws Exception
     */
    public static void queryHighlight(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 设置多条件查询
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.must(QueryBuilders.termQuery("sex", "man"));
            boolQueryBuilder.must(QueryBuilders.termQuery("name", "ll"));
            builder.query(boolQueryBuilder);
            // 设置高亮字段
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            // 设置前缀标签
            highlightBuilder.preTags("<font color='red'>");
            // 设置后缀标签
            highlightBuilder.postTags("</font>");
            // 高亮字段， 高亮字段必须在查询条件中才可以高亮
            List<HighlightBuilder.Field> fields = highlightBuilder.fields();
            fields.add(new HighlightBuilder.Field("name"));
            fields.add(new HighlightBuilder.Field("sex"));
            // 构建高亮请求体
            builder.highlighter(highlightBuilder);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
    }


    /**
     * 聚合查询， 最值
     * @param esClient
     * @throws Exception
     */
    public static void queryMax(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 构建聚合查询请求体 maxAge为最大值输出的名称
            AggregationBuilder maxAggregationBuilder = AggregationBuilders.max("maxAge").field("age");
            // 最小值
            AggregationBuilder minAggregationBuilder = AggregationBuilders.min("minAge").field("age");
            // 平均值
            AggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avgAge").field("age");
            builder.aggregation(maxAggregationBuilder);
            builder.aggregation(minAggregationBuilder);
            builder.aggregation(avgAggregationBuilder);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
        System.out.println("response: " + response);
        // 获取响应中的值
        System.out.println("maxAge: " + ((Max)response.getAggregations().get("maxAge")).getValue());
        System.out.println("minAge: " + ((Min)response.getAggregations().get("minAge")).getValue());
        System.out.println("avgAge: " + ((Avg)response.getAggregations().get("avgAge")).getValue());
    }


    /**
     * 聚合查询， 分组查询
     * @param esClient
     * @throws Exception
     */
    public static void queryGroup(RestHighLevelClient esClient) throws Exception{
        // 查询请求
        SearchRequest request = EsSearchUtil.getSearchRequest(builder -> {
            // 构建分组请求体
            AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
            builder.aggregation(aggregationBuilder);
        });

        // 响应
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 打印匹配数据
        EsSearchUtil.printResponse(response);
        System.out.println(response);
    }


}
