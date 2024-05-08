# MySQL-Study



## 常用数据类型



| 类型         | 大小                                     | 范围（有符号）                                               | 范围（无符号）                                               | 用途                     |
| ------------ | ---------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------ |
| INT或INTEGER | 4  Bytes                                 | (-2 147 483 648，2 147 483 647)                              | (0，4 294 967 295)                                           | 大整数值                 |
| BIGINT       | 8  Bytes                                 | (-9,223,372,036,854,775,808，9 223 372 036 854 775 807)      | (0，18 446 744 073 709 551 615)                              | 极大整数值               |
| FLOAT        | 4  Bytes                                 | (-3.402 823 466 E+38，-1.175 494 351 E-38)，0，(1.175 494 351 E-38，3.402 823 466 351 E+38) | 0，(1.175 494 351 E-38，3.402 823 466 E+38)                  | 单精度 浮点数值          |
| DOUBLE       | 8  Bytes                                 | (-1.797 693 134 862 315 7 E+308，-2.225 073 858 507 201 4 E-308)，0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 双精度 浮点数值          |
| DECIMAL      | 对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2 | 依赖于M和D的值                                               | 依赖于M和D的值，M表示所有数字的位数，缺省值为 10，最大值为 65；D表示小数点后的数字位数，缺省值为 0 | 小数值                   |
| DATE         | 3                                        | 1000-01-01/9999-12-31                                        | YYYY-MM-DD                                                   | 日期值                   |
| TIME         | 3                                        | '-838:59:59'/'838:59:59'                                     | HH:MM:SS                                                     | 时间值或持续时间         |
| DATETIME     | 8                                        | '1000-01-01 00:00:00' 到 '9999-12-31 23:59:59'               | YYYY-MM-DD hh:mm:ss                                          | 混合日期和时间值         |
| TIMESTAMP    | 4                                        | '1970-01-01 00:00:01' UTC 到 '2038-01-19 03:14:07' UTC  结束时间是第 **2147483647** 秒，北京时间 **2038-1-19 11:14:07**，格林尼治时间 2038年1月19日 凌晨 03:14:07 | YYYY-MM-DD hh:mm:ss                                          | 混合日期和时间值，时间戳 |
| CHAR         |                                          | 0-255 bytes                                                  |                                                              | 定长字符串               |
| VARCHAR      |                                          | 0-65535 bytes                                                |                                                              | 变长字符串               |
| TEXT         |                                          | 0-65 535 bytes                                               |                                                              | 长文本数据               |



**char，nchar，nvarchar，varchar的区别：**



char：    固定长度，存储ANSI字符，存储定长数据，索引效率极高，存入数据不够长度会自动补上空格

nchar：   固定长度，存储Unicode字符，所有的字符使用两个字节表示，存入数据不够长度会自动补上空格

varchar：  可变长度，存储ANSI字符，根据数据长度自动变化

nvarchar： 可变长度，存储Unicode字符，根据数据长度自动变化，所有的字符使用两个字节表示





## 系统库



> 1、“demo”是我们通过 SQL 语句创建的数据库，是我们用来存储用户数据的，也是我们使用的主要数据库。



> 2、“information_schema”是 MySQL 系统自带的数据库，主要保存 MySQL 数据库服务器的系统信息，比如数据库的名称、数据表的名称、字段名称、存取权限、数据文件所在的文件夹和系统使用的文件夹，等等。



> 3、“mysql”数据库保存了 MySQL 数据库服务器运行时需要的系统信息，比如数据文件夹、当前使用的字符集、约束检查信息，等等。



> 4、“performance_schema”是 MySQL 系统自带的数据库，可以用来监控 MySQL 的各类性能指标。



> 5、“sakila”样本数据库是MySQL官方提供的一个模拟DVD租赁信息管理的数据库，提供了一个标准模式，可作为书中例子,教程、文章、样品,等等，对学习测试来说是个不错的选择。



> 6、“sys”数据库是 MySQL 系统自带的数据库，主要作用是，以一种更容易被理解的方式展示 MySQL 数据库服务器的各类性能指标，帮助系统管理员和开发人员监控 MySQL 的技术性能。



> 7、“world”帮助开发者快速在本地建立mysql数据库服务，并且介绍一些简单的mysql数据库服务的使用。





## SQL语句分类

- DQL：数据库查询语句（凡是带有select的语句都是）。
- DML：数据库操作语言， 凡是对表数据进行修改或者查询的 ，select， insert， delete， update。
- DDL：数据定义语言， 带有create， drop，alter都是DDL， 对表的结构进行修改。
- TCL： 事务控制语言， 事务提交， 事务回滚。
- DCL：数据控制语言， 授权， 撤销权限。





