# MySQL-Study





## char，nchar，nvarchar，varchar的区别



char：    固定长度，存储ANSI字符，存储定长数据，索引效率极高，存入数据不够长度会自动补上空格

nchar：   固定长度，存储Unicode字符，所有的字符使用两个字节表示，存入数据不够长度会自动补上空格

varchar：  可变长度，存储ANSI字符，根据数据长度自动变化

nvarchar： 可变长度，存储Unicode字符，根据数据长度自动变化，所有的字符使用两个字节表示



## Innodb



## 数据库分页显示

```text
select * from   表名  limit  starIndex， pageSize；

如果有6个用户 select  * from User limit 2，3；

则输出下标为2，3，4，的用户


将数据库分页显示，页大小为pageSize，从starIndex开始输出

select * from User limit 6;

表示页大小pageSize=6，starIndex = 0；
```





## 一些操作



```mysql
use malajava;
select * from kecheng where name like '%体%';
select distinct id from kecheng;

select FLOOR(4.33) as id from kecheng;

select ASCII(3);

select * from kecheng where id = (select max(id) from kecheng);

select * from kecheng where name is null ;

select * from kecheng where name IS NULL;

/*分组查询限执行where， 在group by*/

select id,name,keshi from kecheng group by (name);


delete from kecheng where id = 4;

insert into malajava.kecheng (id, name, keshi) VALUES (4, 'java', 48);


select max(id) from kecheng;

select * from kecheng group by id;

/*通过查询来创建表*/

create table classes as select * from kecheng;

create table name as select name from classes;

drop table name;

desc classes;

show table status ;

show databases ;

/*修改表的列的属性*/
alter table classes modify name nvarchar(40);
desc classes;

/*修改表的列的名字*/
alter table classes rename column keshi to time;
desc classes;

/*删除列*/
alter table classes drop column name;
desc classes;

/*添加注释*/
alter table classes comment '课程表';

alter table classes modify id int  comment '用户名';

show create table classes;

/*删除表*/
drop table classes;
show tables ;

/*截断表，快速删除表，比delete快*/
truncate table kecheng;

/*约束*/

create table consumer(
    consumer_id int(11)  primary key not null,
    consumer_name nvarchar(30) not null,
    consumer_data date
) ;

create table supermarket(
    id int(11) primary key ,
    name nvarchar(50) not null ,
    consumers int(11) ,
    foreign key (consumers) references consumer(consumer_id)
);

/*修改时间为系统事件*/
alter table consumer modify consumer_data date default(current_date);

insert into consumer(consumer_id, consumer_name) VALUES (001,'郭欢军');
insert into consumer(consumer_id, consumer_name) VALUES (002,'罗静');
insert into consumer(consumer_id, consumer_name) VALUES (003,'罗芊');

insert into supermarket(id, name, consumers) VALUES (001,'优乐购',001);
insert into supermarket(id, name, consumers) VALUES (002,'优乐购',002);

/*修改时间为系统事件*/
alter table consumer modify consumer_data datetime default (current_time);
insert into consumer(consumer_id, consumer_name) values (004, '邹龙');
/*当插入的数据有'和"的时候*/
insert into consumer(consumer_id, consumer_name) values(006, 'a''""b');

update consumer set consumer_name = 'a''"b' where consumer_id = 006;
/*一次插入多个数据*/
insert into consumer(consumer_id, consumer_name) values (7, 'a'), (8, 'b'),(9, 'c');

select * from consumer ;

update supermarket set name = '优乐购2', consumers = '1' where id = 2;

insert into supermarket(id, name, consumers) values (3, '喜洋洋', 2);
insert into supermarket(id, name, consumers) values (4, '喜洋洋', 2);

/*内连接查询*/
select * from supermarket s, consumer c where s.consumers = c.consumer_id;
select * from supermarket s inner join consumer c on s.consumers = c.consumer_id;

/*外连接查询*/
/*左外连接*/
select * from supermarket s left join consumer c on c.consumer_id = s.consumers;
/*右外连接*/
select * from supermarket s right join consumer c on c.consumer_id = s.consumers;

select * from supermarket s  join consumer c on c.consumer_id = s.consumers;

insert into supermarket(id, name, consumers) values (6, '太平洋', null);

/*笛卡尔积*/
select * from student cross join teacher;

/*相当于是join查询*/
select * from student cross join teacher t on t.id = student.tid;

/*自链接查询*/
create table staff (
    id int(10) primary key ,
    name nvarchar(30) ,
    manage_id int(10)
)engine innodb;

insert into staff(id, name, manage_id) VALUES (1, '郭欢军', 3);
insert into staff(id, name, manage_id) VALUES (2, '罗静', 3);
insert into staff(id, name, manage_id) VALUES (3, '罗芊', 3);

insert into staff(id, name, manage_id) VALUES (4, 'fg', 5);
insert into staff(id, name, manage_id) VALUES (5, 'hf', 5);
insert into staff(id, name, manage_id) VALUES (6, 'bvn', 5);

insert into staff(id, name, manage_id) VALUES (7, '嘻嘻', 7);
insert into staff(id, name, manage_id) VALUES (8, '嘿嘿', 7);
insert into staff(id, name, manage_id) VALUES (9, '哈哈', 7);

select id || '-' || name  from staff;
select concat(s1.id,s1.name,s1.manage_id) as sta, concat(s2.id,s2.name,s2.manage_id) as man from staff s1, staff s2 where s1.manage_id = s2.id;

/*子查询*/

/*增加索引， add index index_name(column)*/
alter table kecheng add index id(id);

```



