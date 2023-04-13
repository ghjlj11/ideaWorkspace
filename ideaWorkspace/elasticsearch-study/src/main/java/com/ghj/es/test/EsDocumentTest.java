package com.ghj.es.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghj.es.constant.EsClientUtil;
import com.ghj.es.pojo.User;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


/**
 * 文档的增删改查
 * @author 86187
 */
public class EsDocumentTest {
    public static void main(String[] args) throws Exception {
        // 连接es客户端
        EsClientUtil.connect( esClient -> {
            User user = new User("ghj", "man", 123);
            EsDocumentTest documentTest = new EsDocumentTest();
            // 创建文档
            //documentTest.createDocument(esClient, user);
            // 更新文档
            //documentTest.updateDocument(esClient, "1001");
            // 查询文档
            documentTest.queryDocument(esClient, "1003");
            // 删除文档
            //documentTest.deleteDocument(esClient, "1001");
        });
    }

    /**
     * 指定索引下 新建文档
     * @param esClient
     * @param user
     * @throws Exception
     */
    public void createDocument(RestHighLevelClient esClient, User user) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // 需要将对象转化为JSON字符
        String userJson = mapper.writeValueAsString(user);
        IndexRequest request = new IndexRequest();
        // 设置是哪个索引， 新增文档的id
        request.index("user2").id("1001");
        // 请求体中的参数， 文档的内容
        request.source(userJson, XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    /**
     * 更新文档
     * @param esClient
     * @param id
     * @throws Exception
     */
    public void updateDocument(RestHighLevelClient esClient, String id) throws Exception {
        UpdateRequest request = new UpdateRequest();
        // 设置index， 文档id
        request.index("user2").id(id);
        // 局部跟新， 需改的字段名和值
        request.doc(XContentType.JSON, "name", "lj", "sex", "woman");
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    /**
     * 查询文档
     * @param esClient
     * @param id
     * @throws Exception
     */
    public void queryDocument(RestHighLevelClient esClient, String id) throws Exception {
        GetRequest request = new GetRequest();
        // 设置index， 文档id
        request.index("user2").id(id);
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        System.out.println("source: " + response.getSource());
        System.out.println("response: " + response);
    }

    /**
     * 删除文档
     * @param esClient
     * @param id
     * @throws Exception
     */
    public void deleteDocument(RestHighLevelClient esClient, String id) throws Exception {
        DeleteRequest request = new DeleteRequest();
        // 设置index， 文档id
        request.index("user2").id(id);
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }
}
