# Docker-Study



## Docker的基本组成 

![d1](img\docker\d1.png)

**镜像 ：**docker镜像就类似java的类， 通过这个模板来创建服务， 通过这个镜像可以创建多个容器

**容器：** docker利用容器技术， 独立运行一个或者多个应用， 通过镜像来创建的， 有启动，停止， 删除， 等基本命令， 可以理解为一个Linux系统。

**仓库：** 存放镜像的地方， 分为共有仓库和私有仓库



## 安装Docker



**官网下载安装地址：** https://docs.docker.com/engine/install/centos/



**安装步骤：**

- 如果需要卸载旧版本执行：

```bash
 sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```



- 下载yum-utils， 并且配置仓库， 默认是使用docker的仓库， 国外的网站，有时候会很卡

```bash
 sudo yum install -y yum-utils

 sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

可以配置阿里云的仓库， 这样就会更快一点

```bash
yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```



- 安装docker引擎， 默认是安装最新版本

```bash
sudo yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin
```

- 启动docker

```bash
sudo systemctl start docker
```

- 运行helloworld镜像， 这里我们本地没有这个镜像， 就会从远程仓库里边下载这个镜像然后运行。

```bash
sudo docker run hello-world
```



- 配置镜像加速器  阿里云， 登录阿里云， 点击控制台，然后菜单栏， 找到容器镜像服务，就有自己的加速器。

```bash
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://jvsvyjul.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```



## Docker基本命令

- 启动docker： `systemctl start docker`
- 停止docker： `systemctl stop docker`
- 重启docker： ` systemctl restart docker`
- 查看docker状态： `systemctl status docker`
- 查看docker信息：` docker info`
- 开机启动： `systemctl enable docker`
- 查看帮助文档 ： `docker --help`



## 镜像有关的命令

- 查看所有的镜像 ： `docker images`  
- 在远程仓库搜索对应的镜像 ： `docker search hello-world`  
- 删除镜像， 前提是有关的容器得删除 ： `docker image rm 镜像的名字`     或者      `docker rmi 镜像名字或者id`  
- 直接删除镜像， 就算有相关的容器也删除镜像， 不会删除容器： `docker rmi -f  镜像名字或者id [多个镜像名字或者id]`
- 从远程仓库下载镜像：`docker pull 镜像的名字 [: TAG（版本号，默认是最新的）] `
- 查看镜像，容器数据卷所占空间大小 ： `docker system df`
- 创建容器 ： `docker run 镜像名称`
- 将镜像打包成一个tar文件： `docker save -o 镜像名称或者id:版本号`

> 使用docker来创造一个ubuntu系统

首先拉镜像， 然后run， 普通的run不会有终端出来， 我们不好操作， 可以使用： `docker run -it ubuntu bash`  ， `-it`表示使用交互式的模式运行， 并且分配一个伪终端， 我们通过这个伪终端就可以在docker里边找到我们对应的容器， `bash`就表示使用bash当作终端。

使用`docker run -it --name=ghj ubuntu bash` ，可以指定容器的名字

现在就可以使用Linux命令

需要推出只要 exit命令



## 容器相关



- 新建容器 ：`docker run image`

- 启动之前停止的容器 ： `docker start 容器id或者名字`

- 退出容器 ：` exit `直接退出容器， `ctrl + p+ q `退出实例， 但是容器不退出。

- 重启容器 ： `docker restart 容器id或者名字`

- 强制停止容器 ： `docker kill 容器id或者名字`

- 删除已经停止的容器 ： ` docker container rm  容器的id `，`docker rm 容器id或者名字`

- 强制删除容器 ： `docker rm -f 容器id或者名字`

- 以守护模式，就是后台运行创建容器： `docker run -d redis:6.0.8`

- 查看容器的日志 ：**` docker logs 容器id或者名字`**

- 容器重命名： `docker rename 旧的容器名  新的容器名`

- 查询所有的容器 ： `docker container ls -a` 

- 查看正在运行的容器 ： `docker ps`

