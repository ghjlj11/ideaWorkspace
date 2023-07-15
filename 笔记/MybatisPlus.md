# MybatisPlus



**连接：**https://baomidou.com/

## 特性（官网）

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作



## 快速开始



依赖：

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>
```





- 配置yml文件连接对应的数据库

```yaml
# DataSource Config
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/malajava?useSSL=false&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver

```



- 首先是pojo实体类

```java
@TableName("user")//代表这个类对应的数据库表名
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId//代表对应表的主键
    private Long id;
    @TableField("name")//对应数据库的列
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
}

```





- 只需要有一个接口继承`BaseMapper`接口，后面不需要再写SQL，`BaseMapper`接口里面定义了很多的方法供我们使用 。

```java
@Repository
public interface UserMapper extends BaseMapper<User> {
}

```



- 然后在启动类上面加上注解扫描

```java
@SpringBootApplication
@MapperScan("com.ghj.mapper")
public class MybatisPlusStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusStudyApplication.class, args);
    }

}
```



- 最后直接测试

```java
@SpringBootTest
class MybatisPlusStudyApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询user里的所有的数据
        List<User> users = userMapper.selectList(null);

        System.out.println("结果输出!");
        for (User user : users) {
            System.out.println(user);
        }

    }

}

```



## 配置日志



直接在yml文件里面设置：

```yaml
# DataSource Config
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/malajava?useSSL=false&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver


#配置日志，代表在控制台输出的日志。
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

```



- 配置完之后就可以看到对应的SQL语句



## CRUD



### insert语句

```java
@Test
    public void insert(){
        User user = new User();
        user.setName("ghj");
        user.setAge(21);
        user.setEmail("123456");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

```



这里并没有设置id，但是自动给我们生成了：

```txt
JDBC Connection [HikariProxyConnection@1657218305 wrapping com.mysql.cj.jdbc.ConnectionImpl@c889805] will not be managed by Spring
==>  Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
==> Parameters: 1546165431777210370(Long), ghj(String), 21(Integer), 123456(String)
<==    Updates: 1
```



#### 主键生成



> 雪花算法（NONE）

- 这里会有一个雪花算法生成自动id，雪花算法的原理就是生成一个的 64 位比特位的 long 类型的全球唯一 id。
  - 最高 1 位固定值 0，因为生成的 id 是正整数，如果是 1 就是负数了。
  - 接下来 41 位存储毫秒级时间戳，2^41/(1000*60*60*24*365)=69，大概可以使用 69 年。
  - 再接下 10 位存储机器码，包括 5 位 datacenterId 和 5 位 workerId。最多可以部署 2^10=1024 台机器。
  - 最后 12 位存储序列号。同一毫秒时间戳时，通过这个递增的序列号来区分。即对于同一台机器而言，同一毫秒时间戳下，可以生成 2^12=4096 个不重复 id。



- 在`@TableId`里面会有一个type，默认是`NONE`， 值是一个枚举类：

```java
public enum IdType {
    AUTO(0),//自增
    NONE(1),//默认，雪花
    INPUT(2),//手动输入
    ASSIGN_ID(3),//
    ASSIGN_UUID(4);//UUID

    private final int key;

    private IdType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
```



> 设置自增id（AUTO）

- 通过改变数据库里的id为自增， 然后在User类的`@TableId`的type设置成自动增长

```java
@TableId(type = IdType.AUTO)
private Long id;
```

**自增是在没有传入id的情况下自增， 传入了那就是传入的id， 这些主键生成方式都是如此**



这样生成的id就是自动增长的。

> 手动输入id（INPUT）



- 通过改变User类的`@TableId`的type设置成手动输入， 如果数据库里的id还是设置为自增的话， 那么插入的还是自增的id， 这里的SQL没有传入id这个值， 执行的也是之前的代码

```txt
==>  Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
==> Parameters: null, ghj(String), 21(Integer), 123456(String)
```



```java
@TableId(type = IdType.INPUT)
private Long id;
```



### update



- 通过UserMapper继承的方法`updateById(T t)`， 可以根据id来更新数据， 通过传入一个User， 根据id， 然后找到对应的数据， 然后根据User里其他的属性不为null， 那么就进行更新该字段。

```java
    @Test
    public void update(){
        User user = new User();
        user.setId(1234L);
        user.setName("ljlj");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
```



SQL:

```txt
==>  Preparing: UPDATE user SET name=? WHERE id=?
==> Parameters: ljlj(String), 1234(Long)
<==    Updates: 1
```



当增加一个年龄时候的SQL：

```txt
==>  Preparing: UPDATE user SET name=?, age=? WHERE id=?
==> Parameters: ljlj(String), 18(Integer), 1234(Long)
<==    Updates: 1
```



当如果只有一个id其他属性都没有， 那么拼接的SQL就会有问题， 就会报错

```txt
==>  Preparing: UPDATE user WHERE id=?
==> Parameters: 1234(Long)
```



#### 自动填充



> 为时间类型的数据填充为当前时间



- 首先可以通过数据库的设置， 直接把这个字段的默认值设置为当前时间。



- 通过MYbatis-plus设置

  - 首先是把数据库里的字段加上

  - 然后pojo实体类加上属性

    ```java
    @TableField(fill = FieldFill.INSERT )//表示insert的时候自动填充
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)//表示insert、update的时候自动填充
    private Date updateTime;
    ```

  - 然后需要写一个`handler`， 实现`MetaObjectHandler`接口，实现里面的方法， 插入与更新时候的自动填充。

    ```java
    @Component
    public class MyHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            /**
             * 第一个参数： 就是方法上的
             * 第二个参数： 就是pojo实体类的属性名
             * 第三个参数： 就是该属性的类
             * 第四个参数： 就是你需要传入的值
             */
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());// 起始版本 3.3.3(推荐)
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    //        this.setFieldValByName("createTime", new Date(), metaObject);
    //        this.setFieldValByName("updateTime", new Date(), metaObject);
            // 或者
            //this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
            // 或者
            //this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        }
    
        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
            //this.fillStrategy(metaObject, "updateTime", new Date());
            //this.setFieldValByName("updateTime", new Date(), metaObject);
            // 或者
            //this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
            // 或者
            //this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        }
    }
    
    
    ```

    

  - 测试之前记得把刚刚数据库设置的默认的当前时间关掉

  - 然后就可以通过代码层面实现自动填充时间。



#### 乐观锁



- 使用Mybatis-puls实现乐观锁， 需要在数据库加上一个字段version，然后实体类加上对应的属性， 表上注解`@Version`



- 写一个配置类。

```java

