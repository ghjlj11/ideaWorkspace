# Nginx-study

nginx的作用：反向代理， 负载均衡， 动静分离。



> 反向代理

正向代理：当一个局域网内的客户端想要访问外网， 那么就需要代理服务器，因为直接访问是访问不了的， 需要先访问代理服务器， 进而访问目标网站。



反向代理：

通过代理服务器，客户端访问服务器并不能直接访问， 而是先访问反向代理服务器， 通过反向代理服务器来访问服务端， 需要在服务器端进行配置，而不是对客户端进行配置， 客户端不需要做任何操作。



> 负载均衡

在多台服务器运行同一个服务的情况下， 客户端通过访问nginx，进而访问服务器， 而不是客户端直接访问单个服务器，实现了服务器的分压，负载均衡。



> 动静分离

将动态与静态的请求分开。项目中会有很多静态资源例如：html，css；也会有动态资源：jsp， servlet。如果都部署在一个服务器， 那么无论访问动态还是静态资源， 都是访问这个服务器， 使用动静分离的做法， 把静态资源放在一个服务器， 动态放在别的服务器， 因为这两种资源加载速度是不一样， 对服务器的性能需求也就不一样， 动静分离管理有利于服务器的分压。



## 基本命令



> 在nginx执行脚本的目录下：/usr/local/nginx/sbin

- 查看nginx版本号 ： `./nginx -v`
- 启动nginx：`./nginx`。
- 关闭nginx：`./nginx -s stop`
- 重新加载nginx：`./nginx -s reload`，当修改nginx.conf后， 可以不需要重启，重新加载新的配置文件。



## 配置文件



> 全局块



例如：

```bash
# 表示nginx处理并发数量， 值越大， 处理并发量越多
worker_processes  1;
```



> events块



例如：

```bash
# 表示nginx最大可以接收多少个连接
worker_connections  1024;
```





> http块

大部分配置是在http块配置

```bash
# 表示外部访问nginx的端口号以及ip地址
listen       80;
server_name  localhost;
```





## 反向代理



首先准备好tomcat并启动， 配置反向代理只需要在http配置块下加上一个属性：

```bash
# 在这个localtion下加上 proxy_pass http://localhost:8080;
location / {
root   html;
index  index.html index.htm;
}

# 即：
location / {
root   html;
proxy_pass http://localhost:8080;
index  index.html index.htm;
}
```



`proxy_pass`属性表示nginx反向代理的IP地址以及端口号， tomcat服务的端口就是 8080



保存后重新加载 配置， 然后访问 nginx  `43.142.32.254:80`， 就访问到了tomcat的首页。



> 配置代理多个tomcat

可以使用docker开启多个tomcat， 在每个tomcat的webapp下都建一个包， 第一个建/mypage， 第二个/mypage2， 包里都放一个jj.html， 并且测试是否可以访问下面的html。



- nginx.conf的http配置快上加上一个server配置，表示监听9001端口。

```bash
    server {
        listen       9001;
        server_name  localhost;
		# 表示url含有/mypage/则匹配该路径
		# 访问 43.142.32.254:9001/mypage/jj.html
		# 真实访问路径 ： http://43.142.32.254:9001/mypage/jj.html
        location ~/mypage/ {
            proxy_pass http://localhost:8080;
        }
        # 表示url含有/mypage2/则匹配该路径
        location ~/mypage2/ {
            proxy_pass http://localhost:8081;
        }
    }

```



**location后面可以接 =， ~， ~*，^~**

- 后面什么都不接时， 匹配以指定模式开头的location。

- `=` 表示不使用正则表达式匹配， 需要请求字符串与uri严格匹配才能继续往下搜索。
- `~`表示使用正则表达式， 并且区分大小写。
- `~*`表示使用正则表达式， 不区分大小写。
- `^~` 表示不包含正则表达式的uri前，功能和不加符号的一致，唯一不同的是，如果模式匹配，那么就停止搜索其他模式了

> location里的index与root

- index后面接的就是访问的首页， 如果有多个， 那么就依次寻找，找到了就展示首页。
- root就代表， 如果root的路径加上location的`/`后的路径可以在本地文件中匹配到，那么就会去找该位置对应的文件， 而不是去服务里面找。



> #### uri与location匹配

##### 场景一、

nginx配置：

```bash
location /test/ {
   proxy_pass http://127.0.0.1:8088/;
}
123
```

请求地址：**http://127.0.0.1/test/api/findAll**
 实际上服务请求地址为：**http://127.0.0.1:8088/api/findAll**
 规则：location最后有"/“,proxy_pass最后有”/" 结果为 **proxy_pass + url中location最后一个斜线以后的部分**

------

##### 场景二、

nginx配置：

```bash
location /test {
   proxy_pass http://127.0.0.1:8088/;
}
123
```

请求地址：**http://127.0.0.1/test/api/findAll**
 实际上服务请求地址为：**http://127.0.0.1:8088//api/findAll**
 规则：location最后无"/“,proxy_pass最后有”/" 结果为 **proxy_pass + / + url中location最后一个斜线以后的部分**

------

##### 场景三、

nginx配置：

```bash
location /test/ {
   proxy_pass http://127.0.0.1:8088;
}
123
```

请求地址：**http://127.0.0.1/test/api/findAll**
 实际上服务请求地址为：**http://127.0.0.1:8088/test/api/findAll**
 规则：location最后有"/“,proxy_pass最后无”/" 结果为 **proxy_pass + location + url中location后面的部分(不包含第一个/)**

------

##### 场景四、

nginx配置：

```bash
location /test {
   proxy_pass http://127.0.0.1:8088;
}
123
```

