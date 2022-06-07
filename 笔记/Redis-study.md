# Redis-study



## 用于做缓存的NoSQL



- 当访问量过大的时候， 那么我们不可能都直接去访问数据库的信息， 数据库承受不了，我们可以有一个缓存数据库， 该数据库的特点就是读写特别快， 但是肯定会有一点缺点， 就是他是在内存中存储，  而不是在磁盘中， 内存中的话，只要电脑一关机， 服务器出点什么问题就没有了， 所以只能当缓存， 但是读写就是快， 因为是在内存里面，于是就有了**Redis**，  通过缓存技术来让我们的读写更加快， 然后经过指定时间， 缓存里的数据会存入到数据库里面， 这样我们的数据库就会减轻很多压力。

- **Redis是通过key-value的方式来存储的， value有多种数据类型。**



- 下载解压到Linux系统里面。



- 然后可以复制一份redis.conf到另一个地方 ，后面我们就通过这个conf来启动Redis， 这里面默认不是以守护进程的方式运行， 可以使用vim编辑文件的``daemonize no``改为yes。

redis.conf 配置项说明如下：

| 序号 | 配置项                                                       | 说明                                                         |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | `daemonize no`                                               | Redis 默认不是以守护进程的方式运行，可以通过该配置项修改，使用 yes 启用守护进程（Windows 不支持守护线程的配置为 no ） |
| 2    | `pidfile /var/run/redis.pid`                                 | 当 Redis 以守护进程方式运行时，Redis 默认会把 pid 写入 /var/run/redis.pid 文件，可以通过 pidfile 指定 |
| 3    | `port 6379`                                                  | 指定 Redis 监听端口，默认端口为 6379，作者在自己的一篇博文中解释了为什么选用 6379 作为默认端口，因为 6379 在手机按键上 MERZ 对应的号码，而 MERZ 取自意大利歌女 Alessia Merz 的名字 |
| 4    | `bind 127.0.0.1`                                             | 绑定的主机地址                                               |
| 5    | `timeout 300`                                                | 当客户端闲置多长秒后关闭连接，如果指定为 0 ，表示关闭该功能  |
| 6    | `loglevel notice`                                            | 指定日志记录级别，Redis 总共支持四个级别：debug、verbose、notice、warning，默认为 notice |
| 7    | `logfile stdout`                                             | 日志记录方式，默认为标准输出，如果配置 Redis 为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给 /dev/null |
| 8    | `databases 16`                                               | 设置数据库的数量，默认数据库为0，可以使用SELECT 命令在连接上指定数据库id |
| 9    | `save <seconds> <changes>` Redis 默认配置文件中提供了三个条件： **save 900 1** **save 300 10** **save 60 10000** 分别表示 900 秒（15 分钟）内有 1 个更改，300 秒（5 分钟）内有 10 个更改以及 60 秒内有 10000 个更改。 | 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合 |
| 10   | `rdbcompression yes`                                         | 指定存储至本地数据库时是否压缩数据，默认为 yes，Redis 采用 LZF 压缩，如果为了节省 CPU 时间，可以关闭该选项，但会导致数据库文件变的巨大 |
| 11   | `dbfilename dump.rdb`                                        | 指定本地数据库文件名，默认值为 dump.rdb                      |
| 12   | `dir ./`                                                     | 指定本地数据库存放目录                                       |
| 13   | `slaveof <masterip> <masterport>`                            | 设置当本机为 slave 服务时，设置 master 服务的 IP 地址及端口，在 Redis 启动时，它会自动从 master 进行数据同步 |
| 14   | `masterauth <master-password>`                               | 当 master 服务设置了密码保护时，slave 服务连接 master 的密码 |
| 15   | `requirepass foobared`                                       | 设置 Redis 连接密码，如果配置了连接密码，客户端在连接 Redis 时需要通过 AUTH <password> 命令提供密码，默认关闭 |
| 16   | ` maxclients 128`                                            | 设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of  clients reached 错误信息 |
| 17   | `maxmemory <bytes>`                                          | 指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的  Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key  存放内存，Value 会存放在 swap 区 |
| 18   | `appendonly no`                                              | 指定是否在每次更新操作后进行日志记录，Redis 在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为  redis 本身同步数据文件是按上面 save 条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为 no |
| 19   | `appendfilename appendonly.aof`                              | 指定更新日志文件名，默认为 appendonly.aof                    |
| 20   | `appendfsync everysec`                                       | 指定更新日志条件，共有 3 个可选值：  **no**：表示等操作系统进行数据缓存同步到磁盘（快）  **always**：表示每次更新操作后手动调用 fsync() 将数据写到磁盘（慢，安全）  **everysec**：表示每秒同步一次（折中，默认值） |
| 21   | `vm-enabled no`                                              | 指定是否启用虚拟内存机制，默认值为 no，简单的介绍一下，VM 机制将数据分页存放，由 Redis 将访问量较少的页即冷数据 swap 到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析 Redis 的 VM 机制） |
| 22   | `vm-swap-file /tmp/redis.swap`                               | 虚拟内存文件路径，默认值为 /tmp/redis.swap，不可多个 Redis 实例共享 |
| 23   | `vm-max-memory 0`                                            | 将所有大于 vm-max-memory 的数据存入虚拟内存，无论 vm-max-memory  设置多小，所有索引数据都是内存存储的(Redis 的索引数据 就是 keys)，也就是说，当 vm-max-memory 设置为 0  的时候，其实是所有 value 都存在于磁盘。默认值为 0 |
| 24   | `vm-page-size 32`                                            | Redis swap 文件分成了很多的 page，一个对象可以保存在多个 page 上面，但一个 page  上不能被多个对象共享，vm-page-size 是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page 大小最好设置为 32  或者 64bytes；如果存储很大大对象，则可以使用更大的 page，如果不确定，就使用默认值 |
| 25   | `vm-pages 134217728`                                         | 设置 swap 文件中的 page 数量，由于页表（一种表示页面空闲或使用的 bitmap）是在放在内存中的，，在磁盘上每 8 个 pages 将消耗 1byte 的内存。 |
| 26   | `vm-max-threads 4`                                           | 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4 |
| 27   | `glueoutputbuf yes`                                          | 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启 |
| 28   | `hash-max-zipmap-entries 64 hash-max-zipmap-value 512`       | 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法 |
| 29   | `activerehashing yes`                                        | 指定是否激活重置哈希，默认为开启（后面在介绍 Redis 的哈希算法时具体介绍） |
| 30   | `include /path/to/local.conf`                                | 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件 |



来自菜鸟教程。

**``redis-server ghjconfig/redis.conf``启动服务， ``redis-cli -p 6379 ``开启客户端， ``shutdown``关闭客户端， ``exit``退出服务，  这里客户端输入``ping``请求， 由回应``pong``请求就是开启服务成功。** 



- 可以使用``redis-benchmark``来进行压力测试**``redis-benchmark -h localhost -p 6379 -c 100 -n 100000``**， 开100个并发数，测试100000个请求
- 菜鸟的：

redis 性能测试工具可选参数如下所示：

