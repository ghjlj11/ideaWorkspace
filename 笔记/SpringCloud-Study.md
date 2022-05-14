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



### 注册中心





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



```java

@EnableEurekaServer // 配置eureka支持
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```





- 自我保护机制： 如果某个服务在运行时候出现了问题， 那么Eureka不会立即丢弃这个服务， 而是会保存下这些信息，还会出现爆红的现象，  然后如果这个服务好了 ， 也还可以继续链接上来， 就会退出自我保护机制。 



### 注册服务者





- 然后就是配置client， 就是服务注册者：



- 首先导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>2.3.1.RELEASE</version>
</dependency>
```



​		**注意这里的版本需要低一点， 高了的话会注册不进去**

​		**可以直接用下面的新版， 船新版本。**

- 然后配yml文件

```yml
server:
  port: 8001

mybatis:
  type-aliases-package: com.ghj.springcloud.pojo
  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml


spring:
  application:
    name: spring-provider-dept #服务的名称
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



- 配置controller，写一个控制接口，client.getInstances 可以按照id来获取服务实例，然后可以获取到一些信息 ：

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
@EnableEurekaClient //配置eureka， 让注册中心发现， 扫描这个服务。
@SpringBootApplication
@EnableDiscoveryClient // 配置发现服务， 让注册中心发现， 扫描这个服务， 两个配置效果相同
public class DeptProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8001.class,args);
    }
}
```



- 然后跑起来可以看到 服务的信息



**上面的两个依赖spring-cloud-starter-eureka-server， 以及spring-cloud-starter-eureka 都是老版本的， 官方都不维护 了， 以至于spring-boot-starter-actuator必须要降版本， 官方推荐还是用以下的两个依赖， 这样我们的actuator， 也不需要降版本了**

```xml
<!--服务注册中心的依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>3.1.2</version>
</dependency>
<!--服务被注册的依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>3.1.2</version>
</dependency>


```



### 集群



​		如果注册服务中心炸了， 那么就会有很严重的问题， 所有的服务都会拿不到， 因此， 我们需要有集群操作， 就是有多个注册服务中心， 这些注册中心都相互关联， 所有服务都要注册到这些注册中心中。这样一个崩了就还有别的 。 可以有很多个节点。



- 首先建两个模板端口号7002，7003， 文件配置都和7001一模一样， 只是端口号改掉， 然后主机号也需要不一样 ， 这是集群的关键， 因此我们需要去修改一下 系统文件C:\Windows\System32\drivers\etc\hosts， 下面改成：

  127.0.0.1       localhost7001
  127.0.0.1       localhost7002
  127.0.0.1       localhost7003

  然后就可以是要这些主机名， 但是其实都是127.0.0.1， 就是原来的localhost， 只是多了几个名字， 再然后就是需要关联这些服务注册中心了， yml文件：

- ```yml
  server:
    port: 7003
  
  eureka:
    instance:
      hostname: localhost7003
    client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://localhost7001:7001/eureka/,http://localhost7002:7002/eureka/
  ```

​	这里就是7003的配置， 其他两个也是一样。



-  再然后就是把服务注册到这些服务注册中心， 也只需要修改一下yml文件，就只需要修改这一段代码， 让服务都注册在这三个注册中心。 

```yml
#eureka的配置， 服务注册到什么地方
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
  instance:
    instance-id: springcloud-provider-dept-8001 #修改eureka上的默认信息描述
