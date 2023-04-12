package com.ghj.es.test;

import com.ghj.es.constant.HttpHostConstant;
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
        RestHighLevelClient esClient = HttpHostConstant.getEsClient();

        // 关闭es客户端
        esClient.close();
    }
}
