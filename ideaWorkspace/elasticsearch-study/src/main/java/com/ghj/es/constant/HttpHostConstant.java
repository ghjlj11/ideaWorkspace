package com.ghj.es.constant;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 常量类
 * @author 86187
 */
public class HttpHostConstant {
    /**
     * 获取es客户端连接
     * @return
     */
    public static RestHighLevelClient getEsClient(){
         return new RestHighLevelClient(
                RestClient.builder(new HttpHost("www.ghjlj.cn", 9200, "Http"))
        );
    }
}