```



### CAP原则

- C 强一致性
- A 可用性
- P 分区容错性

CAP三进二原则: 要么满足CA， 要么AP， 要么CP， 不能三个都满足。

**ACID**

- 原子性
- 一致性
- 隔离性
- 持久性



分布式系统一般肯定是会保证容错性的， 那么要么就是AP， 要么CP，Zookeeper就是保证的CP而Eureka就是保证的AP。



#### Zookeeper保证的是CP

​		当向注册中心查询服务列表时， 我们可以容忍是几分钟之前的信息， 但是不可以容忍是服务直接down的不可用，就是服务注册功能对可用性的要求要高于一致性 ， 但是Zookeeper会出现这种情况， 如果说主节点因为网络或者什么原因， 与其他的节点失去了联系， 那么剩余的节点会选举出一个新的主节点， 这个选举的时间可能会花费很久， 而且这个时候整个服务是瘫痪状态， 不可访问， 这样就会然后、让用户很没有体验。



#### Eureka保证的是AP



​		Eureka优先保证可用性， 他的各个节点都是平等的， 一个节点挂掉并不会影响别的节点正常工作， 剩余的节点依然可以提供服务， Eureka客户端在向某个节点注册时， 如果连接失败 ，则会自动链接别的节点， 只要有一台服务机存在， 即可提供服务， 不过可能不是最新的信息， 除此之外， Eureka还有自我保护机制， 如果在15分钟之内超过85%的节点没有正常的心跳， 那么Eureka就会认为客户端与注册中心出现了网络故障， 会出现以下几种情况：

​	1、Eureka不再从注册表中移除因为长时间没有收到心跳而国企的服务。

​	2、Eureka仍然可以接受新的服务注册和查询请求， 但是不会被同步到其他的节点上（保证当前节点可用即可）

​	3、当网络稳定时， 当前实例新的注册信息会被同步到其他节点中



### RPC与HTTP

​		RPC (Remote Procedure Call)即远程过程调用，是分布式系统常见的一种通信方法，HTTP 调用其实也可以看成是一种特殊的 RPC，只不过传统意义上的  RPC 是指长连接数据交互，而 HTTP 一般是指即用即走的短链接

**HTTP VS RPC (普通话 VS 方言)**

​		HTTP 与 RPC 的关系就好比普通话与方言的关系。要进行跨企业服务调用时，往往都是通过 HTTP   API，也就是普通话，虽然效率不高，但是通用，没有太多沟通的学习成本。但是在企业内部还是 RPC  更加高效，同一个企业公用一套方言进行高效率的交流，要比通用的  HTTP   协议来交流更加节省资源。整个中国有非常多的方言，正如有很多的企业内部服务各有自己的一套交互协议一样。虽然国家一直在提倡使用普通话交流，但是这么多年过去了，你回一趟家乡探个亲什么的就会发现身边的人还是流行说方言。

​		如果再深入一点说，普通话本质上也是一种方言，只不过它是官方的方言，使用最为广泛的方言，相比而言其它方言都是小语种，小语种之中也会有几个使用比较广泛比较特色的方言占比也会比较大。这就好比开源  RPC 协议中 Protobuf 和 Thrift 一样，它们两应该是 RPC 协议中使用最为广泛的两个。



## Ribbon负载均衡器



**SpringCloud已经不用Ribbon了， 而是用的LoadBalancer， 来进行负载均衡。**



### 负载均衡

- 负载均衡就是把用户的请求平坦分到多个服务器上， 从而达到系统高可用，

- 常用软件 Nginx， Lvs等

- Dubbo、 SpringCloud都有自己的负载均衡， SpringCloud的负载均衡算法可以自定义。

- 负载均衡的分类：

  - 集中式：

    即在服务的消费方与提供方之间有独立的设施， 如Nginx， 该设施负责把请求通过某种手段转发到服务的提供方。

  - 进程式：

    将LB逻辑集成到消费方， 消费方从服务注册中心获取那些地址可用， 然后自己从这些地址选出一个最合适的。



### 消费者关联注册中心



- 我们首先还是需要到依赖， 就是之前的client依赖就够了，这里面存在LoadBalancer的依赖。 然后只需要在config文件里面， 我们之前向Spring注册了RestTemplate，在上面加一个负载均衡的注解就可以了：

```java
@Configuration
public class ConfigBean {