@Configuration
@EnableTransactionManagement
@MapperScan("com.ghj.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}
```



-  然后就可以直接测试

```java
    @Test
    public void optimistic(){

        User user = new User();
        user.setId(1111L);
        user.setName("郭欢军");
        
        //先要查询出表里的数据的version， 然后设置到user里面进行对比version
        User user1 = userMapper.selectById(1111L);
        user.setVersion(user1.getVersion());
        
        //如果此时version不对， 那么更新失败。如果正确那么version++。
        int i = userMapper.updateById(user);
        System.out.println(i);

    }
```



- 测试后的SQL：

```java
==>  Preparing: UPDATE user SET name=?, update_time=?, version=? WHERE id=? AND version=?
==> Parameters: 郭欢军(String), 2022-07-12 21:00:50.515(Timestamp), 3(Integer), 1111(Long), 2(Integer)
<==    Updates: 1
```





### select



- 通过`selectList(null)`， 查询所有的数据

```java
List<User> users = userMapper.selectList(null);
```



- 通过`selectById(Serializable id)`， 查询指定条件的数据。

```java
User user = userMapper.selectById(1L);
```





- 通过`selectBatchIds()`来输入一个id的集合， 然后查询这些id的数据

```java
List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
```



- 通过`selectByMap(Map<String, Object>)`， 查询指定条件的数据。

```java
 /**
     *查询语句， 通过map封装查询条件查询。
     */

    @Test
    public void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "ghj");
//        map.put("id", "1111");
//        map.put("age", "21");
//        map.put("email", "123456");

        List<User> list = userMapper.selectByMap(map);
        for (User user : list) {
            System.out.println(user);
        }
    }

```





### 分页查询



- 首先需要添加配置， 添加一个拦截器， 就是之前的乐观锁的拦截器那边在add一个。

```java