## 更新表的结构



> 修改字段

```SQL

alter table classes modify id int  comment '用户名';
```





> ### 索引



```sql
create table t_dept(
    no int not null primary key,
    name varchar(20) null,
    sex varchar(2) null,
    info varchar(20) null,
    index index_no(no)
  )
```

1、添加PRIMARY KEY(主键索引)mysql>ALTER TABLE `table_name` ADD PRIMARY KEY ( `column` )

2、添加UNIQUE(唯一索引)mysql>ALTER TABLE `table_name` ADD UNIQUE (

`column`

)

3、添加INDEX(普通索引)mysql>ALTER TABLE `table_name` ADD INDEX index_name ( `column` )

4、添加FULLTEXT(全文索引)mysql>ALTER TABLE `table_name` ADD FULLTEXT ( `column`)

5、添加多列索引mysql>ALTER TABLE `table_name` ADD INDEX index_name ( `column1`, `column2`, `column3` )





### 多列链接成一列输出。

```
concat(s1.id,s1.name,s1.manage_id) as sta
```



### 降序升序



```SQl
order by *** desc

order by *** asc		默认
```



## 给表的字段添加索引



PRIMARY KEY主键索引：mysql>ALTER TABLE `table_name` ADD PRIMARY KEY ( `column` ) 
 NIQUE：mysql>ALTER TABLE `table_name` ADD UNIQUE ( `column` ) 
 INDEX普通索引 ：mysql>ALTER TABLE `table_name` ADD INDEX index_name ( `column` ) 
 FULLTEXT全文索引 ：mysql>ALTER TABLE `table_name` ADD FULLTEXT ( `column`) 
 INDEX多列索引：mysql>ALTER TABLE `table_name` ADD INDEX index_name ( `column1`, `column2`, `column3` )



外键约束： 

```SQL
ALTER TABLE <数据表名> ADD CONSTRAINT <外键名> FOREIGN KEY(<列名>) REFERENCES <外键表名> (<列名>);
```



## 给表的字段修改属性



```SQL
ALTER TABLE 表名 CHANGE 旧字段名 新字段名 字段类型(长度) comment (评论);
```

 





-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





## Start



### SQL语句分类

- DQL：数据库查询语句（凡是带有select的语句都是）
- DML：数据库操作语言， 凡是对表进行修改的 ， insert， delete， update， 是对表的数据进行修改；
- DDL：数据定义语言， 带有create， drop，alter都是DDL， 对表的结构进行修改。
- TCL： 事务控制语言， 事务提交， 事务回滚。
- DCL：数据控制语言， 授权， 撤销权限。





```SQL

-- 使用source执行sql存储文件。 

source D:\my-study\ideaWorkspace\table\table1.txt   
```



## 主键与唯一索引的区别

区别：

1、主键是一种约束，唯一索引是一种索引；

2、主键创建后一定包含一个唯一性索引，唯一性索引不一定是主键；

3、唯一性索引列允许空值， 主键不允许；

4、主键可被其他表引为外键，唯一索引不能；

5、一个表只能创建一个主键，但可创建多个唯一索引