| 序号 | 选项                      | 描述                                       | 默认值    |
| ---- | ------------------------- | ------------------------------------------ | --------- |
| 1    | **-h**                    | 指定服务器主机名                           | 127.0.0.1 |
| 2    | **-p**                    | 指定服务器端口                             | 6379      |
| 3    | **-s**                    | 指定服务器 socket                          |           |
| 4    | **-c**                    | 指定并发连接数                             | 50        |
| 5    | **-n**                    | 指定请求数                                 | 10000     |
| 6    | **-d**                    | 以字节的形式指定 SET/GET 值的数据大小      | 3         |
| 7    | **-k**                    | 1=keep alive 0=reconnect                   | 1         |
| 8    | **-r**                    | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
| 9    | **-P**                    | 通过管道传输 <numreq> 请求                 | 1         |
| 10   | **-q**                    | 强制退出 redis。仅显示 query/sec 值        |           |
| 11   | **--csv**                 | 以 CSV 格式输出                            |           |
| 12   | ***-l\*（L 的小写字母）** | 生成循环，永久执行测试                     |           |
| 13   | **-t**                    | 仅运行以逗号分隔的测试命令列表。           |           |
| 14   | ***-I\*（i 的大写字母）** | Idle 模式。仅打开 N 个 idle 连接并等待。   |           |

### 实例

以下实例我们使用了多个参数来测试 Redis 性能：

```
$ redis-benchmark -h 127.0.0.1 -p 6379 -t set,lpush -n 10000 -q

SET: 146198.83 requests per second
LPUSH: 145560.41 requests per second
```

以上实例中主机为 127.0.0.1，端口号为 6379，执行的命令为 set,lpush，请求数为 10000，通过 -q 参数让结果只显示每秒执行的请求数。



- 开启Redis服务与关闭，开启客户端与关闭，首先找到redis.conf对应的目录

```bash
redis-server redis.conf  	#开启服务
redis-cli -p 6379		#开启客户端
127.0.0.1:6379> ping		#测试链接
PONG
127.0.0.1:6379> SHUTDOWN		#关闭服务
not connected> exit


```





- Redis里面一共有16个数据库， 默认选择的是0号数据库， 我们可以通过``select index``切换

```bash
127.0.0.1:6379> dbsize
(integer) 6
127.0.0.1:6379> get ghj
"lovelj"
127.0.0.1:6379> get lj
"love ghj"
127.0.0.1:6379> select 3
OK
127.0.0.1:6379[3]> dbsize
(integer) 0
127.0.0.1:6379[3]> select 0 # 选择数据库
OK
127.0.0.1:6379> dbsize 	# 数据库大小
(integer) 6
127.0.0.1:6379[3]> flushdb  # 删库
OK
127.0.0.1:6379> dbsize
(integer) 0

```



删除所有数据库的内容 ``flashall``

## Redis是单线程



那么问题来了：

​		为什么Redis是单线程还这么快呢， 每秒可以读 110000次， 写81000次。

首先我们需要明白， 并不是多线程就一定会比单线程效率高， 多线程主要就是因为线程在操作磁盘资源的时候， 在使用到磁盘IO的情况下，因为IO会导致当前线程阻塞，单线程程序会因为IO而频繁阻塞，在这种情况下多线程模型效率会更高； 然而Redis的数据基本都是存储在内存里面 ，在内存中线程读取的速度是很快的， 也不会有磁盘的IO操作， 引入多线程反而会因为线程调度切换（上下文切换）影响性能。此外单线程还能避免锁的问题。



## Redis 的基本数据类型

Redis 主要有以下几种数据类型：

- `String` 字符串对象
- `Hash` 哈希Map对象
- `List` 列表对象
- `Set` 集合对象
- `ZSet` 有序集合

还有三种特殊数据类型:

- `geospatial`: Redis 在 3.2 推出 Geo 类型，该功能可以推算出地理位置信息，两地之间的距离。
- `hyperloglog`:基数：数学上集合的元素个数，是不能重复的。这个数据结构常用于统计网站的 UV。
- `bitmap`: bitmap 就是通过最小的单位 bit 来进行0或者1的设置，表示某个元素对应的值或者状态。一个 bit 的值，或者是0，或者是1；也就是说一个 bit 能存储的最多信息是2。bitmap 常用于统计用户信息比如活跃粉丝和不活跃粉丝、登录和未登录、是否打卡等。





### Redis-Key

Redis是通过key-value来存储的， **key就是String类型的**



```bash


 set name value



- 存入某个值到Redis里面，名字为name

 get name



- 通过名字获取值



 move name 1

- 移除某个值， 通过名字移除第一个值

 exists name

- 判断是否存在这个

 expire name number

- 设置某个值只存在几秒， number就是几秒。

 ttl name

- 查看死亡时间

 type key

- 查看当前值的类型
```



### String (字符串)

- **Redis 存储的value是String类型时。**

```bash
#########################################################################################
keys *  exists  append  strlen

127.0.0.1:6379> keys *  # 获取所有的key
1) "name"
127.0.0.1:6379> get name	#获取对应的value
"ghj"
127.0.0.1:6379> exists name		#判断是否存在name这个key
(integer) 1
127.0.0.1:6379> append name " hello"	#追加字符， 如果key不存在， 就相当于set了一个key
(integer) 9
127.0.0.1:6379> get name
"ghj hello"
127.0.0.1:6379> strlen name		#获取字符串的长度
(integer) 9
127.0.0.1:6379> APPEND name " lj"
(integer) 12
127.0.0.1:6379> strlen name
(integer) 12
127.0.0.1:6379> get name
"ghj hello lj"
127.0.0.1:6379> append va "ghj"		#相当于set了一个key
(integer) 3
127.0.0.1:6379> get va
"ghj"

```



- 关于数字value增加

```bash
#########################################################################################
incr  decr

127.0.0.1:6379> set view 0
OK
127.0.0.1:6379> get view
"0"
127.0.0.1:6379> incr view		# 自增
(integer) 1
127.0.0.1:6379> get view
"1"
127.0.0.1:6379> incr view
(integer) 2
127.0.0.1:6379> get view 
"2"
127.0.0.1:6379> decr view		#自减
(integer) 1
127.0.0.1:6379> get view
"1"

#########################################################################################
incrby  decrby

127.0.0.1:6379> incrby view view
(error) ERR value is not an integer or out of range
127.0.0.1:6379> incrby view 10		#每次增加固定值
(integer) 11
127.0.0.1:6379> decrby view 10		#每次减少固定值
(integer) 1

```



- 关于字符串的

```bash
#########################################################################################
getrange  setrange

127.0.0.1:6379> set k1 "hello ghj"
OK
127.0.0.1:6379> get k1
"hello ghj"
127.0.0.1:6379> getrange k1 1 4		#获取范围的字符串， 1-4，与Java不同的是他会获取最后的那个
"ello"
127.0.0.1:6379> getrange k1 0 -1	#end为-1， 就获取所有的。
"hello ghj"
127.0.0.1:6379> getrange k1 5 2		#如果end小于start那么就为""
""
127.0.0.1:6379> setrange k1 2 kk	#设置k1的value， 从2开始改变， 后面的就是value。
(integer) 9
127.0.0.1:6379> get k1		
"hekko ghj"
#########################################################################################
setex  setnx

127.0.0.1:6379> setex k2 30 "hello" 	#setex设置一个值然后再规定时间后过期
OK
127.0.0.1:6379> ttl k2
(integer) 24
127.0.0.1:6379> ttl k2
(integer) 21
127.0.0.1:6379> setnx k3 "redis" 	#setnx设置一个值， 不存在才会创建
(integer) 1
127.0.0.1:6379> keys *		#此时k2已经过期
1) "k3"
2) "k1"
127.0.0.1:6379> setnx k3 "kkk"		#setnx， 存在就创建失败，也不会修改。
(integer) 0
127.0.0.1:6379> get k3
"redis"
#########################################################################################
mset  mget

127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3		#同时设置多个值
OK
127.0.0.1:6379> keys *
1) "k3"
2) "k2"
3) "k1"
127.0.0.1:6379> get k1
"v1"
127.0.0.1:6379> msetnx k1 v1 k4 v4		#同上的， 如果不存在就创建， 存在就失败， 这里的k1存在 ，但是k4 不存在， 结果还是说失败了， 所以这是一个原子性的操作。
(integer) 0
127.0.0.1:6379> mget k1 k2 k3 		#同时获取多个值。
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> mset user:1:name ghj user:1:age 12 	#通过批量设置值， 给对象user:1设置name，age的值
OK
127.0.0.1:6379> mget user:1:name user:1:age		#批量获取对象user:1的值。
1) "ghj"
2) "12"
#########################################################################################
getset

127.0.0.1:6379> getset db redis 		#先获取值然后再set
(nil) 
127.0.0.1:6379> get db
"redis"
127.0.0.1:6379> getset db oracle
"redis"
```



