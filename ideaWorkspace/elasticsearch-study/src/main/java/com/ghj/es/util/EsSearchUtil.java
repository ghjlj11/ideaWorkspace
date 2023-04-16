package com.ghj.es.util;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;
import java.util.Map;


/**
 * 为了减少查询时重复代码
 * @author 86187
 */
public class EsSearchUtil {

    /**
     * 打印响应数据
     * @param response
     */
    public static void printResponse(SearchResponse response){
        System.out.println("================================================");
        System.out.println(
                "调用方："
                        + Thread.currentThread().getStackTrace()[2].getClassName()
                        + "#"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + "(line:"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber() + ")");
        // 匹配数据
        SearchHits hits = response.getHits();
        System.out.println("took: " + response.getTook());
        System.out.println("isTimedOut: " + response.isTimedOut());
        System.out.println("total: " + hits.getTotalHits());
        System.out.println("MaxScore: " + hits.getMaxScore());
        System.out.println("========> hits:");
        for (SearchHit hit : hits) {
            // 高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(!highlightFields.isEmpty()){
                System.out.println("highlightFields: " + highlightFields);
            }
            System.out.println("id: " + hit.getId() + " ==>" + hit.getSourceAsString());
        }
    }

    /**
     * 获取 搜索请求
     * @param task
     * @return
     */
    public static SearchRequest getSearchRequest(EsSearchSourceBuilderTask task) throws Exception {
        // 请求
        SearchRequest request = new SearchRequest();
        // 可以设置多个index
        request.indices("user2");
        // 请求体构造器
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 配置构造器
        task.setBuilder(builder);
        request.source(builder);
        return request;
    }
}
