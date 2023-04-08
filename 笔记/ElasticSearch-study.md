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



> index

- 新建，使用`put`方式发送请求`http://www.ghjlj.cn:9200/shopping` 相当于创建了一个shopping的index

- 查询，使用`get`方式发送以上请求就是获取该索引的信息。
- 删除，使用`delete`发送则是删除该索引。

- 查询全部，使用`get`发送`http://www.ghjlj.cn:9200/_cat/indices?v`则是查看所有索引信息。



> 文档

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



- 条件查询  分页查询 查询自定字段  排序，`get`方式发送请求`http://www.ghjlj.cn:9200/shopping/_search`，带上请求体

  ```json
  // 表示查询cate时huawei的文档
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

  
