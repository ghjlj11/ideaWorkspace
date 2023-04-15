package com.ghj.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * 测试连接es客户端
 * @author 86187
 */
public class EsClientTest {
    public static void main(String[] args) throws IOException {
        // 创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("www.ghjlj.cn", 9200, "Http"))
        );

        // 关闭es客户端
        esClient.close();
    }
}