    /**
     * 实现负载均衡
     */

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

```



- 然后修改controller， 之前我们是通过直接访问服务提供者的service方法，现在我们去服务注册中心拿， 因为服务已经被注册进去了， 所以我们只需要改一下链接就好了， 把链接改成服务的名字， 客户端直接调用服务名就好了， 不用管地址与端口号。 

```java
@Autowired
    RestTemplate restTemplate;
    /**
     * Ribbon这里应该需要的是一个服务名， 因为如果写的是http://localhost:8001那么就只会直接访问服务端，
     * 都不会走注册中心， 这里需要写一个服务的名字， 这样就可以访问注册中心。
     */
    private static final String PREFIX = "http://SPRING-PROVIDER-DEPT";
```



### 实现负载均衡



- 我们创建三个服务， 名字都一样， 然后端口号不一样，访问的数据库也不一样， 这样消费者通过名字访问就可以实现访问三个里面的其中一个， 然后跑起来， 我们通过消费者去访问服务， 连续访问几次， 然后就会发现返回的数据是不一样的，是从三个数据库里面取出来一个， 这就实现了负载均衡 ， 消费者不是只访问一个服务提供者了， 而是访问组测中心里的全部。



​		**这里LoadBalance首先会去服务注册中心获取可用地址列表， 然后通过负载均衡算法， 获取最合适的一个地址， 再去访问， 这样就实现了负载均衡了。我们这里采用的市默认的负载均衡算法， 就是轮询， 每个服务器轮着来。 ** 



### 自定义负载均衡算法

通过ReactorServiceInstanceLoadBalancer这个接口， 我们可以看到有两个系统提供的实现类， 这两个就是 轮询算法， 和随机算法， 另一个是我们自己写的， 点进系统的两个算法， 会发现基本都一样， 就是构造不一样， 还有下面的算法计算不一样， 我们可以自己写一个随机算法， 就按照系统的抄一些， 然后很多东西我们可以省略。



![cloud02](img\cloud02.png)





- 下面来看看我们自己写的负载均衡随机算法， 我们应该另外写一个包， 不被Spring默扫描的包， 也就是在application外的目录的包， 来存放这个类， 以及他的配置类， 因为如果放在了默认扫描的目录下， 那么Spring就会直接扫描到并注册到Spring里面， 会把原来负载均衡算法的覆盖掉，这样就算别的服务需要用， 也会使用我们自己定义的， 而默认的算法就没了。 ：

```java
public class MyRandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public MyRandomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = (ServiceInstanceListSupplier)this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map((serviceInstances) -> {
            return this.processInstanceResponse(supplier, serviceInstances);
        });
    }

    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, List<ServiceInstance> serviceInstances) {
        Response<ServiceInstance> serviceInstanceResponse = this.getInstanceResponse(serviceInstances);
        if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
            ((SelectedInstanceCallback)supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
        }

        return serviceInstanceResponse;
    }
    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        Random random = new Random();
        System.out.println("------------------------------"+instances.size());
        ServiceInstance instance = instances.get(random.nextInt(instances.size()));
        return new DefaultResponse(instance);
    }
}

```



​		**通过获得可用服务的列表， 然后放在一个List里面， 然后通过Random获取里面随机的一个返回，就可以实现随机获取了。 **



- 然后需要把这个算法注册到Spring里面， 就放在Configuration文件里面就行， 也是要在外面不被扫描的目录下。

```java
@Configuration
public class MyConfig {

    @Bean
    public ReactorServiceInstanceLoadBalancer myLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider){
        return new MyRandomLoadBalancer(serviceInstanceListSupplierProvider);
    }

}

```



​		**这里的参数不用管， Spring会自动注入进去， 我们就可以创建实例了。**



- 然后最后还要配置一下启动类：

```java
@SpringBootApplication
@EnableEurekaClient
@LoadBalancerClient(name = "SPRING-PROVIDER-DEPT", configuration = MyConfig.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}