## 基本语句



### 启动与停止服务

```bash
### windows系统启动与关闭服务
## cmd选择mysql的安装目录下的bin目录

# 启动
mysqld --console

# 关闭
mysqladmin -uroot shutdown

## 直接使用net命令启动关闭服务

# 启动
net start MySQL80

# 关闭
net stop MySQL80

# 使用source执行sql存储文件。 

source D:\my-study\ideaWorkspace\table\table1.txt 
```



### 基本使用语句

```mysql
# 当mysql服务已经运行时，可以通过以下命令登录到数据库
# -h 表示主机号，为本机时可以省略，-u后加用户名， -p表示后面输入密码
mysql -h 主机名 -u 用户名 -p

# 查看所有数据库
show databases;

# 新建数据库
# 设置字符集为utf8mb4， 排序规则为utf8mb4_general_ci
CREATE DATABASE tttsss DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 删除数据库
DROP DATABASE tttsss;

# 选择数据库
use malajava;

# 查看当前数据库
select DATABASE();

# 查看所有的表的语句
show tables;
show TABLE status;

# 建表语句
create table consumer(
    consumer_id int(11)  primary key not null,
    consumer_name nvarchar(30) not null,
    consumer_data date
) ;

# 删除表操作
DROP TABLE ttttsss;

# 截断表的所有数据，不改变表的结构，会重置自增值，无法回滚。
truncate table kecheng;
```



## DML语句



```sql
# 查询
select * from emp;

# 更新
UPDATE emp SET email = 1111 WHERE sex = '1';

# 删除
delete FROM emp WHERE `name` = '小明';

# 新增
INSERT INTO emp (`name`, email, sex, department, brith) VALUES ('小明', 123456, 1, 101,CURRENT_DATE );
```



## DDL语句



> 修改表

```sql
# 修改表名 将empkkk表名字修改为emp
alter TABLE empkkk RENAME  TO emp;

# 修改表的注释
ALTER TABLE emp COMMENT '员工表';
```





> 修改字段

```SQL
# 修改表的列的名字
alter table classes rename column keshi to time;

# 修改字段
alter table classes modify id int  comment '用户名';

# 修改列名和列属性
alter table emp change sex sex11 VARCHAR(32) DEFAULT NULL COMMENT 'sex';

# 添加字段
alter table t1 add id BIGINT;

# 删除字段
alter table t1 drop id;
```







## 函数





### 聚合函数



**聚合函数不会计算null值， 就是括号内返回的只要是null， 则不会参与计算。**

> count

查看某一列在表里出现多少次，或者查看表里有多少行数据，例如：

```sql
SELECT count(age) FROM user;
select count(*) FROM user;
```



加条件查询：

```sql
SELECT COUNT(age > 21 OR NULL) FROM user;
```

**这里的条件随便加，但是后面要加上or null，否则就是查出来全部**

**因为当不加 or null 时， 语句为 count（age > 21）， 当age大于21，返回true， 否则返回false， 而count() 函数只是不计算null值，这样count函数都会统计进去，加上or null， 那么当age 大于21时，则条件为真， 不执行后面的or null， 返回true； 当age小于或等于21时， age > 21为false， 则继续执行 or null语句， 结果返回null； **





 **count(*) 和 count(1)和count(列名)区别**

执行效果上：

count(*)包括了所有的列，相当于行数，在统计结果的时候，不会忽略为NULL的值。

count(1)包括了忽略所有列，用1代表代码行，在统计结果的时候，不会忽略为NULL的值。

count(列名)只包括列名那一列，在统计结果的时候，会忽略列值为空（这里的空不是指空字符串或者0，而是表示null）的计数，即某个字段值为NULL时，不统计。



> max、min、avg、sum



求最值以、平均值、总值：

```sql
## 求age的总和
SELECT SUM(age) FROM user;
```



加上条件查询：

```sql
## age大于21的age总和
SELECT SUM(IF(age > 21,age,0)) FROM user;

## age中小于23的最大值
SELECT MAX(IF(age > 23,1,age)) FROM user;
```



`IF`条件表达式： `IF(expr1,expr2,expr3)`， 表示当`expr1`返回为`true`时， 则表达式返回`expr2`， 否则返回`expr3`





### 其他函数



> concat

多列连接成一列输出

```sql
SELECT concat(s1.id,s1.name,s1.manage_id) as sta from s;
```



> concat_ws

连接输出，并且加上连接符`,`

