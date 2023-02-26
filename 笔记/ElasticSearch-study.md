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