### List(列表)

- **Redis存储的value类型是List时**

```bash
#########################################################################################
lpush   lrange

127.0.0.1:6379> lpush list one 		#向list链表存值
(integer) 1
127.0.0.1:6379> lpush list two 
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> lrange list 0 -1		#lrange， 与之前的range类似，取值的时候是从three开始，说明他是后进先出。
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> rpush list right		#rpush， 从右边插入， 这里可以理解为， 开始是从左边插入， 然后读取是从左边读取，所以是倒着读一个，而这里是从右边插入， 就是插在最底部。 
(integer) 5
127.0.0.1:6379> lrange list 0 -1		# 这里取出来的值就是right是在最后面的。
1) "2"
2) "three"
3) "two"
4) "one"
5) "right"
#########################################################################################
lpop， rpop

127.0.0.1:6379> lpop list		#这就是与push对应的pop， lpop就是在左边抛出一个， 就是头部。
"2"
127.0.0.1:6379> lrange list 0 -1	#抛出后值也会消失
1) "three"
2) "two"
3) "one"
4) "right"
127.0.0.1:6379> rpop list		#rpop就是在右边抛出， 就是抛出尾部，到现在我们就可以理解Redis里的List是可以实现两边插入， 两边移除， 底层可能是类似ArrayDeque， 也可能是类似LinkedList， 此时我们还不知道。
"right"
127.0.0.1:6379> lrange list 0 2
1) "three"
2) "two"
3) "one"
#########################################################################################
lindex

127.0.0.1:6379> lindex list 2		#获取指定下标的元素， lindex从0开始。
"one"
127.0.0.1:6379> lindex list 1
"two"
#########################################################################################
llen	
127.0.0.1:6379> llen list		#获取list的长度
(integer) 3
#########################################################################################
lrem 移除指定元素， 并可以限定个数

127.0.0.1:6379> lpush list three 		#这里可以看出， List里面是可以有重复元素的。
(integer) 4
127.0.0.1:6379> lrange list 0 -1 
1) "three"
2) "three"
3) "two"
4) "one"
127.0.0.1:6379> lrem list 1 one		#移除1个one
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "three"
3) "two"
127.0.0.1:6379> lrem list 2 three 		# 移除2 个three
(integer) 2
127.0.0.1:6379> lrange list 0 -1
1) "two"
#########################################################################################
ltrim 截取List

127.0.0.1:6379> rpush list hello0
(integer) 1
127.0.0.1:6379> rpush list hello1
(integer) 2
127.0.0.1:6379> rpush list hello2
(integer) 3
127.0.0.1:6379> rpush list hello3
(integer) 4
127.0.0.1:6379> ltrim list 0 2		#截取从0-2 的list， 其余的都删除
OK
127.0.0.1:6379> lrange list 0 -1
1) "hello0"
2) "hello1"
3) "hello2"
#########################################################################################
rpoplpush 在右边删除，然后在左边加上， 可以指定删除rpop的list与lpush的list
这个是没有 lpoprpush的， 更没有rpushlpop与lpushrpop

127.0.0.1:6379> lpush list h1
(integer) 1
127.0.0.1:6379> lpush list h2
(integer) 2
127.0.0.1:6379> lpush list h3
(integer) 3
127.0.0.1:6379> rpoplpush list list		#删除list的最右边的元素然后添加到list的最左边
"h1"
127.0.0.1:6379> lrange list 0 -1
1) "h1"
2) "h3"
3) "h2"
127.0.0.1:6379> lpush list2 k1 
(integer) 1
127.0.0.1:6379> lpush list2 k2
(integer) 2
127.0.0.1:6379> rpoplpush list list2		# 删除list的最右边的然后增加到list2的最左边。
"h2"
127.0.0.1:6379> lrange list2 0 -1
1) "h2"
2) "k2"
3) "k1"
127.0.0.1:6379> lrange list 0 -1
1) "h1"
2) "h3"
#########################################################################################
exists 判断是否存在
lset 通过List的下标设置对应的值

127.0.0.1:6379> exists list
(integer) 0
127.0.0.1:6379> lpush list v1
(integer) 1
127.0.0.1:6379> lset list 0 k1		#把下标为0值设置为 k1， 如果不存在list， 或者下标越界，则会报错。
OK
127.0.0.1:6379> lrange list 0 -1
1) "k1"
#########################################################################################
linsert 在指定元素的前面或者后面插入值

127.0.0.1:6379> lrange list 0 -1
1) "k3"
2) "k2"
3) "k1"
127.0.0.1:6379> linsert list before k2 ll	# 在k2之前插入ll
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "k3"
2) "ll"
3) "k2"
4) "k1"
127.0.0.1:6379> linsert list after k2 ll		# 在k2之后插入ll
(integer) 5
127.0.0.1:6379> lrange list 0 -1
1) "k3"
2) "ll"
3) "k2"
4) "ll"
5) "k1"

```



> List 小结

- List底层其实是一个链表， 双端链表。
- 如果不存在这个名字的列表， 那么push会创造一个新的List， 否则就是插入新的值。
- 如果List里的值全部被移除， 那么这个List就为空， 也就不存在了。
- 可以用来做消息队列， 也可以用来当作栈。



### Set(集合)

- **Redis存储的value类型是Set时**



就和java里的类似， 不可以有重复元素。

```bash
#########################################################################################
sadd 往set里面添加元素，如果没有就会创建一个set， 这里是不可以添加相同的元素的
smembers 查看set里面的所有元素
sismember 判断set里面是否存在某一个元素， 存在就返回1， 否则返回0


127.0.0.1:6379> sadd set k1		#添加元素
(integer) 1
127.0.0.1:6379> sadd set k2
(integer) 1
127.0.0.1:6379> sadd set k3
(integer) 1
127.0.0.1:6379> smembers set		#查看所有元素
1) "k3"
2) "k2"
3) "k1"
127.0.0.1:6379> sismember set k1		#判断某一个元素是否存在
(integer) 1
127.0.0.1:6379> sismember set k4
(integer) 0
#########################################################################################
scard 查看set的大小， 容量

127.0.0.1:6379> sadd set k1		#添加重复元素失败
(integer) 0
127.0.0.1:6379> smembers set		
1) "k3"
2) "k2"
3) "k1"
127.0.0.1:6379> scard set		# 查看set大小
(integer) 3
#########################################################################################
srem 移除指定元素

127.0.0.1:6379> srem set k1		#移除指定元素
(integer) 1
127.0.0.1:6379> smembers set
1) "k3"
2) "k2"
#########################################################################################
srandmember 随机获取指定个数的元素， 如果没有指定就是1个。
spop 随机删除一个元素

127.0.0.1:6379> sadd set k4
(integer) 1
127.0.0.1:6379> sadd set k5
(integer) 1
127.0.0.1:6379> srandmember set 
"k4"
127.0.0.1:6379> srandmember set 
"k2"
127.0.0.1:6379> srandmember set 
"k2"
127.0.0.1:6379> srandmember set 2
1) "k3"
2) "k2"
127.0.0.1:6379> spop set
"k3"
127.0.0.1:6379> smembers set
1) "k4"
2) "k5"
3) "k2"
#########################################################################################
smove 将指定set里的指定元素移除到指定的set里面， 和之前的srem是不一样的， srem是直接移除。

127.0.0.1:6379> smove set1 set2 k2
(integer) 1
127.0.0.1:6379> smembers set1
1) "k3"
2) "k1"
127.0.0.1:6379> smembers set2
1) "k2"
2) "ll"
#########################################################################################
sdiff 求多个set的差集， 第一个set里面的元素，没有出现在后面的set里的元素
sinter 求多个集合的交集， 所有的交集。
sunion 求多个集合的并集。

127.0.0.1:6379> sadd set1 a 
(integer) 1
127.0.0.1:6379> sadd set1 b
(integer) 1
127.0.0.1:6379> sadd set1 c
(integer) 1
127.0.0.1:6379> sadd set2 c
(integer) 1
127.0.0.1:6379> sadd set2 d
(integer) 1
127.0.0.1:6379> sadd set2 e
(integer) 1
127.0.0.1:6379> sdiff set1 set2		#求set1和set2的差集
1) "a"
2) "b"
127.0.0.1:6379> sinter set1 set2		# 求set1 与set2 的交集
1) "c"
127.0.0.1:6379> sunion set1 set2		# 求set1 与set2 的b
1) "e"
2) "c"
3) "b"
4) "a"
5) "d"

```