```sql
SELECT CONCAT_WS(',',id,name,email) as people FROM emp WHERE sex = 1;
## 输出：1001,小明,123456
```



> format

将数字格式化，保留指定位数的位小数，四舍五入

```sql
SELECT FORMAT(id, 3) FROM emp;
```







## 特殊语句



> 条件查询（where）



> 分组查询（group by）



> 联合查询（nuion）



> 模糊查询（like）



> 连接查询（join）



> 排序（desc、asc）

与`order by`同时使用。

```SQl
order by *** desc

order by *** asc		# 默认
```



> 分页查询（limit）

```text
select * from   表名  limit  starIndex， pageSize；

如果有6个用户 select  * from User limit 2，3；

则输出下标为2，3，4，的用户


将数据库分页显示，页大小为pageSize，从starIndex开始输出

select * from User limit 6;

表示页大小pageSize=6，starIndex = 0；
```









## SQL性能分析



### 慢查询日志

首先查看 是否开启慢查询日志， 执行`SHOW variables like 'slow_query_log';`	, 如果没有开启，可以去mysql配置文件开启，配置文件：/etc/my.cnf，并且可以查看慢查询日志的存放位置， 以及慢查询设定的时间，当查询时间超过设定时间，就会写入慢查询日志。



### show profiles



> 开启profiling



- 首先查看是否支持`profiling`， 执行sql 查看`select @@have_profiling;` ；
- 如果支持那么还需要查看是否开启`profiling` ， 执行`select @@profiling;`，  没开启的话就需要开启`set profiling = 1;`



>  show profires



执行`show profires`查看最近 sql的执行情况



```bash
mysql> show profiles;
+----------+------------+--------------------+
| Query_ID | Duration   | Query              |
+----------+------------+--------------------+
|        1 | 0.00026000 | select @@profiling |
|        2 | 0.00042300 | select * from role |
+----------+------------+--------------------+

```



执行`show profire for query  Query_ID`查看 某一个sql各个阶段的查询效率，  也可 以查询别的一些参数`show profile cpu,block io for query Query_ID;`

```bash
mysql> show profile for query 5;
+--------------------------------+----------+
| Status                         | Duration |
+--------------------------------+----------+
| starting                       | 0.000119 |
| Executing hook on transaction  | 0.000007 |
| starting                       | 0.000015 |
| checking permissions           | 0.000009 |
| Opening tables                 | 0.000305 |
| init                           | 0.000010 |
| System lock                    | 0.000016 |
| update                         | 0.000078 |
| end                            | 0.000005 |
| query end                      | 0.000005 |
| waiting for handler commit     | 0.008767 |
| closing tables                 | 0.000042 |
| freeing items                  | 0.000029 |
| cleaning up                    | 0.000030 |
+--------------------------------+----------+

```



### explain

解释sql语句，重要的调优功能



>  简单示例：

```bash
mysql> explain select * from ttt;
+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------+
| id | select_type | table | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra |
+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------+
|  1 | SIMPLE      | ttt   | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    1 |   100.00 | NULL  |
+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------+

```



> 结果解释：

- `id`表示执行顺序，id越大，则这个表最先执行， 如果相等则从上到下的顺序执行。
- `select_type`表示查询类型，simple，primary，union，subquery等。
- `type`表示连接类型，性能由好到差的连接类型为`NULL`、`system`、`const`、`eq_ref`、`ref`、`range`、`index`、`all`，一般不可能到`null`，除非不访问任何表， `system`表示访问一些系统表， 使用主键或者唯一索引一般到`const`，使用一般索引条件为`=`一般是`ref`， 条件是范围则是`range`， 如果使用索引全表扫描那么就是`index`，`all`就是不使用索引全表扫描 。
- `possible_keys`表示可能用到的索引。
- `key`表示实际用到的索引。
- `key_len`表示索引的长度，每个字段的索引都有各自长度。
- `rows`表示要查询的行数，在innodb中可能不准确。
- `filtered`表示返回结果行数占读取行数的百分比，越高越好。
- `Extra`表示额外信息



## 索引

为了加快查询效率

> 查看索引



`SHOW INDEX FROM testexplain;`



> 添加索引



`CREATE [UNIQUE|FULLTEXT]index index_name on table_name (column1, column2);`



```sql
create table t_dept(
    no int not null primary key,
    name varchar(20) null,
    sex varchar(2) null,
    info varchar(20) null,
    index index_no(no)
  )
```

1、添加**`PRIMARY KEY`**(主键索引)mysql> `ALTER TABLE table_name ADD PRIMARY KEY ( column )`

