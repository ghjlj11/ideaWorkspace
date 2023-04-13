package com.ghj.es.constant;


import org.elasticsearch.client.RestHighLevelClient;

/**
 * 使用lambda实现该接口，方便操作es客户端连接
 * @author 86187
 */
public interface EsTask {
    /**
     * 使用es客户端
     * @param esClient
     * @throws Exception
     */
    void doSomeThing(RestHighLevelClient esClient) throws Exception;
}