- 可以用来存储一些不能重复的事情， 比方说关注列表， 粉丝列表。



### hash（哈希）

- **Redis存储的value类型是map类型**

```bash
#########################################################################################
hset 创建或者存入指定的值到指定hash里面， 可以指定多个值， 但是hash只可以指定一个
hget 获取某一个指定的hash的某一个指定的key的值
hgetall 获取某一个指定的hash的所有的键值对
hmset 为指定的hash设置多个key-value键值对
hmget 获取多个指定的hash的value
hdel 删除指定的hash的指定的key-value。

127.0.0.1:6379> hset hash k1 v1 k2 v2
(integer) 2
127.0.0.1:6379> hget hash k1
"v1"
127.0.0.1:6379> hgetall hash
1) "k1"
2) "v1"
3) "k2"
4) "v2"
127.0.0.1:6379> hmset hash k3 v3 k4 v4 k5 v5
OK
127.0.0.1:6379> hmget hash k1 k2 k3 k4 k5
1) "v1"
2) "v2"
3) "v3"
4) "v4"
5) "v5"
127.0.0.1:6379> hdel hash k1
(integer) 1
127.0.0.1:6379> hget hash k1
(nil)
#########################################################################################
hlen 获取hash的长度；

127.0.0.1:6379> hlen hash
(integer) 4
#########################################################################################
hexists 判断指定hash的key是否存在， 存在就是返回1， 否则就是0.

127.0.0.1:6379> hexists hash k1
(integer) 0
127.0.0.1:6379> hexists hash k2
(integer) 1
#########################################################################################
hkeys 获取指定hash的所有的key
hvals 获取指定hash的所有的value

127.0.0.1:6379> hkeys hash
1) "k2"
2) "k3"
3) "k4"
4) "k5"
127.0.0.1:6379> hvals hash
1) "v2"
2) "v3"
3) "v4"
4) "v5"
#########################################################################################
hincrby 指定hash的key增长指定的值， 需要value是数字类型， 设置负数就是减少
hsetnx 往hash里存一个值， 如果不存在这个key就存入， 如果存在就失败。

127.0.0.1:6379> hset hash k6 5
(integer) 1
127.0.0.1:6379> hincrby hash k6 3
(integer) 8
127.0.0.1:6379> hsetnx hash k8 hello
(integer) 1
127.0.0.1:6379> hsetnx hash k8 ll
(integer) 0

```



- 可以用来存储一个对象， 对象的各种属性都是hash里的key-value。



### Zset（有序的Set）

与Set不同的是， 每个值有一个优先级，然后进行排序，  zadd  set score v1



```bash
#########################################################################################
zadd 创建一个zset， 并且往里面添加多个值， 如果存在这个zset就只是添加值， 这里还存在一个优先级
zrange 与之前的lrange一样， 范围输出zset里的值， 因为这个zset是有序的，里面的元素的位置已经固定好了， 就类似一个队列， 可以有这种范围输出的行为。


127.0.0.1:6379> zadd zset 1 k1
(integer) 1
127.0.0.1:6379> zadd zset 2 k2 3 k3		#添加多个值。
(integer) 2
127.0.0.1:6379> zrange zset 0 -2		#这里奇怪的是zrange 0 -2输出的只有两个值
1) "k1"
2) "k2"
127.0.0.1:6379> zrange zset 0 -1
1) "k1"
2) "k2"
3) "k3"
127.0.0.1:6379> zrange zset 0 -3		# 这里就可以猜测，0是最开始元素的下标， -1则是最后元素的下标， -2则是倒数第二个的下标， 以此类推。 
1) "k1"
127.0.0.1:6379> zrange zset 0 0 
1) "k1"
#########################################################################################
zrangebyscore 就是通过score来排序输出， 这个的范围就是需要排序的score的最小值， 与最大值， 只要score在这个范围里面那么就可以排序输出； 然后后面还可以带withscores 就是带上每个value的score输出


127.0.0.1:6379> zadd salary 2500 hong
(integer) 1
127.0.0.1:6379> zadd salary 5000 ming
(integer) 1
127.0.0.1:6379> zadd salary 3000 jun
(integer) 1
127.0.0.1:6379> zrangebyscore salary -inf +inf
1) "hong"
2) "jun"
3) "ming"
127.0.0.1:6379> zrangebyscore salary -inf +inf withscores
1) "hong"
2) "2500"
3) "jun"
4) "3000"
5) "ming"
6) "5000"
127.0.0.1:6379> zrangebyscore salary 0 3500 withscores		#只选择0 - 3500的score的进行排序输出
1) "hong"
2) "2500"
3) "jun"
4) "3000"

#########################################################################################
zrevrange 就是反向排序输出，和之前的zrange相反 
zrevrangebyscore 反向根据score排序输出， 就是之前的zrangebyscore的相反， 最大值在前面， 最小值在后面

127.0.0.1:6379> zrevrange salary 0 -1
1) "ming"
2) "jun"
3) "hong"
127.0.0.1:6379> zrevrangebyscore salary +inf -inf withscores
1) "ming"
2) "5000"
3) "jun"
4) "3000"
5) "hong"
6) "2500"
#########################################################################################
zcard 查看指定的zset的大小
zrem 移除指定的zset的指定元素

127.0.0.1:6379> zcard salary
(integer) 3
127.0.0.1:6379> zrem salary hong
(integer) 1
127.0.0.1:6379> zcard salary
(integer) 2
#########################################################################################
zcount 获取指定的score的区间里的元素个数

127.0.0.1:6379> zadd myset 1 k1
(integer) 1
127.0.0.1:6379> zadd myset 2 k2 3 k3
(integer) 2
127.0.0.1:6379> zcount myset 1 2
(integer) 2 
127.0.0.1:6379> zcount myset 1 3
(integer) 3
#########################################################################################
即使score相等， zset也可以排序输出

127.0.0.1:6379> zadd myset 2 k4
(integer) 1
127.0.0.1:6379> zrangebyscore myset 1 3
1) "k1"
2) "k2"
3) "k4"
4) "k3"

```