2、添加**`UNIQUE`**(唯一索引)mysql> `ALTER TABLE  table_name ADD UNIQUE (column)`

3、添加**`INDEX`**(普通索引)mysql>`ALTER TABLE table_name ADD INDEX index_name ( column )`

4、添加**`FULLTEXT`**(全文索引)mysql>`ALTER TABLE table_name ADD FULLTEXT ( column)`

5、添加**`多列索引`**mysql>`ALTER TABLE table_name ADD INDEX index_name ( column1, column2, column3 )`



> 删除索引

```mysql
ALTER TABLE table_name ADD DROP `id_k1`;

DROP INDEX <索引名> ON <表名>;
```



> 主键与唯一索引的区别

区别：

1、主键是一种约束，唯一索引是一种索引；

2、主键创建后一定包含一个唯一性索引，唯一性索引不一定是主键；

3、唯一性索引列允许空值， 主键不允许；

4、主键可被其他表引为外键，唯一索引不能；

5、一个表只能创建一个主键，但可创建多个唯一索引



> 外键约束： 

```SQL
ALTER TABLE <数据表名> ADD CONSTRAINT <外键名> FOREIGN KEY(<列名>) REFERENCES <外键表名> (<列名>);
```



### 索引使用规则



> 最左匹配法则

当查询时使用联合索引

- 必须使用索引最左边的字段作为条件，索引才会生效
-  如果中间没有跳过索引字段，那么索引全部生效， 如果中间跳过部分字段，那么跳过部分之后的字段索引失效
- 使用索引字段作为条件的顺序不重要，因为mysql底层会有sql优化器。



假设有索引是由（a，b，c）构成：

使用a作为条件索引才会生效； a和b索引全部生效； a和c部分生效，a字段生效；c和a 和b全部生效；a、d查询时a也会生效



> 范围查询



当使用范围查询时（>,<），范围查询右侧的列索引会失效，如果查询`a=xx and b>12 and c=vv`，那么c字段的索引将会失效。

但是如果使用（>=,<=），来代替（>,<），那么范围查询右侧列索引不会失效。



> 索引列运算



当在索引列进行运算操作，或者使用函数，那么索引失效。



> 字符串类型加引号



当查询字符串型字段时，不加引号索引会失效。



> 模糊查询



使用索引列模糊查询时，如果是头部模糊查询，那么索引失效，如果只是尾部模糊，那么索引生效。

即`where a like '%xx'`和`where a like '%xx%'`索引失效， `where a like 'xx%'` 索引生效。



> or查询



当使用`or`连接条件时，必须`or`前后条件要走索引，那么这个语句才会走索引。

即：

假设现在还有 d字段索引和 e字段索引， 条件为`b = '12' or d = 'ss'`不会走索引， `e = 'ggg' or d = 'sss'`会走索引。



> 数据分布影响



当mysql评估认为走索引比走ALL还慢，那么就会进行全表扫描。



> SQL提示



在执行SQL时，当可以有多个索引选择时，我们可以指定使用哪个索引。



`select * from table_name use index(index_name) where ...`，建议使用哪个索引。

`select * from table_name ignore index(index_name) where ...`，忽略哪个索引。

`select * from table_name force index(index_name) where ...`，强制使用哪个索引。



> 覆盖索引&回表查询



前面的索引使用都是针对条件而言，现在要对查询字段优化。

尽量使要查询的字段在索引中出现，如果直接在索引中出现，或者再加上id，那么走完索引查询就可以直接返回数据。

如果还有别的字段，那么还需要通过主键id去回表查询，从而使SQL变慢。



> 前缀索引

适用varchar，text，根据字段前缀来匹配

创建语法： `create index index_name on table_name (column(n)) `，其中n代表前缀长度。



长度的选择，应该根据这个长度的字段在表里的唯一性， 就比如a字段为1234567，表里面有很多字段是123开头，那么就应该是长度大于三好一点，同时也应该考虑索引的长度占用空间是否很大。

使用SQL查询长度应该多少：

`select count(distinct substring(cloumn, 1, n))/ count(*) from table_name`

其中n代表前缀长度，返回结果越大越不重复。



### 索引设计原则

索引要设计在经常出现在`where`条件之后的字段，以及`group by`和`order by`字段上面，这样才可以提高查询效率。





## SQL优化



### insert优化

- 批量插入， 如果插入多条数据， 应该使用一个insert语句批量插入。
- 手动提交事务， 我们可以开启一个事务， 然后中间写要执行的SQL，最后自己手动提交事务。
- 主键顺序插入， 即主键按照顺序插入。



### 主键优化

