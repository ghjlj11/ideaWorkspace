# SpringCloud-Study



## SpringCloud与SpringBoot的关系 

- SpringBoot是用来构建微服务的， SpringCloud是用来协调微服务的。SpringBoot把程序打成一个个的jar包在SpringCloud上运行。
- SpringCloud是关注于全局的微服务协调整理治理框架， 把SpringBoot的一个个单体服务整合到一起管理， 为各个服务之间提供： 配置管理， 服务发现， 断路器， 路由， 微代理， 事件总线， 全局锁， 决策竞选， 分布式会话等等集成服务。
- SpringBoot可以离开SpringCloud独立使用，但是SpringCloud离不开SpringBoot。
- SpringBoot关注各个单体开发 ， SpringCloud专注于全局的服务治理框架。



## Rest学习环境搭建



- 建立父工程导包

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>springCloud-study</artifactId>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>springcloud-api</module>
        <module>sptingcloud-provider-dept-8001</module>
    </modules>

    <packaging>pom</packaging>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <junit.version>4.13.2</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.18.24</lombok.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2021.0.2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.6.7</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.29</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.2.9</version>
            </dependency>

            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.2.2</version>
            </dependency>

            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>1.3.0-alpha14</version>
            </dependency>


            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>

            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <scope>provided</scope>
            </dependency>

        </dependencies>
    </dependencyManagement>
</project>
```



​		这里的version可以从<properties>里面取出版本号来， <dependencyManagement>就是作为一个父工程导包， 子工程可以不用选定版本号， 直接用父工程的版本就行。



- 建一个名为springcloud-api的子模块， 里面就建一个pojo实体类。

```java
@Data
@NoArgsConstructor
@Accessors(chain = true)//支持链式语法
public class Dept implements Serializable {//需要实现序列化， 这样才可以在网络里传输。
    private Long dept_no;
    private String d_name;
    private String db_source;

    /**
     * 只需要这个，其他的都自动获得
     */
    public Dept(String d_name){
        this.d_name = d_name;
    }
}
```



- 然后建一个名为sptingcloud-provider-dept-8001的子模块， 里面自己导包，从写springBoot的代码， 自己实现 之前自动生成的springboot的代码，也就是自己写一个启动类，  然后连接数据库， 整合mybatis， 配置好yml文件， 还有mapper.xml， server层， controller， 然后就可以启动了。 



​		**controller层调用service层的时候， 需要定义一个service对应的实现类，  然后自动注入， 我们也可以定对应接口， 然后自动注入， 因为实现类， 就是实现的接口， 就是一个接口被实现类实例化。 **



- 然后再建一个consumer， 用来调用provider的service， 这个consumer没有server层， 只有一些请求， 然后通过跳转到 provider上， 再返回给用户。 

  - 首先是要有controller， 然后我们的controller如何跳转到provider的service ， 这就需要用到一个类， 就是RestTemplate， 可实现跳转， 并且携带数据与返回数据， 然而这个类没有注入到Spring离去， 我们就再写一个config， 把他注入进去：

  - ```java
    @Configuration
    public class ConfigBean {
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
    }
    ```

    

  - 然后再写controller

  - ```java
    @RestController
    public class MyController {
    
        @Autowired
        RestTemplate restTemplate;
        private static final String PREFIX = "http://localhost:8001";
    
        @RequestMapping("/consumer/dept/get/{id}")
        public Dept get(@PathVariable("id") Long id){
            return restTemplate.getForObject(PREFIX + "/dept/get/" + id, Dept.class);
        }
    
        @RequestMapping("/consumer/dept/add")
        public String add( Dept dept){
            return restTemplate.postForObject(PREFIX + "/dept/add", dept, String.class);
        }
        @RequestMapping("/consumer/dept/list")
        public List list(){
            return restTemplate.getForObject(PREFIX + "/dept/list",List.class);
        }
    }
    
    ```

  

  - 这里的getForObject就是以get方式请求， 然后请求后面的地址， 以及返回的值的类型；postForObject就是以post方式请求， 然后后面就是地址， 请求携带的参数类型， 返回值的类型。

    

  - 这样就可以实现从consumer跳转到provider的service，是基于Http请求跳转的。





## Eureka



​		**eureka就是类似之前的Dubbo 就是一个组测服务中心，把所有的服务都注册进去， 然后客户端就可以直接在里面访问了**



垃圾玩意停更了， 垃圾。



- 首先导入依赖。

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
        <version>1.4.7.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```



- 然后配置yml文件 。

```yml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false #表示是否需要注册到服务中心
    fetch-registry: false #为false就表示自己是服务注册中心
    service-url: #监控页面
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```



- 然后建一个启动类， 直接启动访问localhost:7001

![cloud01](img\cloud01.png)



- 自我保护机制： 如果某个服务在运行时候出现了问题， 那么Eureka不会立即丢弃这个服务， 而是会保存下这些信息，还会出现爆红的现象，  然后如果这个服务好了 ， 也还可以继续链接上来， 就会退出自我保护机制。 



- 配置client， 就是服务注册者：

```yml
server:
  port: 8001

mybatis:
  type-aliases-package: com.ghj.springcloud.pojo
  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml


spring:
  application:
    name: spring-provider-dept
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db01?userUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    username: root
    password: 123456

#eureka的配置， 服务注册到什么地方
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
  instance:
    instance-id: springcloud-provider-dept-8001 #修改eureka上的默认信息描述

# 配置这个才会有info的信息
management:
  endpoints:
    web:
      exposure:
        include: "*"
  info:
    env:
      enabled: true

# info配置, 这里的配置随便玩。
info:
  app.name: ghj-springcloud
  company.name: alibaba
  school.name: ECUT
  ghj.name: lj


```



- 配置controller，写一个控制接口， 可以按照id来获取服务实例，然后可以获取到一些信息：

```java
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public String add(@RequestBody Dept dept){
        deptService.addDept(dept);
        return "ok";
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> getAll(){
        return deptService.queryAll();
    }

    @GetMapping("/dept/disc")
    public Object discovery(){
        List<String> services = client.getServices();
        List<ServiceInstance> instances = client.getInstances("SPRING-PROVIDER-DEPT");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getInstanceId());
        }
        return this.client;
    }
}
```

 

- 然后 就是 配置启动类

```java
@EnableEurekaClient 
@SpringBootApplication
@EnableDiscoveryClient // 配置发现服务
public class DeptProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8001.class,args);
    }
}
```



- 然后跑起来可以看到 服务的信息
