package com.ghj.es.constant;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 工具类
 * @author 86187
 */
public class EsClientUtil {
    /**
     * 获取es客户端连接
     * @return
     */
    public static void connect(EsTask task) throws Exception{
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("www.ghjlj.cn", 9200, "Http"))
        );
        try{
            task.doSomeThing(esClient);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            esClient.close();
        }
    }
}