- 主键的长度尽量小
- 主键尽量自增



### order by



Using filesort 不通过索引直接返回排序结果； Using index 通过有序索引扫描直接返回有序数据。



使用order by后面排序的字段尽量要走索引， 并且要尽量使用覆盖索引。

### group by

Using temporary 分组效率低， 使用了临时表来分组； Using index分组效率高。



使用group by后面分组的字段尽量要走索引， 并且要尽量使用覆盖索引。



### limit

当数据量很大时， 例如`limit 10000000, 10`，我们只取十条记录， 但是得查找前一千万条记录， 如果直接使用limit也会很慢。



因此我们应该尽量使用覆盖索引，如果还需要查询别的非索引字段数据，那么可以加上子查询， 例如：

```mysql
select t.* from test t , (select id from test limit 10000000, 10) s where t.id = s.id;
```

这样就走了主键，加上子查询也可以查出所有字段的数据了。

### count优化



- count(字段)， 如果没有not null 约束， 会把所有字段的值查出来，在判断是否为null， 累计不是null的字段数量。
- count(主键)， 会把所有主键的值查出来，因为主键不为null， 所以直接计数。
- cuont(1)， 遍历整个表，但是不取值，每一行的返回结果都是1， 只要不是null， 那么count函数就会加一。
- count(*)，InnoDB专门做了优化， 不取值，直接按行进行累加。



效率：

**cuont(字段)<count(主键)<count(1)≈count(*)**

### update优化

在执行update时，需要让条件走索引， 否则在mysql执行事务时，会进行表锁，当别的事务要更新这个表的数据时，就会阻塞；当条件走索引， 那么就只是行锁， 不会影响这个表别的行数据更新



## 锁



### 全局锁

对整个数据库实例加锁，加全局锁之后，对任何表都只能读，不能写和更新表操作，还未提交的事务会被阻塞。使用场景在做全库备份时，对所有的表会锁定，从而获取一致性试图，保证数据完整性。

```mysql
# 添加全局锁
flush tables with read lock;

# 解锁
unlock tables;
```





### 表级锁

表锁分为读锁和写锁，其中的读写锁类似java中的读写锁。



#### 读写锁

当读锁被获取时，所有的写操作会被阻塞，读操作不阻塞；

当写锁被获取时，当前客户端可以进行读、写操作，其他的客户端读、写操作都不能进行。

```mysql
# 获取读锁
lock tables [table name] read;

# 解锁
unlock tables;

# 获取写锁
lock tables [table name] write;

# 解锁
unlock tables;
```



#### 元数据锁

MDL元数据锁，锁的是表的结构，当对表进行增删改查操作时，则获取的是MDL的写、读共享锁，这些操作可以同时进行，不会阻塞；当执行alter语句时，获取的是排他锁，与任何MDL锁都互斥；



#### 意向锁

当线程A开启事务，并更行表其中的一条数据时，线程B尝试获取表的读/写锁，此时需要一行一行判断数据是否被锁，如果出现则会被阻塞；这样效率低下，因此有了意向锁，当A锁了一行数据时，也会给该表加上一个意向锁，当B尝试获取表锁时，会查看当前表的意向锁是否被获取，如果已经被获取则B被阻塞。



>  意向锁分为意向共享锁（IS）、意向互斥锁（IX）：

**意向共享锁**：与表锁共享锁（读锁）兼容，与表锁排他锁（写锁）互斥

**意向互斥锁**：与表锁共享锁（读锁）和表锁排他锁（写锁）都互斥。意向锁之间不互斥。



### 行级锁

对于表中某一行的锁。



#### 行锁

行锁分为两种，**共享锁(S)**以及**排他锁（X）**

**共享锁**：共享锁之间不会相互阻塞，共享锁与排他锁互斥，正常执行select语句不会加锁，在select语句后面加上`lock in share mode`则会加上共享锁。

**排他锁**：排他锁与共享锁互斥，与排他锁也互斥，当执行`update、insert、delete、select for update`语句时都会加上排他锁



当执行update、delete语句时，后面的条件只要带了索引，则添加的是行锁的排他锁，如果后面的条件没有加索引，则会升级为表锁。



#### 间隙锁



间隙锁是在两条记录之间的间隙上加锁，锁住的不是数据，而是间隙。间隙锁存在的唯一目的是防止其他事务插入间隙。间隙锁可以共存，一个事务采用的间隙锁不会阻止另一个事务在同一个间隙上采用的间隙锁



默认情况下，Innodb在REPEATABLE READ（可重复读）事务隔离级别运行，Innodb使用next-key锁（临键锁）进行搜索和索引扫描，以防止幻读。