- 重新进入容器中， 并以交互模式进入： 

  - `docker exec -it 容器id或名字 bash`，这个使用exit退出时不会终止容器（推荐）。

  - `docker attach 容器id或者名字`， 这个使用attach退出时会终止容器。

  - 我们通过 `exec`进入到redis中，就可以直接按照redis来操作：

    ```bash
    [root@ghj-study ~]# docker exec -it 90ffdc3370e5 /bin/bash
    root@90ffdc3370e5:/data# ls       
    root@90ffdc3370e5:/data# redis-cli -p 6379
    127.0.0.1:6379> ping
    PONG
    127.0.0.1:6379> set k1 v1
    OK
    127.0.0.1:6379> lset k2 v2
    (error) ERR wrong number of arguments for 'lset' command
    127.0.0.1:6379> lpush k2 v2
    (integer) 1
    127.0.0.1:6379> lrange k2 0 -1
    1) "v2"
    127.0.0.1:6379> lpush k2 v4
    (integer) 2
    127.0.0.1:6379> lrange k2 0 -1
    1) "v4"
    2) "v2"
    
    ```

    

- 将容器内的文件复制到主机里面 ： **`docker cp 容器的id:容器内的路径  目的主机的路径`**

- 将外部文件复制到容器内：**`docker cp 宿主机的地址  容器id或名字:容器内路径`**

- 将容器导出成一个tar文件到当前目录下 ：` docker export 容器id > 打包后的文件名.tar`
- 将打包好的tar文件导入docker为镜像， 然后run这个镜像之后， 就会得到和之前被打包一样的容器，里边的文件也都存在： ` docker import - 镜像用户/镜像名：镜像版本号 < tar文件名.tar`



## commit

首先run一个ubuntu容器， 然后执行命令 `apt-get update` ， 更新apt， 然后我们下载vim编辑器，执行`apt-get install vim`， 然后提交我们的容器副本，执行命令`docker commit -m="提交信息" -a="作者" 容器id  需要创建的目标镜像的名字:[标签名]` ， 这样会生成一个带有vim编辑器的ubuntu镜像



> 将镜像推送到阿里云仓库

- 首先在阿里云上创建一个镜像仓库，然后按照仓库的命令执行就好了

> 私有的仓库

- 首先下载镜像 ： docker pull registry，查看镜像就会有

```bash
REPOSITORY                                      TAG       IMAGE ID       CREATED         SIZE
registry.cn-hangzhou.aliyuncs.com/atghj/ghjlj   1.1       58832d523150   5 days ago      179MB
```





172.17.0.6



> 私服库

- 首先pull一个私服库的镜像registry

- 然后以守护进程模式开启 ： `docker run -d -p 5000:5000 -v /zzyyuse/myregistry/:/tmp/registry --privileged=true registry` ， `-d`表示以守护进程模式开启， `-p`表示指定端口  宿主机的端口 ：容器内的端口， `-v`表示后面的路径下的文件宿主机与容器相互绑定，`--privileged=true` 表示以授予真正的root权限给当前的用户。 

- run一个ubuntu的镜像，然后生成的容器下载一个ifconfig ： `apt-get install net-tools`。

- 然后commit我们的镜像，生成一个新的镜像。

- 用curl命令查看我们的私服库有啥东西： `curl -XGET http://localhost:5000/v2/_catalog`

  刚开始是啥也没有 ：

  ```bash
  [root@ghj-study ~]# curl -XGET http://localhost:5000/v2/_catalog
  {"repositories":[]}
  ```

- 然后打包我们的镜像成一个新的镜像 ：`docker tag 镜像名称 私服库的地址(这里是localhost):端口号(5000)/打包后的镜像名称[:版本号]`

  ```bash
  REPOSITORY                                      TAG       IMAGE ID       CREATED         SIZE
  localhost:5000/ifconfig-ubuntu                  1.1       9f424136f809   3 hours ago     112MB
  
  ```

- 修改docker配置文件支持http： `vim /etc/docker/daemon.json` ，加上 `"insecure-registries":["localhost:5000"]` ，后面是私服库的地址以及端口号。

- 然后再推送我们新生成的镜像到私服库 ： `docker push 镜像名称:版本号`

- 然后查看私服库镜像就会有东西了 ： `[root@ghj-study ~]# curl -XGET http://localhost:5000/v2/_catalog
  {"repositories":["ifconfig-ubuntu"]}`

- 最后我们把之前的镜像删了，然后pull，测试新的镜像有没有`ipconfig`  ： `docker run -it localhost:5000/ifconfig-ubuntu:1.1 /bin/bash`  ，然后直接run，测试。



## 容器数据卷



