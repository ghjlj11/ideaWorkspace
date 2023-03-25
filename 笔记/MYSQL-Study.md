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





## 索引



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





## SQL调优