@Configuration
@EnableTransactionManagement
@MapperScan("com.ghj.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加乐观锁的拦截器。
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        //添加分页拦截
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
```



- 然后需要测试时候需要一个Page的对象， 用来装数据， 并且设置第几页，以及页面大小。

```java
    /**
     * 测试分页插件selectPage(Page, null)
     */
    @Test
    public void testPage(){

        //首先需要new 一个page ， 第一个参数是第几页， 第二个参数是页面的大小。
        Page<User> page = new Page<>(2, 3);

        //通过page查询。
        userMapper.selectPage(page, null);


        List<User> users = page.getRecords();

        users.forEach(System.out::println);

    }
```



对应的SQL：

```txt
==>  Preparing: SELECT COUNT(*) AS total FROM user
==> Parameters: 
<==    Columns: total
<==        Row: 13
<==      Total: 1
==>  Preparing: SELECT id,name,age,email,create_time,update_time,version FROM user LIMIT ?,?
==> Parameters: 3(Long), 3(Long)
<==    Columns: id, name, age, email, create_time, update_time, version
<==        Row: 4, Sandy, 21, test4@baomidou.com, null, null, 1
<==        Row: 5, Billie, 24, test5@baomidou.com, null, null, 1
<==        Row: 1111, 郭欢军, 21, 123456, 2022-07-11 22:41:48, 2022-07-12 22:10:58, 4
<==      Total: 3
```



###  delete语句

delete语句与select语句方法类似

- 通过id删除， `deleteById()`

```java
//直接指定id
int i = userMapper.deleteById(1546474832920682501L);
//把id放在一个实体类 里面， 即使里面存放了别的属性， 也只会通过id删除
User user = new User();
user.setId(1546474832920682502L);

user.setName("ll");
int i = userMapper.deleteById(user);
```



- 通过map来删除，里面存放的东西就是查询条件， `deleteByMap()`

```java
HashMap<String, Object> map = new HashMap<>();

map.put("id", 1546474832920682499L);
map.put("name", "ghj");
map.put("age", "21");
int i = userMapper.deleteByMap(map);
System.out.println(i);
```



对应的SQL：

```txt

==>  Preparing: DELETE FROM user WHERE name = ? AND id = ? AND age = ?
==> Parameters: ghj(String), 1546474832920682499(Long), 21(String)
<==    Updates: 1
```



- 通过Collection来存放id， 然后全部删除， `deleteBatchIds()`。

```java
ArrayList<Long> longs = new ArrayList<>();

longs.add(1546474832920682497L);
longs.add(4321L);
int i = userMapper.deleteBatchIds(longs);

System.out.println(i);
```



对应的SQL：

```txt
==>  Preparing: DELETE FROM user WHERE id IN ( ? , ? )
==> Parameters: 1546474832920682497(Long), 4321(Long)
<==    Updates: 2
```



### 逻辑删除



> 物理删除

直接从数据库中删除

>逻辑删除

通过设置字段， 实现逻辑删除。

- 首先我们需要在数据库表里面加一个`deleted`字段， 并且设置默认值为0，删除值为1。

- 然后实体类， 加上对应的字段和注解

```java
    /**
     * 逻辑删除注解
     */
    @TableLogic
    private Integer deleted;
```



- 在yaml文件配置

```yaml
#配置日志
mybatis-plus:
  global-config:
    db-config:
      #配置逻辑删除字段
      logic-delete-field: deleted
      #配置对应的删除时候的值
      logic-delete-value: 1
      #配置对应的默认值
      logic-not-delete-value: 0
```



- 最后直接测试删除效果

```java
int i = userMapper.deleteById(1546474832920682500L);
```



可以看到到对应的SQL变成了update语句， 而不是delete语句：

```txt
==>  Preparing: UPDATE user SET deleted=1 WHERE id=? AND deleted=0
==> Parameters: 1546474832920682500(Long)
<==    Updates: 1
```



- 并且加上了逻辑删除的话， 那么查询语句也会加上判断， 查询deleted= 0

```txt
SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0
```



## 条件构造器



可以支持我们写一些复杂的数据库操作



官方列出了很多个方法

```txt
allEq全部eq(或个别isNull)
例1: allEq({id:1,name:"老王",age:null})--->id = 1 and name = '老王' and age is null
例2: allEq({id:1,name:"老王",age:null}, false)--->id = 1 and name = '老王'


eq(相等)
例: eq("name", "老王")--->name = '老王'


ne
例: ne("name", "老王")--->name <> '老王'

gt
例: gt("age", 18)--->age > 18


ge
例: ge("age", 18)--->age >= 18


lt
例: lt("age", 18)--->age < 18


le
例: le("age", 18)--->age <= 18


between
BETWEEN 值1 AND 值2
例: between("age", 18, 30)--->age between 18 and 30
    
    
notBetween
NOT BETWEEN 值1 AND 值2
例: notBetween("age", 18, 30)--->age not between 18 and 30
    

like
LIKE '%值%'
例: like("name", "王")--->name like '%王%'
    
    
notLike
NOT LIKE '%值%'
例: notLike("name", "王")--->name not like '%王%'
    
    
likeLeft
LIKE '%值'
例: likeLeft("name", "王")--->name like '%王'


likeRight
LIKE '值%'
例: likeRight("name", "王")--->name like '王%'
    
    
isNull
字段 IS NULL
例: isNull("name")--->name is null
    
    
isNotNull
字段 IS NOT NULL
例: isNotNull("name")--->name is not null
    
    
in
字段 IN (value.get(0), value.get(1), ...)
例: in("age",{1,2,3})--->age in (1,2,3)
字段 IN (v0, v1, ...)
例: in("age", 1, 2, 3)--->age in (1,2,3)
    
    
notIn


inSql
字段 IN ( sql语句 )
例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
例: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)