请求地址：**http://127.0.0.1/test/api/findAll**
 实际上服务请求地址为：**http://127.0.0.1:8088/test/api/findAll**
 规则：location最后无"/“,proxy_pass最后无”/" 结果为 **proxy_pass + location + “/” + url中location后面的部分(不包含第一个/)**





## 负载均衡



nginx配置：

```bash
	upstream myserver{
       server localhost:8080;
       server localhost:8081;
    }
    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            proxy_pass http://myserver;
            index  index.html index.htm;
        }
	}
```



在每个tomcat下都放一个mypage文件夹， 下面都有一个jj.html， 配置好后重新加载nginx配置， 访问jj.html就会采用轮询的负载均衡。



### 负载均衡策略



> 轮询（默认）

每个请求按时间顺序逐一分配到不同的后端服务器， 如果后端服务器down了， 它可以自动剔除。



> weight

代表权重， 默认是1 ，权重越高分配的次数越多。

```bash
	upstream myserver{
       server localhost:8080 weight 1;
       server localhost:8081 weight 2;
    }
```



> ip_hash

根据访问的ip来判断是访问哪一个服务器， 也就是说只要用的同一台电脑访问， 就只能访问一台服务器， 这样可以解决session问题， 配置只需要加上ip_hash即可。

	upstream myserver{
	   ip_hash;
	   server localhost:8080;
	   server localhost:8081;
	}



> fair

根据请求的响应时间来分配， 当请求发起， 会查看访问哪个服务器响应的时间最短， 那么就使用该台服务器



	upstream myserver{
	   fair;
	   server localhost:8080;
	   server localhost:8081;
	}



## 动静分离

将静态请求交给nginx处理， 直接访问对应的文件， 动态请求就交给对应的服务器处理。



配置， 将之前的server修改， root就代表， 如果root的路径加上location的`/`后的路径可以在本地文件中匹配到，那么就会去找该位置对应的文件， 而不是去服务里面找。

```bash
    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            proxy_pass http://myserver;
            index  index.html index.htm;
        }
        location /www/ {
            root    /mydata/;
            index  index.html index.htm;
        }
        location /image/ {
            root  /mydata/;
            autoindex  on;
        }
	}
```



 测试url， http://43.142.32.254/image/a.png 与  http://43.142.32.254/www/jj.html后， 可以直接访问`/mydata/www/jj.html`， `/mydata/image/a.png`.  此外，我们还可以访问http://43.142.32.254/image/， 看到该文件夹下的所有文件， 因为配置了  `autoindex  on;`





## 高可用（集群）

nginx也会有宕机的情况， 一旦宕机那么请求无法被处理， 因此也需要集群



提前准备 ： 首先需要准备两个nginx， 以及两台机器都装上 keepalived ， 直接使用yum命令安装`yum install keepalived -y`



1、使用docker运行一个nginx

2、在容器内使用`apt-get`安装`keepalived` ：需要先执行以下命令：

```bash
apt-get update

apt-get install libssl-dev 

apt-get install openssl 

apt-get install libpopt-dev 

apt-get install libnl-3-dev

apt-get install libnl-genl-3-dev

apt-get install keepalived
```



3、检查keepalived是否安装成功

```bash
keepalived -v
```





4、修改keepalived配置文件 `/ect/keepalived`

```bash
! Configuration File for keepalived

global_defs {
   notification_email {
     acassen@firewall.loc
     failover@firewall.loc
     sysadmin@firewall.loc
   }
   notification_email_from Alexandre.Cassen@firewall.loc
   smtp_server 10.0.12.17
   smtp_connect_timeout 30
   router_id LVS_DEVEL
   vrrp_skip_check_adv_addr
   vrrp_strict
   vrrp_garp_interval 0
   vrrp_gna_interval 0
}
# 检测脚本
vrrp_script chk_http_port {

    script  "/usr/local/src/nginx_check.sh"

    interval  2    #检测脚本执行时间的间隔

    weight 2

}

vrrp_instance VI_1 {
    state MASTER  # 备份服务器就是BACKUP，主服务器MASTER
    interface eth0
    virtual_router_id 51
    priority 100  # 优先级， 一般主机100， 从机90
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        10.0.12.16  #虚拟地址
        #192.168.200.17
        #192.168.200.18
    }
}

```



并且在`/usr/local/src`该路径下放置一个脚本 ：

```sh
#!/bin/bash
A = 'ps -C nginx -no-header |wc -l'
if [ $A -eq 0 ];then
    /usr/local/nginx/sbin/nginx
    sleep 2
    if [ 'ps -C nginx --no-header |wc -l' -eq 0 ];then
        killall keepalived
    fi
fi
```



5、重启nginx， 启动keepalived `systemctl start keepalived.service`



6、测试， 访问虚拟路径， 也可以访问到nginx， 主机的nginx挂了也可以访问到从机。



## 原理



![1666017512(1)](img\nginx\1666017512(1).jpg)



查看nginx的进程，会有master以及worker两个进程，我们发送的请求是通过master发送给worker然后再转发大服务器， 进行处理。并且worker之间获取master的请求是通过争抢的方式， 并不是轮询



> 一个master多个worker的好处:

1、 利于使用热部署， 就是执行 `./nginx -s reload`，master在reload后，会把配置同步到不在处理请求的worker， 等到worker处理完之后， 也可以更新配置， 这样就不会有空档的时间。

2、每个worker都是一个独立的进程， 其中一个挂了， 其余的也可以正常工作。

3、设置多少个worker最合适， 一般选择与CPU数量相同最合适。



> 发送一个请求worker占用几个连接数

当收到请求， 可能是访问静态资源或者动态资源， 当访问本机的静态资源就直接是占用两个连接，一个是接收请求的时候的，另一个是响应的； 当访问的是动态资源 ，就可能需要再去连接一台服务器， 以及那台服务器的响应， 就是四个连接。