```



​		**这里的@LoadBalancerClient(name = "SPRING-PROVIDER-DEPT", configuration = ConfigBean.class)就是配置负载均衡， name就是服务的名称， 就是8001~8003的名称，configuration 就是我们的配置类 的类型， 表示从里面去获取负载均衡算法。 **



- 然后直接启动， 访问数据库就会发现， 访问的是随机的数据库， 代表访问的服务是按照服务名字里面相同的随机的一个，这样就实现了负载均衡随机算法。 



- 修改负载均衡算法， 定义每个服务器访问五次就换下一个：

```java
private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        Random random = new Random();
        System.out.println("------------------------------"+instances.size());
        int index = (count/5)%3;
        count++;
        ServiceInstance instance = instances.get(index);
        return new DefaultResponse(instance);
    }
```



### Feign



​		我们之前从消费者访问服务者的service是通过RestTemplate来根据服务名称跳转请求的，而这种看起来会很不舒服，链接都是拼接来的，  java万物皆对象， 就把 这个服务名称放在一个对象里面， 这些消费者的请求也放在里面， 然后消费者如果要调用服务， 就只需要拿到这个对象， 然后去用里面的方法调用服务， Feign就是这样实现的。 



- 首先导入依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>3.1.2</version>
</dependency>
```



- 在共模块上面 写一个service， 就是api模块， 这个service用来实现调用到服务端的请求。

```java
@Service
@FeignClient(name = "SPRING-PROVIDER-DEPT")
public interface FeignService {

    @PostMapping("/dept/add}")
    public String addDept(Dept dept);

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

}

```



​		**这里的@FeignClient(name = "SPRING-PROVIDER-DEPT")就是根据名字去找到这个服务， 然后下面的代码实现调用这个服务的请求。 **



- 然后复制一个消费者， 名叫Feign， 配置和消费者的一样， 只需要改controller

```java
@RestController
public class MyController {

    @Autowired
    FeignService feignService;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return feignService.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public String add( Dept dept){
        return feignService.addDept(dept);
    }
    @RequestMapping("/consumer/dept/list")
    public List list(){
        return feignService.queryAll();
    }
}

```



​		**这里就是通过注入上面的FeignService， 来调用里面的方法， 从而调用服务提供者的请求**



- 启动类也需要加上注解@EnableFeignClients

```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.ghj.springcloud")
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
```



​		**这个@EnableFeignClients(basePackages = "com.ghj.springcloud")， 就代表会Spring扫描到com.ghj.springcloud下的带有Feign的注解， 因为api模板是一个子模版， 所有的模板的pom依赖都有包含他， 所以， 我们这个Feign模板也是可以扫描到api模板的com.ghj.springcloud下的注解。**



## Hystrix

​		

### 服务熔断



​		服务熔断，我们的微服务可能是一段连续请求各个服务器的， 如果其中有一个服务器崩了， 那么请求就会过不去， 那么就会一直卡在那边， 消费者就会一直占用这个请求， 越来越多之后就会让整个服务宕机， 这时候我们需要， 有一个备选的服务， 这样如果服务崩了， 就会有备选的服务， 从而返回给用户信息， 这时候就不会一直占用这个资源了， Hystrix就是来做这种的，为了保护服务器，这里都是在服务端做的措施。



下面我们就来实现：



- 首先建复制一个8001的服务， 然后导入以下依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
```



- 其次修改controller，这里我们只保留一个根据id查询的服务

```java
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        Dept dept = deptService.queryById(id);
        if(dept == null){
            throw new RuntimeException("id is null not found");
        }
        return dept;
    }

    private Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept()
                .setDept_no(id)
                .setD_name("id=>" + id + "is not found")
                .setDb_source("no database in MySQL");
    }


}

```



​		**只要加上了@HystrixCommand(fallbackMethod = "hystrixGet")， 就代表如果该方法出现异常， 那么就会调用hystrixGet方法， 也就是我们下面定义的方法， 会返回一个 错误对象的信息， 而不是抛出异常， 出现错误。**



- 最后启动类加上注解：

```java
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
public class DeptProviderHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001.class,args);
    }
}