- 这个Zset就是set的排序版。
- 我们可以用在排序公司员工的工资， 排序每个人的成绩， 因为员工id与学生id都是唯一的
- 也可以用来对某个网站的热搜排序， 或者粉丝量， 访问量等等。



## 三种特殊的数据类型



### geospatial地理位置

**这里面就只有六个命令**



> ## geoadd 

- 向一个地理对象中添加地区，不存在对象则创建。
- 参数key值 （经度、纬度、名称） 名称

```bash
127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing  # 添加地理位置
(integer) 1
127.0.0.1:6379> geoadd china:city 121.47 31.23 shanghai
(integer) 1
127.0.0.1:6379> geoadd china:city 106.50 29.53 chongqin 114.05 22.52 shengzhen 	# 同时添加多个
(integer) 2
127.0.0.1:6379> geoadd china:city 120.16 30.24 hangzhou 108.96 34.26 xian
(integer) 2

```



> ## geopos

- 获取指定key的指定地理位置的经纬度

```bash
127.0.0.1:6379> geopos china:city shanghai 
1) 1) "121.47000163793563843"
   2) "31.22999903975783553"
127.0.0.1:6379> geopos china:city shanghai beijing chongqin		#获取多个地理位置的经纬度
1) 1) "121.47000163793563843"
   2) "31.22999903975783553"
2) 1) "116.39999896287918091"
   2) "39.90000009167092543"
3) 1) "106.49999767541885376"
   2) "29.52999957900659211"

```



> ## geodist

- 查看指定key里的两个地理位置的直线距离

- 单位：
  - **m** 表示单位为米。
  - **km** 表示单位为千米。
  - **mi** 表示单位为英里。
  - **ft** 表示单位为英尺。
  - 这里的单位默认就是**m**

```bash
127.0.0.1:6379> geodist china:city shanghai beijing
"1067378.7564"
127.0.0.1:6379> geodist china:city shanghai beijing km
"1067.3788"
127.0.0.1:6379> geodist china:city shanghai chongqin km
"1447.6737"

```



> ## georadius

- 获取指定的经纬度位置按指定的半径为圆内key里的所有位置

```bash
127.0.0.1:6379> georadius china:city 110 38 1000 km 		# 获取指定位置范围里的所有地理位置
1) "xian"
2) "chongqin"
3) "beijing"
127.0.0.1:6379> georadius china:city 110 38 500 km 
1) "xian"
127.0.0.1:6379> georadius china:city 110 38 500 km withdist
1) 1) "xian"
   2) "426.3396"
127.0.0.1:6379> georadius china:city 110 38 500 km withdist withcoord		#带距离以及经纬度输出
1) 1) "xian"
   2) "426.3396"
   3) 1) "108.96000176668167114"
      2) "34.25999964418929977"
127.0.0.1:6379> georadius china:city 110 38 1000 km withdist withcoord
1) 1) "xian"
   2) "426.3396"
   3) 1) "108.96000176668167114"
      2) "34.25999964418929977"
2) 1) "chongqin"
   2) "995.8944"
   3) 1) "106.49999767541885376"
      2) "29.52999957900659211"
3) 1) "beijing"
   2) "592.3892"
   3) 1) "116.39999896287918091"
      2) "39.90000009167092543"
127.0.0.1:6379> georadius china:city 110 38 1000 km withdist withcoord count 1		#限制只允许寻找出一个位置
1) 1) "xian"
   2) "426.3396"
   3) 1) "108.96000176668167114"
      2) "34.25999964418929977"

```



> ## georadiusbymember

- 获取指定位置附近指定范围的地理位置， 就是把上面的经纬度换成指定的位置

```bash
127.0.0.1:6379> georadiusbymember china:city beijing 500 km
1) "beijing"
127.0.0.1:6379> georadiusbymember china:city beijing 1000 km
1) "beijing"
2) "xian"
127.0.0.1:6379> georadiusbymember china:city shanghai 500 km
1) "hangzhou"
2) "shanghai"
127.0.0.1:6379> georadiusbymember china:city shanghai 1000 km
1) "hangzhou"
2) "shanghai"

```



> ## geohash

- 获取某些地理位置的hash， 由11个字符串组成

```bash
127.0.0.1:6379> geohash china:city beijing
1) "wx4fbxxfke0"
127.0.0.1:6379> geohash china:city beijing shanghai
1) "wx4fbxxfke0"
2) "wtw3sj5zbj0"

```



> ## geospatial 底层基于Zset实现

- 因此我们可以使用Zset的命令来操控他

```bash
127.0.0.1:6379> zrange china:city 0 -1		#查看所有的地理位置
1) "chongqin"
2) "xian"
3) "shengzhen"
4) "hangzhou"
5) "shanghai"
6) "beijing"
127.0.0.1:6379> zrem china:city beijing		#删除某个地理位置
(integer) 1
127.0.0.1:6379> zrange china:city 0 -1
1) "chongqin"
2) "xian"
3) "shengzhen"
4) "hangzhou"
5) "shanghai"

```



- 这个geospatial 可以用来定位， 生活中就会有很多的实例可以用他， 比如游戏区域排名， 微信的附近的人等等。



### Hyperloglog

- 用来做基数统计， 就是只允许不同的对象在这个里面， 这个基本只是用来计数的， 因为他的方法很少。可以不用set， 这个的占用内存会更小。

```bash
127.0.0.1:6379> pfadd mypf a b c d e f g h i		#添加数据
(integer) 1
127.0.0.1:6379> pfcount mypf			# 获取里面的数据的数量
(integer) 9
127.0.0.1:6379> pfadd mypf2 g h i j k l m n 
(integer) 1
127.0.0.1:6379> pfcount mypf2
(integer) 8
127.0.0.1:6379> pfmerge mypf3 mypf mypf2		#把两个合并成一个
OK
127.0.0.1:6379> pfcount mypf3		# 会自动删除重复的元素
(integer) 14
127.0.0.1:6379> pfadd mypf3 a		# 不能添加重复的元素
(integer) 0
127.0.0.1:6379> pfcount mypf3
(integer) 14

```



- 如果允许容错， 那么就一定可以使用Hyperloglog
- 如果不允许容错， 那么就使用set或者自己的数据类型即可。



### Bitmaps

- 这是一个存储元素只有1，0这两个值的map，可以用来表示两种状态， 比方说打卡，登录，签到等等

```bash
#########################################################################################
setbit 用来往Bitmaps里面放值， 值只有0 ，1

127.0.0.1:6379> setbit sign 0 1
(integer) 0
127.0.0.1:6379> setbit sign 1 1
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> setbit sign 3 0
(integer) 0
127.0.0.1:6379> setbit sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 0
(integer) 0
#########################################################################################
getbit 用来获取bitmap里的某个值

127.0.0.1:6379> getbit sign 2
(integer) 0
127.0.0.1:6379> getbit sign 4
(integer) 1
#########################################################################################
bitcount 统计里面所有的1的数量

127.0.0.1:6379> setbit sign 6 1
(integer) 0
127.0.0.1:6379> bitcount sign 
(integer) 4

```



## 事务

- **Redis的单条命令是保证原子性的， 但是Redis的事务并不保证原子性， 有可能其中一个命令执行失败了，但是其他的成功了**。
- **Redis的事务本质**：就是一组命令的集合， 一个事务中所有的命令都会被序列化， 在执行的过程中， 会按照顺序执行！ 一次性、顺序性、 排他性、执行一列的命令
- **Redis事务没有隔离级别的概念**
- 所有的命令并不会被直接执行， 只有发起执行命令的时候才会执行
- Redis的事务：
  - 开启事务（multi）
  - 命令入队（set....）
  - 执行事务（exec）

