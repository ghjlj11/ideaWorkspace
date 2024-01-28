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





## MySQL体系结构



![1679924779219](img\mysql\1679924779219.jpg)



> 逻辑存储结构



![1f2456b10be7435f98841033256e0268](img\mysql\1f2456b10be7435f98841033256e0268.png)



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

使用a作为条件索引才会生效； a和b索引全部生效； a和c部分生效，a字段生效；c和a 和b全部生效



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

