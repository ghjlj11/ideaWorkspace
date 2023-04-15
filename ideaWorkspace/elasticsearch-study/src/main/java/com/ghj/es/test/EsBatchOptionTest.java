package com.ghj.es.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghj.es.util.EsClientUtil;
import com.ghj.es.pojo.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Arrays;


/**
 * 批量新增删除修改文档，不能批量查看
 * @author 86187
 */
public class EsBatchOptionTest {
    public static void main(String[] args) throws Exception {
        // 连接es客户端
        EsClientUtil.connect( esClient -> {

            EsBatchOptionTest batchOptionTest = new EsBatchOptionTest();
            // 批量新增
            batchOptionTest.batchInsert(esClient);
            // 批量删除
            //batchOptionTest.batchDelete(esClient);
            // 批量杂
            //batchOptionTest.batchMix(esClient);
        });
    }

    /**
     * 批量新增
     * @param esClient
     * @throws Exception
     */
    public void batchInsert(RestHighLevelClient esClient) throws Exception{
        User user1 = new User("ll", "man", 22);
        User user2 = new User("jj", "woman", 22);
        User user3 = new User("hh", "man", 44);
        ObjectMapper mapper = new ObjectMapper();
        // 批量操作请求
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest request1 = new IndexRequest().index("user2").id("1006").source(mapper.writeValueAsString(user1), XContentType.JSON);
        IndexRequest request2 = new IndexRequest().index("user2").id("1007").source(mapper.writeValueAsString(user2), XContentType.JSON);
        IndexRequest request3 = new IndexRequest().index("user2").id("1008").source(mapper.writeValueAsString(user3), XContentType.JSON);

        // 添加请求
        bulkRequest.add(request1).add(request2).add(request3);

        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("items: " + Arrays.toString(response.getItems()));
        System.out.println("took: " + response.getTook());
    }

    /**
     * 批量删除
     * @param esClient
     * @throws Exception
     */
    public void batchDelete(RestHighLevelClient esClient) throws Exception{
        // 批量操作请求
        BulkRequest bulkRequest = new BulkRequest();
        DeleteRequest request1 = new DeleteRequest().index("user2").id("1003");
        DeleteRequest request2 = new DeleteRequest().index("user2").id("1004");
        DeleteRequest request3 = new DeleteRequest().index("user2").id("1005");

        // 添加请求
        bulkRequest.add(request1).add(request2).add(request3);

        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("items: " + response.getItems());
        System.out.println("took: " + response.getTook());
    }


    /**
     * 批量混合
     * @param esClient
     * @throws Exception
     */
    public void batchMix(RestHighLevelClient esClient) throws Exception{
        User user1 = new User("ll", "man", 22);
        User user2 = new User("jj", "woman", 33);
        ObjectMapper mapper = new ObjectMapper();
        // 批量操作请求
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest request1 = new IndexRequest().index("user2").id("1003").source(mapper.writeValueAsString(user1), XContentType.JSON);
        IndexRequest request2 = new IndexRequest().index("user2").id("1004").source(mapper.writeValueAsString(user2), XContentType.JSON);
        DeleteRequest request3 = new DeleteRequest().index("user2").id("1004");
        UpdateRequest request4 = new UpdateRequest().index("user2").id("1003").doc(XContentType.JSON, "name", "haha");

        // 添加请求
        bulkRequest.add(request1).add(request2).add(request3).add(request4);

        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("items: " + Arrays.toString(response.getItems()));
        System.out.println("took: " + response.getTook());
    }
}