1、索引上的等值查询（唯一索引），给不存在的记录加锁时，优化为间隙锁。

2、索引上的等值查询（普通索引），向右遍历时最后一个不满足查询需求时，next-key lock退化为间隙锁。

3、索引上的范围查询（唯一索引）--会访问到不满足查询条件的第一个值为止

间隙锁：锁住的是数据之间的间隙。

行锁：锁住的是一行一行的数据。

临键锁：锁住的是间隙与行数据。





## MySQL体系结构



![1679924779219](img\mysql\1679924779219.jpg)



> 逻辑存储结构



![1f2456b10be7435f98841033256e0268](img\mysql\1f2456b10be7435f98841033256e0268.png)







![image-20240408215903015](img\mysql\image-20240408215903015.png)

### 内存架构

​	

### 磁盘结构





## 事务

数据库的事务（Transaction）是一种机制、一个操作序列，包含了一组数据库操作命令。事务把所有的命令作为一个整体一起向系统提交或撤销操作请求，即这一组数据库命令要么都执行，要么都不执行，因此事务是一个不可分割的工作逻辑单元。



### 事务的特性

事务具有 4 个特性，即原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）和持久性（Durability），这 4 个特性通常简称为 ACID

**原子性**：事务是一个完整的操作。事务的各元素是不可分的（原子的）。事务中的所有元素必须作为一个整体提交或回滚。如果事务中的任何元素失败，则整个事务将失败。**（undolog实现）**

**一致性**：当事务完成时，数据必须处于一致状态。也就是说，在事务开始之前，数据库中存储的数据处于一致状态。在正在进行的事务中. 数据可能处于不一致的状态，如数据可能有部分被修改。然而，当事务成功完成时，数据必须再次回到已知的一致状态。**（undolog+redolog实现）**

**隔离性**：对数据进行修改的所有并发事务是彼此隔离的，这表明事务必须是独立的，它不应以任何方式依赖于或影响其他事务。修改数据的事务可以在另一个使用相同数据的事务开始之前访问这些数据，或者在另一个使用相同数据的事务结束之后访问这些数据。**（锁+MVCC实现）**

**持久性**：事务的持久性指不管系统是否发生了故障，事务处理的结果都是永久的。**（redolog实现）**



### 事务的语法

```mysql
# 开启事务
BEGIN; 
或是
START TRANSACTION;

# 提交事务
COMMIT;

# 回滚事务
ROLLBACK;

```

​	

### 隔离级别

MySQL默认的隔离级别是可重复读，MySQL支持四种事务隔离级别，它们分别是：

1. **READ UNCOMMITTED（读未提交）**：允许读取尚未提交的数据变更，可能导致脏读、不可重复读和幻读问题。
2. **READ COMMITTED（读已提交）**：允许读取并发事务已经提交的数据，可以避免脏读，但可能造成不可重复读和幻读。
3. **REPEATABLE READ（可重复读）（默认）**：对同一字段多次读取的结果都是一致的，可以解决不可重复读的问题，但还存在幻读问题。
4. **SERIALIZABLE（可串行化）**：事务最高隔离级别，它会强制事务排序，使之不会发生冲突，从而解决了脏读、不可重复读和幻读问题，但因为执行效率低，所以真正使用的场景并不多。



> 脏读幻读不可重复读

MySQL中的脏读、不可重复读和幻读是三种不同的并发控制问题，它们分别描述了事务间数据读取和更新的不同行为和影响。

- 脏读（Dirty Read）。当一个事务读取了另一个事务未提交的数据时，就会发生脏读。例如，事务B更新了数据，但尚未提交，事务A读取了更新后的数据，如果事务B回滚，那么事务A读取的数据将不再有效，这种情况就被称为脏读。123456

- 不可重复读（Non-Repeatable Read）。在同一个事务中，如果多次读取相同的数据，每次读取的结果可能不一致。这是因为在此期间可能有其他事务对数据进行了修改并提交了。例如，事务A在两次查询中读取了同一条记录，但第二次查询的结果与第一次不同，因为可能在两次查询之间有其他事务修改了数据。
- 幻读（Phantom Read）。幻读通常发生在查询操作中，特别是在使用索引的条件下。当事务A在范围查询过程中，事务B插入了新的满足查询条件的数据。导致事务A在再次查询时未能读取到新增的数据，而产生幻读。在默认的隔离级别下，事务A读取操作并不能读取到事务B中更新的数据，因此会发生幻读。

