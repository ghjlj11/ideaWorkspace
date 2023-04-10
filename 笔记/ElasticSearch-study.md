# ElasticSearch-study



## 下载安装

 

> 使用docker安装



- 首先拉镜像`docker pull elasticsearch:7.8.0`
- 然后需要在外部 建 一些文件夹

```bash
 mkdir -p /mydata/elasticsearch/config
 mkdir -p /mydata/elasticsearch/data/
 mkdir -p /mydata/elasticsearch/plugins

#新建文件
echo "http.host: 0.0.0.0" >> /mydata/elasticsearch/config/elasticsearch.yml

# 并且设置权限
chmod -R 777 /mydata/elasticsearch/plugins/
chmod -R 777 /mydata/elasticsearch/data/
chmod -R 777 /mydata/elasticsearch/config/
chmod -R 777 /mydata/elasticsearch/config/elasticsearch.yml 
```



- 启动镜像

```bash
docker run --name elasticsearch7.8.0 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /mydata/elasticsearch/data/:/usr/share/elasticsearch/data -v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins -d elasticsearch:7.8.0
```



> linux环境安装



下载地址：https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.8.0-linux-x86_64.tar.gz，下载之后解压，运行需要java环境，所以运行之前需要下载配置jdk。



下载完之后修改对应配置文件，然后启动，之后访问 本机ip:9200，就可以查看是否启动成功。

```yaml
http.port: 9200
transport.tcp.port: 9300
cluster.name: my-application
node.name: node-1
node.master: true
node.data: true
network.host: 127.0.0.1
cluster.initial_master_nodes: ["node-1"]
discovery.seed_hosts: ["127.0.0.1:9300"]
```







## 介绍

用于通过关键字快速查找文档，百度的搜索，等等。



![1680786150874](img\es\1680786150874.jpg)



>倒排索引

正常来说索引是通过对某一个字段的排序，然后快速查找这个字段，这是正排索引；

当使用key，value结构，把某一个关键字作为索引，然后value放的是包含该关键字的文档的id，这就是倒排索引。



## 基本操作



使用postman发送请求，对于不同请求方法的请求有不同的意义。



### index

- 新建，使用`put`方式发送请求`http://www.ghjlj.cn:9200/shopping` 相当于创建了一个shopping的index

- 查询，使用`get`方式发送以上请求就是获取该索引的信息。
- 删除，使用`delete`发送则是删除该索引。

- 查询全部，使用`get`发送`http://www.ghjlj.cn:9200/_cat/indices?v`则是查看所有索引信息。



### 文档

- 新建，使用`post`方式发送请求`http://www.ghjlj.cn:9200/shopping/_doc`，并且需要加上请求体，请求体就是文档的内容，就会在shopping索引里面新增一个文档，id是随机生成的，重复请求时，因为id是随机的，所以会再次生成数据，id不一样就是。

  ```json
  // 请求体
  {
      "title": "华为",
      "price": 5699,
      "cate": "huawei"
  }
  ```

  

- 指定id新建，使用`post`方式发送请求`http://www.ghjlj.cn:9200/shopping/_doc/1111`，可以指定id为1111，当再次发送，版本号会更新。

- 查询，使用`get`方式发送请求`http://www.ghjlj.cn:9200/shopping/_doc/1111`， 就可以查询id为1111的数据，未找到该id则没有数据。

- 查询全部，使用`get`方式发送请求`http://www.ghjlj.cn:9200/shopping/_search`，可以查看该索引下全部文档信息。

- 更新文档， 使用`post`方式发送请求`http://www.ghjlj.cn:9200/shopping/_update/1111`，加上请求体，需要更新什么，就在请求体里加什么。

  ```json
  {
      "doc": {
      "title": "小米"
      }
  }
  ```

- 删除文档，使用`delete`方式发送请求`http://www.ghjlj.cn:9200/shopping/_doc/1111`，就可以删除指定id的文档。



- 条件查询  分页查询 查询自定字段  排序，`get`方式发送请求`http://www.ghjlj.cn:9200/shopping/_search`，根据不同的请求体表示查询

  ```json
  // 表示查询cate是huawei的文档
  {
      "query": {
          "match": {
              "cate": "huawei"
          }
      }
  }
  
  // 表示全查询
  {
      "query": {
          "match_all": {
              
          }
      }
  }
  
  // 分页查询 从第0个元素开始，查询3个元素
  {
      "query": {
          "match": {
              "cate": "huawei"
          }
      },
      "from": 0,
      "size": 3
  }
  
  // 指定字段
  {
      "query": {
          "match": {
              "cate": "huawei"
          }
      },
      "from": 0,
      "size": 3,
      "_source": ["title", "price"]
  }
  
  // 排序 价格降序
  {
      "query": {
          "match": {
              "cate": "huawei"
          }
      },
      "from": 0,
      "size": 3,
      "_source": ["title", "price"],
      "sort": {
          "price": {
              "order": "desc"
          }
      }
  }
  ```

