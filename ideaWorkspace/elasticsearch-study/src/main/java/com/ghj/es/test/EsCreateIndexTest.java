package com.ghj.es.test;

import com.ghj.es.constant.HttpHostConstant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * 测试es创建索引
 * @author 86187
 */
public class EsCreateIndexTest {
    public static void main(String[] args) throws IOException {
        // 创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HttpHostConstant.HOST, HttpHostConstant.PORT, HttpHostConstant.METHOD))
        );
        // 创建索引
        CreateIndexRequest user2 = new CreateIndexRequest("user2");
        CreateIndexResponse response = esClient.indices().create(user2, RequestOptions.DEFAULT);

        // 响应
        boolean acknowledged = response.isAcknowledged();
        System.out.println("响应状态：" + acknowledged);

        // 关闭es客户端
        esClient.close();
    }
}