> 正常执行事务

```bash

127.0.0.1:6379> multi		#开启事务
OK
127.0.0.1:6379(TX)> set k1 v1 		#命令入队
QUEUED							# 进入队列
127.0.0.1:6379(TX)> set k2 v2 
QUEUED
127.0.0.1:6379(TX)> get k2
QUEUED
127.0.0.1:6379(TX)> set k3 v3
QUEUED
127.0.0.1:6379(TX)> exec		#执行事务
1) OK
2) OK
3) "v2"
4) OK

```



> 放弃事务

```bash
127.0.0.1:6379(TX)> set k1 l1 
QUEUED
127.0.0.1:6379(TX)> set k2 l2 
QUEUED
127.0.0.1:6379(TX)> set k4 v4
QUEUED
127.0.0.1:6379(TX)> discard		#放弃事务
OK
127.0.0.1:6379> get k4		#事务队列中的命令都不会被执行
(nil)

```



> 编译型异常，就是代码有问题， 命令有问题，事务中的左右命令都不执行

```bash
127.0.0.1:6379> multi		
OK
127.0.0.1:6379(TX)> set k3 v3 
QUEUED
127.0.0.1:6379(TX)> set k4 v4 
QUEUED
127.0.0.1:6379(TX)> sets k5 v5		#命令有问题直接提示
(error) ERR unknown command 'sets', with args beginning with: 'k5' 'v5' 
127.0.0.1:6379(TX)> set k6 v6 
QUEUED
127.0.0.1:6379(TX)> getset k7
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379(TX)> exec		#执行事务出问题
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k6		#获取不到k6.并没有执行前面的。
(nil)
127.0.0.1:6379> get k4
(nil)

```





> 运行时异常， 如果事务队列由语法型错误，那么执行的时候其他命令正常执行， 错误的命令抛出异常

```bash
127.0.0.1:6379> set k1 "v1"
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> set k2 v2 
QUEUED
127.0.0.1:6379(TX)> INCR k1		#自增字符串，运行时异常
QUEUED
127.0.0.1:6379(TX)> set k3 v3 
QUEUED
127.0.0.1:6379(TX)> get k3
QUEUED
127.0.0.1:6379(TX)> exec		#执行的的时候仅仅是一个命令有错，其他的正常执行
1) OK
2) (error) ERR value is not an integer or out of range
3) OK
4) "v3"
127.0.0.1:6379> get k2		#d
"v2"
127.0.0.1:6379> get k1
"v1"
127.0.0.1:6379> get k3
"v3"

```



> 监控！Watch

**悲观锁：**

- 做任何操作都要上锁

**乐观锁：**

- 认为什么时候都不会出问题， 不用上锁， 更新数据的时候进行判断，在此期间是否有人修改过数据
- 获取版本号version
- 更新的时候比较版本号version



> Redis测监视测试

正常执行成功

```bash
127.0.0.1:6379> set mo 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch mo 	#监视mo对象
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> decrby mo 20
QUEUED
127.0.0.1:6379(TX)> incrby out 20
QUEUED
127.0.0.1:6379(TX)> exec		#事务正常执行成功
1) (integer) 80
2) (integer) 20

```



- 开了多线程之后， 如果在监视的时期， 另外一个线程修改了里面对象的值，那么本线程事务就会提交失败。
- 此时如果要继续执行事务的话， 就要先放弃监视， 然后再重新监视， 然后继续执行事务。
- 这时Redis就可以利用watch实现乐观锁。



## Jedis



使用java来操作Redis

- 导入依赖

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.2.3</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.70</version>
        </dependency>


    </dependencies>
```



- 创建链接

```java
public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("43.142.32.254",6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.set("k1", "k2"));
        System.out.println(jedis.get("k1"));
    }
}

```



- 事务

```java
public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("43.142.32.254",6379);
        Transaction multi = jedis.multi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name", "ghj");
        String s = jsonObject.toJSONString();
        try {
            multi.set("user1",s);
            multi.set("user2",s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
        }
    }
}

```



## 整合SpringBoot



- 我们首先创建一个Spring的项目，建项目的时候勾选NoSQL的第一个Redis， 还有Web，主要时下面这个依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```



- 然后可以查看RedisAutoConfiguration的源码， 里面有一个RedisProperties的配置类， 我们只需要在yml文件里面用spring.redis后面的配置就可以配置这里面的属性， 注意SpringBoot集成Redis不是用的jedis， 而是lettuce

```yml
spring:
  redis:
    host: "43.142.32.254"
    port: 6379

```



- 我们就可以直接测试，直接注入一个RedisTemplate，就可以使用他的方法， 就跟之前的使用方法是一样的，  redisTemplate.opsForXXXX就可以对某一种类型进行操作， 但是前提需要把MyUser序列化， 或者用json来进行传递数据。

```java
@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() throws JsonProcessingException {
        MyUser myUser = new MyUser("郭欢军", 2);
        redisTemplate.opsForValue().set("me", myUser);
        System.out.println(redisTemplate.opsForValue().get("me"));
    }

}
```

但是这样进行传递，虽然java可以取到值，但是在命令行上看他的key就是有别的代码。

- 我们可以定义自己的RedisTemplate， 就可以让命令行的key变成正常的了

```java
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }


}
```



- 对于RedisTemplate操作总是有点繁琐， 我们可以写一个工具类， 把RedisTemplate封装进去， 就可以更简便的去使用。



## Redis.config详解

- 找到对应的redis.config文件，每个Redis服务都对应一个Redis.config这个配置文件， 使用vim命令进行编辑

```bash
protected-mode no 	#是否开启保护模式

port 6379 #端口号

################################ SNAPSHOTTING  ################################
关于RDB的一些操作

############################## APPEND ONLY MODE ###############################
关于AOF的一些操作
```





## Redis持久化



### RDB（Redis Database）



在指定的时间间隔将内存中的数据集快照写入磁盘，也就是Snapshot， 他恢复时是将快照文件直接读到内存



Redis会单独创建一个子进程来进行持久化， 会先将数据写入到一个临时的文件中， 待持久化过程都结束， 在用这个文件替换上次持久化的文件。整个过程主进程不进行IO操作， 确保了性能极高， 如果需要进行大规模数据的恢复，且对于恢复的完整性不是非常敏感， 那么RDB比AOF会更加高效， RDB的缺点就是最后一次的持久化后的数据可能会丢失，我们默认就是使用的RDB， 一般不需要修改这个配置。



- 我们通过修改配置文件的RDB对应的属性， 修改把之前的save注释， 然后加上`save 60 3` 代表60秒内执行了3次操作的话就会保存文件， 保存的文件名也可以配置  ， 默认是 `dbfilename dump.rdb`， 就是保存在dump.rdb文件里面， 我们首先删除这个文件， 然后操作几次。

```bash
#########################################################################################
先删除文件， 然后另一个线程执行几次跟新或者查询数据库的语句 ，就是set或者get，就会发现又有这个文件了。

[root@ghj-study ghjconfig]# ls 
dump.rdb  redis.conf
[root@ghj-study ghjconfig]# rm dump.rdb 
rm: remove regular file ‘dump.rdb’? y
[root@ghj-study ghjconfig]# ls
redis.conf
[root@ghj-study ghjconfig]# ls
dump.rdb  redis.conf

```



- 如果我们保存好了文件没有删除， 我们再来重启一下redis的服务器， 这时候按理来说里面应该是没有数据了， 因为存在缓存里面重启就没了， 但是我们进行了持久化， 这时候开启客户端又可以发现之前保存的那些key都还存在

