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

- 启动docker： systemctl start docker
- 停止docker： systemctl stop docker
- 重启docker：  systemctl restart docker
- 查看docker状态： systemctl status docker
- 查看docker信息： docker info
- 开机启动： systemctl enable docker
- 查看帮助文档 ： docker --help



## 镜像有关的命令

- 查看所有的镜像 ： docker images  
- 在远程仓库搜索对应的镜像 ： docker search hello-world  
- 删除镜像， 前提是有关的容器得删除 ： docker image rm 镜像的名字     或者      docker rmi 镜像名字或者id  （删除容器命令： docker container rm  容器的id ， 查询所有的容器 ： docker container ls -a）
- 直接删除镜像， 就算有相关的容器也删除镜像， 不会删除容器： docker rmi -f  镜像名字或者id [多个镜像名字或者id]
- 从远程仓库下载镜像：docker pull 镜像的名字 [: TAG（版本号，默认是最新的）] 
- 查看镜像，容器数据卷所占空间大小 ： docker system df
- 创建容器 ： docker run 镜像名称

> 使用docker来创造一个ubuntu系统

首先拉镜像， 然后run， 普通的run不会有终端出来， 我们不好操作， 可以使用： `docker run -it ubuntu bash`  ， `-it`表示使用交互式的模式运行， 并且分配一个伪终端， 我们通过这个伪终端就可以在docker里边找到我们对应的容器， `bash`就表示使用bash当作终端。

使用`docker run -it --name=ghj ubuntu bash` ，可以指定容器的名字

现在就可以使用Linux命令

需要推出只要 exit命令



## 容器相关



- 新建容器 ：docker run image

- 启动之前停止的容器 ： docker start 容器id或者名字

- 退出容器 ： exit 直接退出容器， ctrl + p+ q 退出实例， 但是容器不退出。

- 重启容器 ： docker restart 容器id或者名字

- 强制停止容器 ： docker kill 容器id或者名字

- 删除已经停止的容器 ： docker rm 容器id或者名字

- 强制删除容器 ： docker rm -f 容器id或者名字

- 以守护模式，就是后台运行创建容器： docker run -d redis:6.0.8

- 查看容器的日志 ： docker logs 容器id或者名字

- 重新进入容器中， 并以交互模式进入： 

  - docker exec -it 容器id或名字 bash，这个使用exit退出时不会终止容器（推荐）。

  - docker attach 容器id或者名字在， 这个使用attach退出时会终止容器。

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

    

- 将容器内的文件复制到主机里面 ： docker cp 容器的id  容器内的路径  目的主机的路径

- 将容器导出成一个tar文件到当前目录下 ： docker export 容器id > 打包后的文件名.tar
- 将打包好的tar文件导入docker为镜像， 然后run这个镜像之后， 就会得到和之前被打包一样的容器，里边的文件也都存在： cat 文件名.tar | docker import - 镜像用户/镜像名：镜像版本号



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

- 然后以守护进程模式开启 ： `docker run -d -p 5000:5000 -v /zzyyuse/myregistry/:/tmp/registry --privileged=true registry`

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