我们docker跑的容器就是相当于一个个小系统，一些重要的文件也是存放在容器里面，当一不小心删除容器那就什么都没有了， 因此需要有可以备份数据的东西， 并且这个东西不应该是在docker容器里面，因此就有了容器数据卷，就是相当于在宿主机里面备份了关于对应容器的数据。



> 实例

- 我们首先启动一个ubuntu ： `docker run -it --privileged=true -v /tmp/docker-data/:/tmp/host-data/ --name=ghjlj ubuntu`， 这里的`--privileged=true` 表示使容器内的 root用户 拥有真正的root权限， `-v /tmp/docker-data/:/tmp/host-data/` ，`-v`表示生成容器卷， 后面的路径就是 宿主机对应的文件路径 : 容器对应的文件路径， 将这两个路径下的文件绑定， 就形成了容器数据的备份， `--name=ghjlj`表示给 这个容器自定义名字。
- 相互绑定了 后，只要是在 docke容器内修改了该路径下的内容，那么就会同步到宿主机的路径下，宿主机修改了也会同步到docker容器里面， 并且就算是docker容器 停止运行了， 期间宿主机的内容修改， 启动该容器后数据也会同步进来。
- 执行 `docker inspect 容器名称或者id`，可以看到对应的 容器的重要信息，其中就有

```bash
        "Mounts": [
            {
                "Type": "bind",
                "Source": "/tmp/docker-data",
                "Destination": "/tmp/host-data",
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            }
        ],

```

这就是与外部的宿主机对应的文件绑定的配置信息。



> 设置容器内只读

如果需要让容器内的相互绑定的文件只读， 那么只需要在启动容器时候容器绑定的路径上加上 `:ro`就可以，比如： `docker run --privileged=true -it -v /docker-con/ghj:/tmp/lj:ro --name ljlj ubuntu `

当我们在容器里面修改对应的文件就会报错：

```bash
root@3beb4d4685bd:/tmp/lj# touch a.txt
touch: cannot touch 'a.txt': Read-only file syste
```

但是在宿主机里面修改的文件依旧可以同步到容器里面。



> 容器卷之间的继承



当另一个容器想要与一个容器之间实现文件互传，那么可以使用 容器卷继承， 即一个容器继承另一个容器的容器卷，命令 ： `docker run -it --privileged=true --volumes-from ljlj --name=ghj ubuntu bash`，这里的`--volumes-from`其实就是以上的 `-v`命令的原型。



这样文件将会在 两个容器以及宿主机之间共享， 但是如果被继承的容器是只读权限， 那么继承而来的容器也是， 期间修改了文件 三方都会共享， 如果容器挂了，期间文件修改了， 重启之后依旧可以共享。 



## 安装软件



安装软件无非就是，先搜索镜像，然后拉下来， 然后run， 然后就好了。



> 安装tomcat

- 按照上面的步骤， 然后我没直接 run ： `docker run -d -p 8848:8080 --name u1 tomcat` ，然后通过本地的地址加上8848端口，就可以访问到 tomcat， 这里如果需要访问到tomcat首页， 需要先把tomcat的 webapps这个文件夹删了， 然后重命名webapps.dist为webapps。



> 安装mysql

- 拉下来镜像后直接run： `docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:tag`， `-e`代表环境配置。

- 进入到容器以后就执行 `mysql -uroot -p` ，然后直接输入密码，就可以进入到mysql。

  ```bash
  mysql> show databases
      -> ;
  +--------------------+
  | Database           |
  +--------------------+
  | information_schema |
  | mysql              |
  | performance_schema |
  | sys                |
  +--------------------+
  4 rows in set (0.00 sec)
  
  mysql> create database db01;
  Query OK, 1 row affected (0.00 sec)
  
  mysql> show databases
      -> ;
  +--------------------+
  | Database           |
  +--------------------+
  | information_schema |
  | db01               |
  | mysql              |
  | performance_schema |
  | sys                |
  +--------------------+
  5 rows in set (0.00 sec)
  
  mysql> use db01
  Database changed
  mysql> create table t1(id int, name varchar(200),age int);
  Query OK, 0 rows affected (0.03 sec)
  
  mysql> insert into t1(id, name, age) values(1, 'wert', 18);
  Query OK, 1 row affected (0.00 sec)
  
  mysql> select * from t1
      -> ;
  +------+------+------+
  | id   | name | age  |
  +------+------+------+
  |    1 | wert |   18 |
  +------+------+------+
  1 row in set (0.00 sec)
  
  
  ```


