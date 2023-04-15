package com.ghj.es.util;


import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 使用lambda接口设置请求体builder
 * @author 86187
 */
public interface EsSearchSourceBuilderTask {

    /**
     * 设置builder
     * @param builder
     */
    void setBuilder(SearchSourceBuilder builder);
}