```bash
127.0.0.1:6379> SHUTDOWN		#关闭服务
not connected> exit
[root@ghj-study ghjconfig]# redis-server redis.conf 		#开启服务与开启客户端
[root@ghj-study ghjconfig]# redis-cli -p 6379
127.0.0.1:6379> keys *
1) "k3"
2) "k1"
3) "k2"
127.0.0.1:6379> get k1			#依然可以获取里面的值
"v"

```



> 触发机制

- 满足save的条件
- 执行flushdb命令
- 退出redis

备份就会自动生成一个.rdb文件， 只要rdb文件在redis.config文件所在的目录下， 我们下次开启redis服务就会自动读取这个服务，恢复数据。



> **优点**

- 适合大规模的数据恢复
- 对数据的完整性要求不高

> **缺点**

- 需要一定的时间间隔进行操作， 如果redis意外宕机了， 最后一次的数据就没了
- fork进程的时候， 会占用一定的内存。



### AOF（Append Only File）

将我们所有的命令都记录下来（除了读的操作）， 恢复的时候就在执行一次所有的命令， 只许追加文件， 但是不可以修改文件， redis启动的时候就会读取文件， 重新构建数据， 就是再执行一次所有的命令，然后恢复数据。

我们首先查看一下配置文件。

```bash
appendonly no		#这里默认是关闭的

appendfilename "appendonly.aof"		#保存下来的文件名
appenddirname "appendonlydir"		#保存文件的文件夹

# appendfsync always
appendfsync everysec		#这里默认的配置是每秒钟都保存一次
# appendfsync no

```

- 只需要把`appendonly`改成yes即可

然后修改完配置文件只需要重启一下redis就可以了

```bash
127.0.0.1:6379> SHUTDOWN
not connected> exit
[root@ghj-study ghjconfig]# redis-server redis.conf 
[root@ghj-study ghjconfig]# redis-cli -p 6379
127.0.0.1:6379> ping
PONG

```

- 在另一个线程里面就可以查看到有对应的文件夹以及文件。

我们set几个值然后查看文件里的内容

```bash
^M
*3^M
$3^M
set^M
$2^M
k1^M
$2^M
v1^M
*3^M
$3^M
set^M
$2^M
k2^M
                
```



- 如果说我们的aof文件出错误了，我们重启redis服务就会失败 ， 这是我们需要找到`redis-check-aof` 这个文件去修复aof文件，下面的命令就可以了， 但是出错的那条语句会被删除， 就会导致少了一些数据

```bash
redis-check-aof --fix ghjconfig/appendonlydir/appendonly.aof.1.incr.aof
```



> **优点**

- 每次修改都同步， 文件的完整会更加好
- 每秒同步一次， 会丢失最后一秒的数据

> **缺点**

- 对于数据文件来说aof远远大于rdb， 修复速度也是很慢
- Aof运行的效率也比rdb慢得多， 因为Aof是文件读取操作IO操作，会比rdb慢得多



如果说同时开启了RDB与AOF那么数据恢复会默认使用AOF的文件



## Redis发布订阅



- 首先打开两个redis的服务端， 这个作为是关注公众号的人。

```bash
127.0.0.1:6379> subscribe ghjlj  	#关注某个频道
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "ghjlj"
3) (integer) 1
#等待接收信息
```



- 这个客户端就当作是公众号

```bash
127.0.0.1:6379> publish ghjlj "hi"		#往频道里面发布消息
(integer) 1
127.0.0.1:6379> publish ghjlj "hello"
(integer) 1

```



- 另一个客户端不需要执行任何操作， 就可以获得信息

```bash
127.0.0.1:6379> subscribe ghjlj
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "ghjlj"
3) (integer) 1
1) "message"		#消息#自动获取信息
2) "ghjlj"		#消息的频道
3) "hi"			#消息的内容
1) "message"
2) "ghjlj"
3) "hello"

```





## 主从复制

主从复制， 是指将一台Redis的服务器的数据复制到其他的Redis服务器。前者就是主节点（master），后者就是从节点（slave）；数据复制是单向的， 只能从主节点到从节点，master以写为主， slave以读为主。

主从复制，读写分离，可以减缓服务器的压力。

**默认情况下， 每个redis服务器都是一个主节点**

一个主节点可以有多个从节点， 但一个从节点只可以有一个主节点。

**主从复制的作用主要包括：**

1、数据冗余

2、故障恢复

3、负载均衡

4、高可用（集群）基石



### 环境配置

只需要配置从库， 不用配置主库

```bash
127.0.0.1:6379> info replication
# Replication
role:master					#当前角色， 主机
connected_slaves:0			#从机数量
master_failover_state:no-failover
master_replid:ef762559619e3000d6840889ef5d484d50161b7d
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0

```



#### 一主二从

- 首先复制三个redis.conf文件重命名， 然后vim修改里面的配置， 把端口号， 日志文件输出名，RDB对应的配置， 还有pidfile就好了，然后开启多个服务， 查看后台看有没有成功

```bash
[root@ghj-study ghjconfig]# ps -ef|grep redis
root      5784     1  0 22:20 ?        00:00:00 redis-server 0.0.0.0:6379
root      5896     1  0 22:20 ?        00:00:00 redis-server 0.0.0.0:6380
root      6191     1  0 22:21 ?        00:00:00 redis-server 0.0.0.0:6381
root      8124 32470  0 22:29 pts/0    00:00:00 grep --color=auto redis

```

- 一般情况我们只需要配置从机。在从机上使用命令就可以去认主机，把自己变成从机

```bash
127.0.0.1:6380> slaveof 127.0.0.1 6379		#去认老大， 地址与端口号
OK
127.0.0.1:6380> info replication			#查看当前服务的info
# Replication
role:slave									#自己的角色变成了从机
master_host:127.0.0.1						#主机地址
master_port:6379
master_link_status:up
master_last_io_seconds_ago:5
master_sync_in_progress:0
slave_read_repl_offset:14
slave_repl_offset:14
slave_priority:100
slave_read_only:1
replica_announced:1
connected_slaves:0
master_failover_state:no-failover
master_replid:84c4a8ea8e26a939244709ba7fcf4c9336d3c1ac
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:14
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:15
repl_backlog_histlen:0

#########################################################################################
再来查看一下主机的info

127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6380,state=online,offset=70,lag=0		#从机的信息
master_failover_state:no-failover
master_replid:84c4a8ea8e26a939244709ba7fcf4c9336d3c1ac
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:70
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:70



```



- 这样的主从配置只是暂时的，真实的是需要修改配置文件， 才可以永久的配置。关于主从复制的配置也在配置文件里面

```bash
################################# REPLICATION #################################

# replicaof <masterip> <masterport>   #配置主机的ip与端口号

# masterauth <master-password>		#主机的密码
```



**主机负责写， 从机负责读， 从机的数据会与主机同步， 从机不能写数据**

```bash
#########################################################################################
在主机中写入数据

127.0.0.1:6379> keys *		#主机可以正常的get与set
(empty array)
127.0.0.1:6379> set k1 v1
OK
#########################################################################################
从机中可以直接读取， 但是从机是不可以写入数据的

127.0.0.1:6380> keys *
1) "k1"
127.0.0.1:6380> get k1
"v1"
127.0.0.1:6380> set k2 v2
(error) READONLY You can't write against a read only replica.
```



- 期间就算主机宕机， 从机依旧可以获得数据， 如果主机重连了， 那么从机还会继续共享主机的数据。