- 并且，根据主机号以及端口号， 我们可以使用navcat链接这个mysql进行操作。

- 我们使用这个命令查看会发现， 这个数据库不支持中文，插入中文会报错，字符集都是`latin1`：

  ```bash
  mysql> show variables like 'character%'
      -> ;
  +--------------------------+----------------------------+
  | Variable_name            | Value                      |
  +--------------------------+----------------------------+
  | character_set_client     | latin1                     |
  | character_set_connection | latin1                     |
  | character_set_database   | latin1                     |
  | character_set_filesystem | binary                     |
  | character_set_results    | latin1                     |
  | character_set_server     | latin1                     |
  | character_set_system     | utf8                       |
  | character_sets_dir       | /usr/share/mysql/charsets/ |
  +--------------------------+----------------------------+
  8 rows in set (0.00 sec)
  
  ```

- 使用下面的命令来启动mysql， 通过修改外部的容器数据卷，从而同步到mysql配置里面， 就可以修改字符集，实现支持中文插入：

  - 先跑起来mysql， 并且建立容器数据卷：

  ```bash
  docker run -p 3667:3306 -d --privileged=true -v /zzyyuse/mysql/log:/var/log/mysql -v /zzyyuse/mysql/data:/var/lib/mysql -v /zzyyuse/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 --name jj-mysql mysql:5.7
  ```

  - 然后在`/zzyyuse/mysql/conf`， 下面新建一个my.cnf的文件，并且编辑：

  ```bash
  [client]
  default_character_set=utf8
  [mysqld]
  collation_server = utf8_general_ci
  character_set_server = utf8
  ```

  - 然后重启容器就可以看到，现在字符集就是utf8，就可以支持中文插入， 如果期间重启不了容器， 那么可以查看容器的日志， 可能是配置文件写错了：

  ```bash
  mysql> show variables like 'character%';
  +--------------------------+----------------------------+
  | Variable_name            | Value                      |
  +--------------------------+----------------------------+
  | character_set_client     | utf8                       |
  | character_set_connection | utf8                       |
  | character_set_database   | utf8                       |
  | character_set_filesystem | binary                     |
  | character_set_results    | utf8                       |
  | character_set_server     | utf8                       |
  | character_set_system     | utf8                       |
  | character_sets_dir       | /usr/share/mysql/charsets/ |
  +--------------------------+----------------------------+
  8 rows in set (0.00 sec)
  
  
  ## 建完库以及表之后就可以去navicat插入中文， 这里直接在终端命令里面输入不了中文
  mysql> select * from t1;
  +------+--------+
  | id   | name   |
  +------+--------+
  |    1 | aa     |
  |    2 | 罗静   |
  +------+--------+
  
  
  ```
  
- **经过上面的容器数据卷备份后， 即使当前mysql容器被删除， 以之前的命令重启一个mysql容器， 只要数据卷里的数据还在， 那么mysql容器里的数据也就在**



> 安装redis

- 首先直接拉镜像， 然后run就可以， 启动之后会默认启动redis-server， 我们只需要启动redis-cli就可以：`docker run -d -p 6399:6379 redis:6.0.8`， 然后进入容器直接启动redis-cli：`redis-cli`。
- 配置redis.conf文件版本的安装：
  - 首先需要新建文件夹  `/mydata/redis/conf/ `， 然后去粘贴一个好的redis.conf文件到这里（最好是版本相同的redis.conf文件）， 注意要修改 `daemonize no`， 还有注释 `bind: 127.0.0.1` , 还有开启aof，`appendonly yes` ，注意版本问题。
- 直接运行redis ：`docker run -d -p 6399:6379 --name my-redis --privileged=true -v /mydata/redis/conf/redis.conf:/etc/redis/config/redis.conf -v /mydata/redis/data:/data  redis:7.0.0 redis-server /etc/redis/config/redis.conf`， 这里的`redis-server /etc/redis/config/redis.conf`表示使用这个目录下的配置文件启动redis。
- 如何证明我们使用了 我们自己的redis.conf，直接修改宿主机里面redis.conf， 然后会同步到容器里面， 修改一下redis数据库的数量 ， `databases 10` ，然后重启redis，选择第10个库：

```bash
root@849831de29de:/data# redis-cli
127.0.0.1:6379> select 9
OK
127.0.0.1:6379[9]> select 10
(error) ERR DB index is out of range

```