- 多条件查询  or连接条件查询  范围查询， 使用`get`方式发送`http://www.ghjlj.cn:9200/shopping/_search`，根据不同请求体表示不同的查询

  ```json
  
  // 多条件查询， 使用and连接
  {
      "query": {
          "bool": {
              "must": [
                  {
                      "match": {
                          "cate": "huawei"
                      }
                  },
                  {
                      "match": {
                          "price": 5699
                      }
                  }
              ]
          }
      }
  }
  
  // 多条件查询 使用or连接
  {
      "query": {
          "bool": {
              "should": [
                  {
                      "match": {
                          "cate": "huawei"
                      }
                  },
                  {
                      "match": {
                          "price": 9999
                      }
                  }
              ]
          }
      }
  }
  
  // 根据price范围查询
  {
      "query": {
          "bool": {
              "should": [
                  {
                      "match": {
                          "cate": "huawei"
                      }
                  },
                  {
                      "match": {
                          "price": 3333
                      }
                  }
              ],
              // 过滤 price大于 6000的文档
              "filter": {
                  "range": {
                      "price": {
                          "gt": 6000
                      }
                  }
              }
          }
      }
  }
  ```

- 全文查询 完全匹配 高亮 ， 使用`get`方式发送请求`http://www.ghjlj.cn:9200/shopping/_search`，加上不同的请求体。

  ```json
  // 全文检索 与之前的条件查询一样， 但是其实会查询title包含 '小'以及'为'这两个字段的文档， 因为底层匹配其实是会把'小为'这两个字段拆开，然后进行倒排索引的查询。
  {
      "query": {
          "match": {
              "title": "小为"
          }
      }
  }
  
  // 完全匹配 需要使用match_phrase进行匹配， 表示title包含'华小'的文档， 这样就不会进行拆解， 就是直接进行倒排索引的查找。
  {
      "query": {
          "match_phrase": {
              "title": "华小"
          }
      }
  }
  
  // 高亮显示 当加上highlight属性，那么就会对fields中的字段的值与查询的 值匹配的值高亮展示 以下高亮展示的是 title中 的'华'
  {
      "query": {
          "match_phrase": {
              "title": "华"
          }
      },
      "highlight": {
          "fields": {
              "title": {}
          }
      }
  }
  ```

- 聚合查询，使用`get`发送``， 聚合查询

  ```json
  // 平均
  {
      "aggs": { // 聚合操作
          "price_avg": { // 名称随意
              "avg": { // 平均函数
                  "field": "price" // 求平均的字段
              }
          }   
      },
      "size": 0 // 表示不需要查询原文档的数据
  }
  
  // 分组操作
  {
      "aggs": { // 聚合操作
          "price_group": { // 名称随意
              "terms": { // 分组函数
                  "field": "price" // 分组的字段
              }
          }   
      },
      "size": 0
  }
  ```

  

### 映射

es中也有类似与mysql的表



> 创建映射关系

使用`post`方式发送`http://www.ghjlj.cn:9200/user/_mapping`，并且带上请求体，表示创建映射关系

```json
{
    "properties": {
        "name": {
            // 为text可以分词查询
            "type": "text",
            // 为true表示可以被索引
            "index": true
        },
        "sex": {
            // 为keyword表示必须全量查询
            "type": "keyword",
            "index": true
        },
        "tel": {
            "type": "keyword",
            // 为false表示不可以被索引
            "index": false
        }
    }
}
```

使用`get`方式则是查询该索引下的映射关系。

加入数据，使用接口`http://www.ghjlj.cn:9200/user/_create/1234`，请求体：

```json
{
    "title": "小米",
    "price": 3333,
    "cate": "xiaomi"
}

```



创建之后，然后通过索引查询，因为之前创建了映射关系，name字段可以分词查询，sex字段只能全量查询，tel则不可以被索引查询。





## JavaApi



创建一个maven模块，并添加依赖

```java
	<dependencies>
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>7.8.0</version>
        </dependency>
        <!-- elasticsearch 的客户端 -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.8.0</version>
        </dependency>
        <!-- elasticsearch 依赖 2.x 的 log4j -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.8.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.8.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.9</version>
        </dependency>
        <!-- junit 单元测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
```



### 测试连接es

```java
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
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HttpHostConstant.HOST, HttpHostConstant.PORT, HttpHostConstant.METHOD))
        );

        // 关闭es客户端
        esClient.close();
    }
}
```



### 创建索引

```java
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

```

