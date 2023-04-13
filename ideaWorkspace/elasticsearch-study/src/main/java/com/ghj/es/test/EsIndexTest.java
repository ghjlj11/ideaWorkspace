package com.ghj.es.test;

import com.ghj.es.constant.EsClientUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * 创建 查询 删除 索引
 * @author 86187
 */
public class EsIndexTest {
    public static void main(String[] args) throws Exception {
        // 连接es客户端
        EsClientUtil.connect( esClient -> {

            EsIndexTest indexTest = new EsIndexTest();

            indexTest.createIndex(esClient, "user2");
            // 查询索引
            //indexTest.getIndex(esClient, "user2");

            // 删除
            //indexTest.deleteIndex(esClient, "user2");
        });
    }

    /**
     * 查询索引
     * @param esClient
     * @throws Exception
     */
    public void getIndex(RestHighLevelClient esClient, String indexName) throws Exception {
        // 查询索引
        GetIndexRequest request = new GetIndexRequest(indexName);
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);

        // 响应
        System.out.println("aliases:"+response.getAliases());
        System.out.println("mappings:"+response.getMappings());
        System.out.println("settings:"+response.getSettings());
    }

    /**
     * 创建索引
     * @param esClient
     * @throws Exception
     */
    public void createIndex(RestHighLevelClient esClient, String indexName) throws Exception {
        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);

        // 响应
        boolean acknowledged = response.isAcknowledged();
        System.out.println("响应状态：" + acknowledged);
    }

    /**
     * 删除索引
     * @param esClient
     * @param indexName
     * @throws Exception
     */
    public void deleteIndex(RestHighLevelClient esClient, String indexName) throws Exception {
        // 创建索引
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        // 响应
        boolean acknowledged = response.isAcknowledged();
        System.out.println("响应状态：" + acknowledged);
    }
}