notInSql


groupBy
    分组：GROUP BY 字段, ...
    例: groupBy("id", "name")--->group by id,name
    
    
orderByAsc
    排序：ORDER BY 字段, ... ASC
    例: orderByAsc("id", "name")--->order by id ASC,name ASC
    
    
orderByDesc
    排序：ORDER BY 字段, ... DESC
    例: orderByDesc("id", "name")--->order by id DESC,name DESC
    
    
orderBy
    排序：ORDER BY 字段, ...
    例: orderBy(true, true, "id", "name")--->order by id ASC,name ASC
    
    
having
    HAVING ( sql语句 )
    例: having("sum(age) > 10")--->having sum(age) > 10
    例: having("sum(age) > {0}", 11)--->having sum(age) > 11
    
    
func
    func 方法(主要方便在出现if...else下调用不同方法能不断链)
    例: func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})
    
    
or
拼接 OR
例: eq("id",1).or().eq("name","老王")--->id = 1 or name = '老王'
    OR 嵌套
    例: or(i -> i.eq("name", "李白").ne("status", "活着"))--->or (name = '李白' and status <> '活着')
    
    
and
    AND 嵌套
    例: and(i -> i.eq("name", "李白").ne("status", "活着"))--->and (name = '李白' and status <> '活着')
    
    
nested
    正常嵌套 不带 AND 或者 OR
    例: nested(i -> i.eq("name", "李白").ne("status", "活着"))--->(name = '李白' and status <> '活着')
    
    
apply
拼接 sql
例: apply("id = 1")--->id = 1
例: apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
例: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")


last
    无视优化规则直接拼接到 sql 的最后

    注意事项:只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
    例: last("limit 1")
    

exists
    拼接 EXISTS ( sql语句 )
    例: exists("select id from table where age = 1")--->exists (select id from table where age = 1)
    
    
notExists
    拼接 NOT EXISTS ( sql语句 )
    例: notExists("select id from table where age = 1")--->not exists (select id from table where age = 1)
    
    
select
例: select("id", "name", "age")
例: select(i -> i.getProperty().startsWith("test"))


set
    SQL SET 字段
    例: set("name", "老李头")
    例: set("name", "")--->数据库字段值变为空字符串
    例: set("name", null)--->数据库字段值变为null
    
setSql
    设置 SET 部分 SQL
    例: setSql("name = '老李头'")
    
    
 
```



```java
/**
     * 测试条件构造器 wrapper
     */
    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //链式语法， eq等于，column与对应的值匹配，ge大于。
        wrapper
                .eq("id", 2)
                .ge("age", 12)
                .eq("name", "jack");

        List<Object> objects = userMapper.selectObjs(wrapper);

        System.out.println(objects);
    }
```



对应的SQL就会有更复杂的条件：

```txt
==>  Preparing: SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND (id = ? AND age >= ? AND name = ?)
==> Parameters: 2(Integer), 12(Integer), jack(String)
<==    Columns: id, name, age, email, create_time, update_time, version, deleted
<==        Row: 2, Jack, 20, test2@baomidou.com, null, null, 1, 0
<==      Total: 1

```





- 模糊查询

```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper.like("name", "j");
List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
```



SQL:

```txt
==>  Preparing: SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND (name LIKE ?)
==> Parameters: %j%(String)
<==    Columns: id, name, age, email, create_time, update_time, version, deleted
<==        Row: 1, Jone, 18, test1@baomidou.com, null, null, 1, 0
<==        Row: 2, Jack, 20, test2@baomidou.com, null, null, 1, 0
<==        Row: 1234, ljlj, 22, 123456, 2022-07-11 22:42:47, 2022-07-11 22:42:47, 1, 0
<==      Total: 3
```



- 子查询

```java
/**
     * 子查询inSql()
     */
    @Test
    public void testWrapper4(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 3");

        List<Object> list = userMapper.selectObjs(wrapper);

        for (Object o : list) {
            System.out.println(o);
        }
    }
```



SQL

```txt
==>  Preparing: SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND (id IN (select id from user where id < 3))
==> Parameters: 
<==    Columns: id, name, age, email, create_time, update_time, version, deleted
<==        Row: 1, Jone, 18, test1@baomidou.com, null, null, 1, 0
<==        Row: 2, Jack, 20, test2@baomidou.com, null, null, 1, 0
<==      Total: 2

```





## 代码生成器





java代码：

```java
public class GhjCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/malajava?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("ghj") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/my-study/ideaWorkspace/mybatis-plus-study/src/main/java/com/ghj/blog"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ghj")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/my-study/ideaWorkspace/mybatis-plus-study/src/main/java/com/ghj/blog")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("blog") ;// 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

```