为了解决这些问题，MySQL提供了不同的隔离级别，如读未提交（Read Uncommitted）、读已提交（Read Committed）、可重复读（Repeatable Read）和串行化（Serializable）。这些隔离级别通过限制事务之间数据更新的可见性来避免上述并发问题。



### 事务的原理



#### redolog

用来记录事务提交时，数据页的修改，用来实现事务的持久性，在事务的执行过程中开始记录。主要用于刷新脏页数据到磁盘中失败时，进行数据正确的修改。对于一个已经提交的事务，在事务提交后即使系统发生了崩溃，这个事务对数据库中所做的更改也不能丢失。但是如果我们只在内存的 Buffer Pool 中修改了页面，假设在事务提交后突然发生了某个故障，导致内存中的数据都失效了，那么这个已经提交了的事务对数据库中所做的更改也就跟着丢失了，这是我们所不能忍受

![1712678905367](img\mysql\1712678905367.jpg)



redolog由两部分组成，一部分存在内存中 redo log buffer，一部分存在磁盘中redo log file。

当事务在修改数据时，修改的数据会先写入缓冲池中（内存），再由缓冲池写入到redo log buffer中；当事务提交时，会将redo log buffer中记录的日志写入到redo log file中。因为buffer pool中的数据会不定时刷新到磁盘中，如果在刷新磁盘时候失败了，就可以使用redo log file进行数据修改。



如果buffer pool中的数据直接刷新到磁盘中，因为我们每次更新的数据可能不是连续的，在磁盘上可能不是连续的页，因此如果直接刷新会占用大量磁盘io，影响性能；使用redo log buffer写入到redo log file中 时，因为写入redo log file是顺序写入，因此占用磁盘io小，性能高。



#### undolog



undolog是一种用于撤销回退的日志，主要用于回滚操作，以及多版本控制（MVCC），在事务开始之前开始记录。当执行一个insert操作，undolog会记录一个与之对应的delete语句；当执行一个update操作，undolog会记录一个相反的update操作。当事务提交失败时，则可以使用undolog来回滚事务。

#### binlog



binlog是记录所有数据库表结构变更（例如CREATE、ALTER TABLE…）以及表数据修改（INSERT、UPDATE、DELETE…）的二进制日志。不会记录SELECT和SHOW这类操作，因为这类操作对数据本身并没有修改。在事务提交之后记录。

bin log 是 MySQL 的一种二进制日志，记录引起或可能（更新删除没有匹配的记录）引起数据库变动的事件信息。

bin log 以事件形式记录，不是事务日志。对于非事务表的操作，每当语句执行完成则直接写入；对于事务表的操作则会在事务提交时（先记录到缓存中）一次性写入。

主要用于数据备份和恢复、主从同步、审计、数据异构、基于数据的任务分发等等。

## MVCC

多版本并发控制，维护一个数据的多个版本。实现MVCC需要有隐藏字段、undolog、readview，这三个条件。



> 当前读

读取的是记录最新的版本，并且读取时会阻塞其他事务对当前数据的更改，也就是会对读取的记录加锁。例如：`select ... lock in share mode`（共享锁）、`insert`、`update`、`delete`、`select ...  for update`（排他锁），都是当前读。



> 快照读

快照读读取的是数据的可见版本，有可能是历史版本，不加锁，非阻塞的。例如：常规的`select`语句

- Read Committed：每一次select，都会生成一个快照读。
- Repeatable Read：开启事务后的第一个select语句会生成快照读
- Serializable：快照读退化为当前读。

生成快照读，以Repeatable Read为例，当事务开启后的第一个select时，会选择当MySQL服务最新的可见版本的数据，在之后进行正常的select语句时，查询的都是这个版本的数据，无论中间有无其他事务更改数据。



### 隐藏字段



|  隐藏字段   |                             含义                             |
| :---------: | :----------------------------------------------------------: |
|  DB_TRX_ID  | 最近修改事务ID，记录插入这条记录或最后一次修改该记录的事务ID。 |
| DB_ROLL_PTR | 回滚指针，指向这条记录的上一个版本，用于配合undolog，指向上一个版本。 |
|  DB_ROW_ID  |    隐藏主键，如果表结构没有指定主键，将会生成该隐藏字段。    |



### undolog

回滚日志，在insert、update、delete的时候产生的便于数据回滚的日志。

当insert的时候，产生的undolog日志只在回滚时需要，在事务提交后，可被立即删除。

而update、delete的时候，产生的undolog日志不仅在回滚时需要，在快照读时也需要，不会立即被删除。



> undolog版本链

当不同事务对同一个记录修改时，因为表中的隐藏字段DB_ROLL_PTR，undolog会生成一个版本链表，链表头部既是最新的历史版本，链表尾部则是最早的历史版本。

