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



