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