![1713369723343](img\mysql\1713369723343.jpg)

### readview

ReadView(读视图) 是 快照读 SOL执行时MVCC提取数据的依据，记录并维护系统当前活跃的事务(未提交的)id。ReadView中包含了四个核心字段:

|      字段      |                        含义                        |
| :------------: | :------------------------------------------------: |
|     m_ids      |                当前活跃的事务ID集合                |
|   min_trx_id   |                   最小活跃事务ID                   |
|   max_trx_id   | 预分配事务ID，当前最大事务ID+1(因为事务ID是自增的) |
| creator_trx_id |               ReadView创建者的事务ID               |



> 版本链数据访问规则

trx id:代表是当前事务ID。

- trx_id == creator_trx_id?可以访问该版本。     ====>成立，说明数据是当前这个事务更改的。
- trx_id<min_trx_id?可以访问该版本       ======>成立，说明数据已经提交了。
- trx_id>max_trx_id?不可以访问该版本      =======> 成立，说明该事务是在ReadView生成后才开启。
- min_trx_id <=trx_id<= max_trx_id?如果trx_id不在m_ids中是可以访问该版本的   =====>成立，说明数据已经提交。
- min_trx_id <=trx_id<= max_trx_id?如果trx_id在m_ids则不可以访问该版本，因为说明当前事务在活动事务集合中，数据未提交。



**不同的隔离级别，生成ReadView的时机不同：**

**READ COMMITTED：在事务中每一次执行快照读时生成ReadView。**
**REPEATABLE READ：仅在事务中第一次执行快照读时生成ReadView，后续复用该ReadView。**



结合下图，**在RC隔离级别时**，查看事务5中第一个生成readview读取版本链中的版本，只能读取到事务id为2的版本。将版本链中的事务id带入到4个条件中，即可发现只能读取事务id为2的事务。在第二个readview中，则可以查看到事务id为3的版本数据。	

![1713455138220](img\mysql\1713455138220.jpg)



**在RR隔离级别时**，上图中的事务5则只会产生一个readview，只能读取事务id为2的数据，第二次查询时，会复用第一次查询生产的readview，因此查询到数据的版本时同一个，也就保证了可重复读，每次读取的都是一样的数据。



## 慢查询日志

开启mysql慢查询日志，用于查看耗时较长的sql语句。



- 开启慢查询日志，找的mysql的配置文件my.cnf，并且开启配置，如果日志文件没有先创建一个。

```bash
# 开启慢查询日志
slow_query_log=1
# 执行时间参数
long_query_time=2
# 设置慢查询日志保存路径，确保mysql对该文件有权限
slow_query_log_file=/var/log/mysql/mysql-slow.log
# 记录未使用索引的查询
log_queries_not_using_indexes=1
# 记录执行较慢的管理语句
log_slow_admin_statements=1
```



- 重启mysql服务，并且执行慢sql查询。
- 查看配置中的日志文件，查看慢sql详细信息。

```bash
/usr/sbin/mysqld, Version: 8.0.36 (MySQL Community Server - GPL). started with:
Tcp port: 3306  Unix socket: /var/lib/mysql/mysql.sock
Time                 Id Command    Argument
# Time: 2024-04-28T14:21:55.671919Z
# User@Host: root[root] @ localhost []  Id:     8
# Query_time: 2.691150  Lock_time: 0.000004 Rows_sent: 10  Rows_examined: 6000010
use test;
SET timestamp=1714314112;
select * from people limit 6000000, 10;
```





```yaml
systemLog:
#MongoDB发送所有日志输出的目标指定为文件
# #The path of the log file to which mongod or mongos should send all diagnostic logging information
destination: file
#mongod或mongos应向其发送所有诊断日志记录信息的日志文件的路径
path: "/mongodb/single/log/mongod.log"
#当mongos或mongod实例重新启动时，mongos或mongod会将新条目附加到现有日志文件的末尾。
logAppend: true
storage:
#mongod实例存储其数据的目录。storage.dbPath设置仅适用于mongod。
##The directory where the mongod instance stores its data.Default Value is "/data/db".
dbPath: "/mongodb/single/data/db"
journal:
#启用或禁用持久性日志以确保数据文件保持有效和可恢复。
enabled: true
processManagement:
#启用在后台运行mongos或mongod进程的守护进程模式。
fork: true
net:
#服务实例绑定的IP，默认是localhost
bindIp: localhost,192.168.0.2
#bindIp
#绑定的端口，默认是27017
port: 27017
```