```



​		**@EnableHystrix就代表该程序支持Hystrix。**、



- 我们还可以给yml配置一下eureka.client.prefer-ip-address: true， 这样我们开启服务的时候， 鼠标悬浮在服务的连接上的时候， 就不是显示localhost:xxxx， 而是127.0.0.1:xxxx这种格式。





### 服务降级



​		服务降级， 当一个服务器访问过多时候， 服务器可能会绷不住， 这时候就需要更多的服务器， 有些服务器就很空闲， 我们可以直接把这个空闲的服务器停了， 去服务那些很火爆的服务， 这样就不至于服务器崩了， 但是会让这个空闲的服务器提供的服务关闭，但是如果用户访问这个服务， 我们并不希望直接报错， 而是有一些错误提示返回。 我们可以通过Feign来实现



- 首先要导入依赖， 在api客户端， 还有Fegin消费者都需要导入， 就是之前Hystrix的依赖。

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
```



- 然后在api模块的服务写一个服务暂停的备用服务， 需要实现FallbackFactory接口。

```java
@Service
public class FeignServiceDe implements FallbackFactory {
    @Override
    public FeignService create(Throwable cause) {
        return new FeignService() {
            @Override
            public String addDept(Dept dept) {
                return "null";
            }

            @Override
            public Dept queryById(Long id) {
                return new Dept()
                        .setDept_no(id)
                        .setD_name("该服务由于服务降级， 暂时暂停服务")
                        .setDb_source("无数据");
            }

            @Override
            public List<Dept> queryAll() {
                return new ArrayList<>();
            }
        };
    }
}

```



- 然后在常用的服务接口关联这个备用的服务， fallbackFactory = FeignServiceDe.class就是加了个这个注解， 出现服务降级就会直接访问这个接口：

```java
@Service
@FeignClient(name = "SPRING-PROVIDER-DEPT", fallbackFactory = FeignServiceDe.class)
public interface FeignService {

    @PostMapping("/dept/add}")
    public String addDept(Dept dept);

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

}

```



- 最后需要在Feign消费者这里加一个配置 ， 开启服务降级

```yml
feign:
  circuitbreaker:
    enabled: true

```



### Dashboard 流监控



​		配置一个Hystrix的监控



- 首先建一个消费者的模块，导入对应的依赖， 还有新的依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
```



- 然后编写yml文件， 修改端口为 9001

- 然后创建启动类， 加上注解开启流监控功能

```java
@SpringBootApplication
/**
 * 开启dashboard
 */
@EnableHystrixDashboard
public class DashboardApp {
    public static void main(String[] args) {
        SpringApplication.run(DashboardApp.class, args);
    }
}
```



- 然后就可以启动， 请求路径http://localhost:9001/hystrix， 然后就可以看到下面的页面：

![cloud03](img\cloud03.png)



- 然后如果我们想要监控某个服务，需要有 @EnableHystrix注解的服务，那么就要去启动类里加一些东西， 这里的服务是之前的hystrix的服务，只需要加那个@Bean就好了， 这里的"/actuator/hystrix.stream" ， 就是上面的页面上的链接的后缀。

```java
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix//对熔断的支持
public class DeptProviderHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001.class,args);
    }
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}

```



- 然后还需要配置dashboard的yml文件

```yml
hystrix:
  dashboard:
    proxy-stream-allow-list: "localhost"
```



- 然后启动一个注册中心， 启动hystrix的服务， 启动dashboard的消费者， 然后访问hystrix的http://localhost:8001/actuator/hystrix.stream， 如果一直在ping， 那么就先发一个get请求， 再请求这个， 然后在各个的dashboard的豪猪页面上输入请求路径， 毫秒数， 还有标题， 点击下面的监视就可以看到页面了

![cloud04](img\cloud04.png)



​		**这里的心跳图就是我们访问请求的频率， 频率越高圆点也越大， 还有请求出错率。**



## Zuul路由网关