```bash
#########################################################################################
主机断开重连，并设置值

127.0.0.1:6379> set k1 v1
OK
127.0.0.1:6379> SHUTDOWN
not connected> exit
[root@ghj-study ghjconfig]# redis-server redis79.conf 
[root@ghj-study ghjconfig]# redis-cli -p 6379
127.0.0.1:6379> keys *
1) "k1"
127.0.0.1:6379> set k2 v2
OK

#########################################################################################
从机依旧获得k2

127.0.0.1:6380> get k2
"v2"



```



- 如果从机宕机， 那么重连回来， 主机的数据依然可以共享，但是我们这里使用命令配置的主机， 所以重连回来还需要设置主机， 数据还是可以共享， 即使是在从机宕机的时候新增的数据依旧可以共享， 这说明， 只要新加了从机，那么他就会直接共享主机的数据， 即使他之前没有链接主机。

> 复制原理

- 全量复制：刚开始链接主机的时候， master就会发送一个sync命令， 从机就会全部复制里面的数据。
- 增量复制：连接之后， 主机新增的数据也会同步到从机里面， 就是增量复制。



#### 层层链路

这个模型，就与之前的不同， 他是第一个为主节点， 下一个是前一个的从节点， 就是三个的主节点是第二个节点， 中间的节点既是主节点， 也是从节点。



这样的话第一个主节点就只有一个从节点，第二个节点是最特殊的，他既是主节点也是从节点， 但是他的角色依旧是从节点 ，也就是只可以读。

```bash
127.0.0.1:6380> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6379
master_link_status:up
master_last_io_seconds_ago:9
master_sync_in_progress:0
slave_read_repl_offset:37670
slave_repl_offset:37670
slave_priority:100
slave_read_only:1
replica_announced:1
connected_slaves:1
slave0:ip=127.0.0.1,port=6381,state=online,offset=37670,lag=1
master_failover_state:no-failover
master_replid:fba2d2377bd3218fa78a061225f7eb862480a3f6
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:37670
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:11373
repl_backlog_histlen:26298

```



- 如果此时主节点宕机， 那第二个节点相当主节点的话，就要执行命令

```bash

127.0.0.1:6380> slaveof no one			#自己当主节点
OK
127.0.0.1:6380> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6379
master_link_status:up
master_last_io_seconds_ago:9
master_sync_in_progress:0
slave_read_repl_offset:37670
slave_repl_offset:37670
slave_priority:100
slave_read_only:1
replica_announced:1
connected_slaves:1
slave0:ip=127.0.0.1,port=6381,state=online,offset=37670,lag=1
master_failover_state:no-failover
master_replid:fba2d2377bd3218fa78a061225f7eb862480a3f6
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:37670
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:11373
repl_backlog_histlen:26298

```



## 哨兵模式（sentinel）

如果说主节点宕机了， 那么需要选举出一个新的主节点，哨兵就是用来监控所有的节点是否宕机，就是一个进程， 一直往所有的节点发请求，如果没有响应， 那么就认为节点宕机了，哨兵也有可能宕机， 所以会有多个哨兵相互监视，当主节点宕机， 那么就会由某一个哨兵发起一次投票， 选取下一个主节点。

**最好参见官网**

- 配置哨兵配置文件，在redis.conf同及目录下创建一个sentinel.conf文件，配置：

```bash
sentinel monitor myredis 127.0.0.1 6379 1 #这里代表这个哨兵监视了127.0.0.1 的6379端口的主服务器，这个主服务器判断失效至少需要1个sentinel同意才可以
```



到bin目录下启动sentinel服务，使用命令`redis-sentinel ghjconfig/sentinel.conf `即可。



- 如果说此时关闭了6379的主节点，那么哨兵过一会就会选举出新的主节点

```bash

2844:X 28 May 2022 15:48:22.629 * +failover-state-send-slaveof-noone slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:22.687 * +failover-state-wait-promotion slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:23.536 * Sentinel new configuration saved on disk
2844:X 28 May 2022 15:48:23.536 # +promoted-slave slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:23.536 # +failover-state-reconf-slaves master myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:23.602 * +slave-reconf-sent slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:24.543 * +slave-reconf-inprog slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:24.543 * +slave-reconf-done slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:24.620 # +failover-end master myredis 127.0.0.1 6379
2844:X 28 May 2022 15:48:24.620 # +switch-master myredis 127.0.0.1 6379 127.0.0.1 6381
2844:X 28 May 2022 15:48:24.620 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6381
2844:X 28 May 2022 15:48:24.620 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ myredis 127.0.0.1 6381
2844:X 28 May 2022 15:48:24.631 * Sentinel new configuration saved on disk
2844:X 28 May 2022 15:48:54.633 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ myredis 127.0.0.1 6381
#修改其他的从节点的主机为6381

```

- 自动故障迁移（Automatic failover）： 当一个主服务器不能正常工作时， Sentinel 会开始一次自动故障迁移操作，  它会将失效主服务器的其中一个从服务器升级为新的主服务器， 并让失效主服务器的其他从服务器改为复制新的主服务器；  当客户端试图连接失效的主服务器时， 集群也会向客户端返回新主服务器的地址， 使得集群可以使用新主服务器代替失效服务器。



- 如果说旧的主机回归， 那么也不会成为主节点， 只会变成新的主节点的从节点。



> 哨兵模式

优点：

- 哨兵集群， 基于主从复制，有主从复制的所有优点
- 主从可以切换故障转移
- 可以自动化，自动切换

缺点：

- Redis不好做在线扩容，集群容量满了， 那就很麻烦
- 哨兵模式的配置很麻烦



## 缓存穿透与雪崩



### 缓存穿透



- 当客户端查询的内容缓存中没有，那么就会直接去数据库中查询，但如果本身就没有这个数据， 数据库中也没有， 那么如果大量的请求访问数据库， 数据库就会崩了，就相当于出现了缓存穿透。

> ### 解决方案

**布隆过滤器**

布隆过滤器是一种数据结构， 对所有可能查询的参数以hash形式存储， 在控制层先进行校验，不符合就丢弃， 从而避免了对底层存储系统的查询压力；



**缓存空对象**

当存储层不命中后，即使返回的空对象也将其缓存起来，同时会设置一个过期时间， 之后在访问这个数据就会从缓存中获取，保护了后端数据源。

缺点：

- 有大量的空的缓存，浪费了空间
- 虽然空缓存会有过期时间， 但是还是会有一段时间缓存层与存储层的数据不一致，就会导致一些业务很麻烦。



### 缓存击穿

这个与缓存穿透有区别， 当某一个key非常热点， 不停的被请求，由于有缓存过期， 如果这个key在失效的瞬间，就会有大量的请求到数据库， 就像是屏幕被打开了一个洞， 这时候数据库就会有很大的压力。



> 解决方案

**设置热点key不过期**

但是不过期的话就会有大量的key一直被保存，就会占用大量的空间。

**加互斥锁**

分布式锁： 使用分布式锁，保证同时只有一个线程去查询后端服务， 其他线程没有权限， 因此需要等待，这时候，压力就都到了分布式锁这边。



### 缓存雪崩

当很多key在某一时间被集中访问， 那么等到key的过期时间之后，就会有一段时间有大量的key都不在缓存， 需要去查找数据库，这时候数据库就可能崩掉

> 解决方案

**Redis高可用**

这个就是，多增加几台Redis服务即可， 这样一个宕机之后还有其他的可以继续工作，其实就是搭建集群。

**限流降级**

缓存失效后，通过加锁或者队列来控制都数据库写缓存的线程数量。

还有就是服务降级， 关掉某一些服务， 从而把服务器用到那些使用很频繁的服务上。

**数据预热**

在正式部署前， 把所有的可能的数据都访问一般，这样就会有很多数据加载到缓存。
