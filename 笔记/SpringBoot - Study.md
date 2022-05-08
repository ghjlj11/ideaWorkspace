



# SpringBoot - Study



## 第一个springBoot程序 



### 从官网下载 

- <img src="img\image-20220422175121144.png">



- 下载后直接在idea里面打开。



### 自己创建



- 原理也是通过官网来下载的
- ![image-20220422175253178](img\image-20220422175253178.png)



- 这里也是通过官网来的。



- 我们的程序可以在maven的生命周期里的package打包出一个jar包， 我们就可以不通过idea，直接运行这个jar包，也可以在浏览器中访问。 实现相应的功能。



- 我们可以在application.properties文件里面加上**server.port=8848**代码，这样我们的tomcat的端口号就被修改成了8848。



- 还可以在resourses包下加上banner.txt文件， 文件里可以自定义banner。





## 原理分析



### pom.xml文件

- ```xml
  <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>3.0.0-SNAPSHOT</version>
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
  ```



- 这里的是包含了父工程下的pom.xml文件里的依赖， 所以我们下面导入依赖的时候， 只需要加上groupID以及artifactId， 对应的版本号不用加上， 因为在父工程下， 这些已经导入好了， 就只有这个版本， 所以不需要加上。



### 注解



- 这里的注解说的是主函数里的@SpringBootApplication这个注解下面包括了三个重要的注解：

  - @SpringBootConfiguration

    - 这个注解下面又包含了@Configuration；说明了这里面就是一个配置类， 包含Spring的所有的配置。

  - @EnableAutoConfiguration**（核心理解）**，下面两个注解

    - @AutoConfigurationPackage。

      - @Import({Registrar.class})，这个就是去找到那些需要的包，然后自动注册进去。

    - @Import({AutoConfigurationImportSelector.class})，这里就是去自动导入那些需要的配置。

      ​     

      **核心 ： **

      

      - ```java
        protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
            if (!this.isEnabled(annotationMetadata)) {
                return EMPTY_ENTRY;
            } else {
                AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
                List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
                configurations = this.removeDuplicates(configurations);
                Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
                this.checkExcludedClasses(configurations, exclusions);
                configurations.removeAll(exclusions);
                configurations = this.getConfigurationClassFilter().filter(configurations);
                this.fireAutoConfigurationImportEvents(configurations, exclusions);
                return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
            }
        }
        ```

      

      

      **补充英语：Duplicates ：完全一样的东西;复制品;**

      **Exclusions：排斥; 排除在外; **

      **Candidates：候选人，申请人;                                                                                                                                                **

      

      

      

      ​		**这里就是比较重要的是自动导入配置文件的代码， 首先getCandidateConfigurations方法会获得，spring里面的所有的配置， 然后再加上自己项目里面需要的一些配置，然后通过removeDuplicates方法， 把里面一些重复导入的配置删除，通过getExclusions方法获得那些spring里面一些需要排除的配置， 然后再在configurations里面把这些都删除， 这样configurations里面就都是我们需要的并且不会重复的那些配置 。  **

    

    

    - ```java
      protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
          List<String> configurations = new ArrayList(SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader()));
          ImportCandidates var10000 = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader());
          Objects.requireNonNull(configurations);
          var10000.forEach(configurations::add);
          Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
          return configurations;
      }
      ```

  

  

  ​		**List<String> configurations = new ArrayList(SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader()));   通过这个方法去加载spring里面的所有可以自动配置的类，就是系统资源, 会把META-INF/spring.factories里的资源都放在Properties里面， 也就是下面这个方法**

  

  ​		**this.getSpringFactoriesLoaderFactoryClass()这个方法返回为EnableAutoConfiguration.class， 就是去找到spring里面所有标注了EnableAutoConfiguration的类**

  

  ​		**this.getBeanClassLoader()这个返回的是this.beanClassLoader， 就是返回一个加载器， 去加载那些自动装配的类**
  
  
  
  ​		**ImportCandidates var10000 = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader());这个步骤就是去加载带有注释AutoConfiguration的类**
  
  

  

  ```java
    public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
          ClassLoader classLoaderToUse = classLoader != null ? classLoader : SpringFactoriesLoader.class.getClassLoader();
          String factoryTypeName = factoryType.getName();
          return (List)getAllFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
      }
  ```
  
    
  
  **ImportCandidates var10000 = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader());  这个方法就是去获取我们需要用到的 那些配置  ， 就是项目里需要的资源 。 **
  
  ```java
  public static ImportCandidates load(Class<?> annotation, ClassLoader classLoader) {
          Assert.notNull(annotation, "'annotation' must not be null");
          ClassLoader classLoaderToUse = decideClassloader(classLoader);
          String location = String.format("META-INF/spring/%s.imports", annotation.getName());
          Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
          ArrayList autoConfigurations = new ArrayList();
  
          while(urls.hasMoreElements()) {
              URL url = (URL)urls.nextElement();
              autoConfigurations.addAll(readAutoConfigurations(url));
          }
  
          return new ImportCandidates(autoConfigurations);
      }
  ```
  
  
  
  

  **removeDuplicates去重的操作**

  

  ```java
  protected final <T> List<T> removeDuplicates(List<T> list) {
      return new ArrayList(new LinkedHashSet(list));
  }
  ```
  
  
  
  
  
  
  
  
  
  - ```java
    @ComponentScan(
        excludeFilters = {@Filter(
        type = FilterType.CUSTOM,
        classes = {TypeExcludeFilter.class}
    ), @Filter(
        type = FilterType.CUSTOM,
        classes = {AutoConfigurationExcludeFilter.class}
    )}
    )
    ```



这里的自动配置的文件都在这个包里面，也就是上面代码上的 “ No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct ” .这里面的。

![image-20220423173948012](img\image-20220423173948012.png)







### run方法

run方法是SpringApplication这个类里面的 ， 而这个类主要做的事情就是

- 推断这个项目是普通项目还是Web项目。 
- 查找并加载 所有的可用初始化器， 设置到initializer属性中。
- 查找所有应用的程序监听器， 设置到listeners属性中。
- 推断并设置main方法的定义类， 找到运行的主类，





## yaml



- 可以代替原本的properties文件。



### 基本语法

```yaml
#k=v
#就是k，v键值对
#对空格的要求很高，就是如果下面的port前面没有空格的话， 那就是两个键值对， 就是port并不是server里面的。

server:
  port: 8848

#普通键值对

name: ghj

#对象

student:
  name: ghj
  age: 3

people: {name: ghj,age: 5}
#数组
array:
  - a1
  - a2
  - a3

arr: [a,b,c]
```



### yaml通过set方式来给对象注入值。



我们先创建一个Dog类

```java
@Component
@Data
public class Dog {
    @Value("嘻嘻")
    private String name;
    @Value("12")
    private int age;

}

```





创建一个people类

**需要给被配置的类加上两个注解， @Configuration代表这是一个配置类，@ConfigurationProperties(prefix = "people")代表**

**这个类的配置在配置文件里的 以 “people” 开头的配置上面  。**

```java
@Component
@Configuration
@ConfigurationProperties(prefix = "people")
@Data
public class People {
    private String name;
    private int age;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;
}

```



然后我们通过yaml文件向people注入值



```yaml


people:
  name: ghj
  age: 21
  map: {k1: v1,k2: v2}
  list:
    - a
    - b
    - c
  dog:
    name: 旺财
    age: 3
```





创建测试类

```java
@SpringBootTest
class SpringBootStudy01ApplicationTests {

    @Autowired
    private People people;
    @Test
    void contextLoads() {
        System.out.println(people);
    }

}

```



- 上述注入的值依然可以注入；



### yaml配置十分灵活



```yaml

people:
  name: ghj${random.uuid}
  age: ${random.int}
  map: {k1: v1,k2: v2}
  hello: xx
  list:
    - a
    - b
    - c
  dog:
    name: ${people.hello:ak}_旺财
    age: 3
```



- 这里可以运用一些占位符， random随机获得，**${people.hello:ak}_旺财**就是表示拼接people的hello属性的值，和旺财， 如果hello没有那么就会取到ak。



#### yaml的松散配置



- 在yaml里面可以允许：

```yaml
dog:
  dog-name: 旺财
  age: 12
```



但是Dog类是： 

```java
private String dogName;
    private int age;
```



- 这里的Dog类的属性名是dogName， 然而在yaml里面配置的是dog-name也是可以的， 然后要注意 ： 要把对应的setter修改好名字， 要不然 值 会 注入不成功。



#### JSR303校验



我们可以对我们注入的值进行校验。 



- 首先在pom.xml配置

```xml
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
 </dependency>
```



- 然后可以给Dog的属性加上校验， 需要在类上加上**@Validated注解**。

```java
@Email(message = "格式错误")
    private String dogName;
    private int age;
```



message的值也可以不写入， 有对应的默认值。

- 这样运行的话，就会校验报错了

![image-20220424100028285](img\image-20220424100028285.png)





## config文件



### 配置的优先级



​		**关于配置文件的详情， 可以看看这个类ConfigFileApplicationListener**



官网下说配置文件可以放在这四个地方。

![image-20220424103125933](img\image-20220424103125933.png)



**这里的file就是项目，classpath就是 src下的 resources文件夹， 项目下的config下的配置文件优先级最高， 其次是项目直接下面的配置文件， 然后是classpath下的config里的配置文件， 最后才是classpath下直接的配置文件。**



### 多环境开发



```yaml
server:
  port: 8081

spring:
  profiles:
    active: test
---
server:
  port: 8082
spring:
  config:
    activate:
      on-profile: dev
---
server:
  port: 8083
spring:
  config:
    activate:
      on-profile: test

---
server:
  port: 8083
spring:
  config:
    activate:
      on-profile: main
```





- 我们在yaml配置中可以配置多套环境， 根据自己的需求来选择哪一个环境：

  

- ```yaml
  spring:
    config:
      activate:
        on-profile: main
  ```



这里就是给一套环境加上一个名字；



- ```yaml
  spring:
    profiles:
      active: test
  ```



这里就是根据名字选择哪一套环境。



## 原理再探究



自己定义springBoot里的配置。



- 这些自动配置的类， 他们都有相同的注解

  ```java
  @Configuration(
      proxyBeanMethods = false
  )
  @EnableConfigurationProperties({ServerProperties.class})
  @ConditionalOnWebApplication(
      type = Type.SERVLET
  )
  @ConditionalOnClass({CharacterEncodingFilter.class})
  @ConditionalOnProperty(
      prefix = "server.servlet.encoding",
      value = {"enabled"},
      matchIfMissing = true
  )
  ```



就比如这个， 他会有一个@Configuration， 就是表示这个是一个配置类；@ConditionalOn就是表示判断，只有后面的条件满足，才会去加载这个配置；点进每个@EnableConfigurationProperties({ServerProperties.class})后面的.class后，我们都会看到有

```java
@ConfigurationProperties(
    prefix = "server",
    ignoreUnknownFields = true
)
```



这个注解， 而这个注解就是java的类与yaml配置联系的注解， 通过这个就可以 在 yaml文件里面去配置这个类的一些值，所以**@EnableConfigurationProperties**这个注解就是表示可以自动配置的文件， 后面的ServerProperties这个类里面会有默认值， 我们也可以通过yaml文件自己来配置。

![image-20220424194539589](img\image-20220424194539589.png)



​		**在我们的资源中会有大量的  XXXAutoConfigration类文件， 这些类里面有很多属性，而里面会有一些XXXProperties的文件， 这些文件可以通过我们的配置文件去自定义里面的属性，这里面的所有配置类都是需要有启动器starter才可以匹配到， 要不然就不可以配置这个类， 也不可以使用。**



​		**在一开始springBoot会自动装配很多配置类， 这些里面会有默认值， 如果我们需要去修改， 只需要在配置文件里修改就可以了。**



​		**在配置文件里面加上debug: true， 就可以看到哪些配置类匹配上了， 那些没有。**





## 静态资源导入



```java
classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/,
```



这些路径下都可以放静态资源， 优先级就是按照这些顺序来的， /META-INF/resources/是外部导入的库配置里的。



## 首页与图标



- 首页需要查看**WebMvcAutoConfiguration**这个配置类， 里面有大量配置MVC里的类与方法， 其中有**welcomePageHandlerMapping()、getWelcomePage()、getIndexHtml(Resource location)**



- 在**WebProperties.class文件下我们可以看到之前看到的Resources目录其实就是我们项目里的classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/这些目录 ， 就是放置静态资源的目录。**

![image-20220425175813866](img\image-20220425175813866.png)





- 图标的话就是在我们的Resources下也就是上面的那些路径下，放上一个favicon.ico图片文件，这个文件就是我们网站的图标。

![image-20220425193815144](img\image-20220425193815144.png)



## Thymeleaf模板引擎



- 用这个模板引擎来支持前后端数据交互， 通过这个模板引擎我们的Controller就可以跳转到templates下的html文件， 当然这是一个配置类， 可以查看这个类的属性ThymeleafProperties， 这里面有很多属性可以配置， 包括了prefix和suffix，通过这两个属性， 我们就可以配置跳转到别的路径下的html文件。



- 首先我们需要导入这个配置类的启动器。

```xml
<!--        <dependency>-->
<!--            <groupId>org.thymeleaf</groupId>-->
<!--            <artifactId>thymeleaf-spring6</artifactId>-->
<!--        </dependency>-->

<!--        <dependency>-->
<!--            <groupId>org.thymeleaf.extras</groupId>-->
<!--            <artifactId>thymeleaf-extras-java8time</artifactId>-->
<!--        </dependency>-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

```



- 然后创建我们的html页面，这里注意要加**xmlns:th="http://www.thymeleaf.org"**， 这样我们就可以使用他了， 就像vue一样， th:text就是用msg绑定div里的文本。

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
<div th:text="${msg}"></div>
</body>
</html>
```



- 我们还需要在Controller创建一个Controller， 然后加上model， 和addAttribute， 这样就可以用了。 这里不可以用#来取msg里的值， 会乱码。



- th:utext , 这个不转译一些代码， 就比如上面的msg的为“<h1>你在搞什么啊!</h1>”，这个就可以直接以h1显示



- th:each，如果后端传来的是一个List对象， 我们可以通过th:each来遍历， <h3 th:each="li:${list}" th:text="${li}"></h3>，同时通过 th:text来绑定这个h3的文本。我们也可以通过<h3 th:each="li:${list}">[[${li}]]</h3>遍历， 但是还是建议第一种。



- controller的代码：

```java
@Controller
public class SpringBootController {

    @RequestMapping("/hello")
    public String test1(Model model){
        model.addAttribute("msg","<h1>你在搞什么啊!</h1>");
        model.addAttribute("list", Arrays.asList("ghj","lj","lq"));
        return "hello";
    }
}

```



- html的代码：

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
<div th:text="${msg}"></div>
<div th:utext="${msg}"></div>
<hr>
<h3 th:each="li:${list}" th:text="${li}"></h3>
<hr>
<h3 th:each="li:${list}">[[${li}]]</h3>
</body>
</html>
```



- 结果



![image-20220429230948644](img\springBoot01.png)





## SpringMVC自动装配



### 扩展MVC配置



- Spring Boot 提供了适用于大多数 Spring MVC 应用的自动配置（auto-configuration）。

自动配置在 Spring 默认功能上添加了以下功能：

- 引入 `ContentNegotiatingViewResolver` 和 `BeanNameViewResolver` bean。
- 支持服务静态资源，包括对 WebJar 的支持（[见下文](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/spring-boot-features.html#boot-features-spring-mvc-static-content)）。
- 自动注册 `Converter`、`GenericConverter` 和 `Formatter` bean。
- 支持 `HttpMessageConverter`（见[下文](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/spring-boot-features.html#boot-features-spring-mvc-message-converters)）。
- 自动注册 `MessageCodesResolver`（见[下文](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/spring-boot-features.html#boot-features-spring-message-codes)）。
- 支持静态 index.html。
- 支持自定义 Favicon （见[下文](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/spring-boot-features.html#boot-features-spring-mvc-favicon)）。
- 自动使用 `ConfigurableWebBindingInitializer` bean（见[下文](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)）。



- 如果您想保留 Spring Boot MVC 的功能，并且需要添加其他 [MVC 配置](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc)（interceptor、formatter 和视图控制器等），可以添加自己的 `WebMvcConfigurerAdapter` 类型的 `@Configuration` 类，但**不能**带 `@EnableWebMvc` 注解。如果您想自定义 `RequestMappingHandlerMapping`、`RequestMappingHandlerAdapter` 或者 `ExceptionHandlerExceptionResolver` 实例，可以声明一个 `WebMvcRegistrationsAdapter` 实例来提供这些组件。

- 如果您想完全掌控 Spring MVC，可以添加自定义注解了 `@EnableWebMvc` 的 @Configuration 配置类。

  ​																																										------------------摘自官网。



​		 从官网的话我们可以知道， SpringMVC的很多功能我们可以自己来添加， 我们只需要写一个 WebMvcConfigurerAdapter配置类 ，通过全文查找， 就知道WebMvcConfigurerAdapter是一个接口， 所以我们只需要写一个类继承这个接口就可以， 并且需要加上@Configuration的注解，代表是一个配置类， 在里面定义我们自己的配置。  



### 自己定义一个视图解析器



- 官网说他的SpringMVC默认引入引入 `ContentNegotiatingViewResolver` 和 `BeanNameViewResolver` bean。我们就去找这两个类，他们都需要去实现ViewResolver这个接口， 通过ContentNegotiatingViewResolver， 我们发现他的核心就是去获取所有的视图解析器， 然后去获取所有的视图，再从其中获取最好的视图（最合适的）。 
- ![boot02](img\boot02.png)





​		因此我们只需要实现ViewResolver接口， 就可以写我们自己的视图解析器， 因为我们外部的类是一个配置类， 带有@Configuration所以需要加上带有@Bean的方法获取bean， 我们通过内部类实现。

```java
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MyViewResolver getMyViewResolver(){
        return new MyViewResolver();
    }
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

}

```



- 下一个阶段：  我们该如何知道我们自己的视图解析器被用上了 呢， 我们在SpringMVC阶段都知道， 所有的请求都需要 通过DispatchServlet（前端控制器），这个控制器的核心方法就是 doDispatch， 我们在这里打上断点， 再Debug一下， 然后请求一下hello页面。

![boot3](img\boot3.png)



​		**这里就可以看到了， 前面两个就是 官方说的那两个视图解析器， 而下一个就是Thymeleaf的视图解析器， 再下一个就是我们自己的视图解析器了。**





## @EnableWebMvc



- 上面官方特地强调说不能使用这个@EnableWebMvc， 那么是为什么呢 ？ 点卡这个注解， 我们就会发现这里面import了一个类， 就是**DelegatingWebMvcConfiguration**， 再点进去会发现这个类继承了**WebMvcConfigurationSupport**这个类。 

![boot4](img\boot4.png)



![boot5](img\boot5.png)





- 然后我们再看**WebMvcAutoConfiguration**的源码，这里面有一个条件。

![boot6](img\boot6.png)





- **@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})**， 表示如果没有**WebMvcConfigurationSupport**这个类，**WebMvcAutoConfiguration**这个自动配置类才会生效， 所以如果我们加上了那个注解， 那么就表示 **WebMvcAutoConfiguration**将不会被自动装配， 那么里面所以功能我们都用不了。 





## Configuration和AutoConfiguration



Configuration和AutoConfiguration 

- 他们都是定义Bean 的一种方式。 

- 相同点：     
  - 他们都用@Configuration 做annotation. 他们同样都可以加  @Bean、@Import、@ImportResource.     他们都可以用 @Condition 来控制加载条件. 

- 不同点：
  - 目的：      @Configuration 是给Application 的用户，直接代码进行配置的。     AutoConfiguration 是给 Springboot 插件俗称starter用的。
  - 加载的方式:      @Configuration 加载是由@ComponentScan指定的package，如果没有指定，default  是ApplicationClass 的package 开始。     AutoConfiguration 是通过 classpath*:META-INF/spring.factories 来被发现。 通过  key org.springframework.boot.autoconfigure.EnableAutoConfiguration.  AutoConfiguration 是由 import selector 的方式加载的, 而不是 scan path. 
  - 顺序：正常情况下, @Configuration 先加载 AutoConfiguration后加载。



## Web项目员工管理系统



### 准备工作；

- 先导入对应的Web项目， 将静态资源放在templates包下；
- 己写一个controller，请求路径为“/”， 先看看能不能跳转到首页， 然后再用thymeleaf格式， 把链接之类的都改掉， 这样就可以适配我们的thymeleaf模板。



### 国际化



- 首先我们自己写三个配置文件， 分别对应index.html里的默认的文字， 中文文字， 英文文字

```prop
login.password=密码
login.remember=记住我
login.sign=登录
login.tip=请登录
login.username=用户名
```



```pro
login.password=Password
login.remember=Remember me
login.sign=Sign in
login.tip=Please sign in
login.username=Username
```





- 把index.html里的文字设置成， 从配置文件里面取值  注意这里的传参我们采用**@{/index(l='zh_CH')}这种() 的格式传参**。

```html
<body class="text-center">
		<form class="form-signin" action="dashboard.html">
			<img class="mb-4" th:src="@{/img/bootstrap-solid.svg}" alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
			<input type="text" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
			<input type="password" class="form-control" th:placeholder="#{login.password}" required="">
			<div class="checkbox mb-3">
				<label>
          <input type="checkbox" value="remember-me" th:text="#{login.remember}">
        </label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.sign}">Sign in</button>
			<p class="mt-5 mb-3 text-muted">© 2017-2018</p>
			<a class="btn btn-sm" th:href="@{/index(l='zh_CH')}">中文</a>
			<a class="btn btn-sm" th:href="@{/index(l='en_US')}">English</a>
		</form>

	</body>
```





- 在我们页面中会有中英文切换的选项。 我们也可以实现它。这也是WebMvc里的配置， 所以我们去找WebMvcAutoConfiguration这个配置类， 找到 localeResolver()，这个装配了Bean的方法， 注意到名字是localeResolver， 然后点进里面， AcceptHeaderLocaleResolver。

  

  ![boot7](img\boot7.png)





- ```java
  public Locale resolveLocale(HttpServletRequest request) {
          Locale defaultLocale = this.getDefaultLocale();
          if (defaultLocale != null && request.getHeader("Accept-Language") == null) {
              return defaultLocale;
          } else {
              Locale requestLocale = request.getLocale();
              List<Locale> supportedLocales = this.getSupportedLocales();
              if (!supportedLocales.isEmpty() && !supportedLocales.contains(requestLocale)) {
                  Locale supportedLocale = this.findSupportedLocale(request, supportedLocales);
                  if (supportedLocale != null) {
                      return supportedLocale;
                  } else {
                      return defaultLocale != null ? defaultLocale : requestLocale;
                  }
              } else {
                  return requestLocale;
              }
          }
      }
  ```



- 这里就是 国际化 的解析了， 我们照着这个思路类似写一个类 实现LocaleResolver。



```java
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String la = request.getParameter("l");
        Locale locale = Locale.getDefault();
        System.out.println(la);
        if(!StringUtils.isEmpty(la)){
            String[] s = la.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

```



​	**先判断参数 l 是否为空， 如果是那么就返回默认的 locale，否则 就返回我们自己创建的。 并且要在 我们自己写的 WebMvc配置类里面注册， 因为 这是属于我们自己添加的配置， 需要交给Spring里面**



- ```java
  package com.ghj.config;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.servlet.LocaleResolver;
  import org.springframework.web.servlet.View;
  import org.springframework.web.servlet.ViewResolver;
  import org.springframework.web.servlet.config.annotation.EnableWebMvc;
  import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
  import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
  
  import java.util.Locale;
  
  /**
   * @author 86187
   */
  @Configuration
  public class MyWebMvcConfig implements WebMvcConfigurer {
  
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          registry.addViewController("/").setViewName("index");
          registry.addViewController("/index").setViewName("index");
      }
  
      @Bean
      public LocaleResolver localeResolver(){
          return new MyLocaleResolver();
      }
  
  }
  
  ```



- 注意这里的方法名字要是localeResolver，这与之前的对应， 然后我们在index.html里面设置参数就好了。 



### 实现登录功能与拦截器



#### 登录



登录功能很简单， 只需要接收前端的 用户名与密码， 在进行判断 ， 选择跳转的页面就行。



#### 拦截器

​		这里的拦截器跟我们之前写的是一样的 ， 只不过这个拦截器需要在自己的WebMvcConfig里面添加， 通过重写方法来添加拦截器 ，然后选择拦截的请求。



- 首先我们需要在Controller里的session添加Attribute ， 这里还有注销功能， 也是和之前一样。

```java
@Controller
public class MyController {
    @RequestMapping("/toLogin")
    public String test01(@RequestParam("username")String username, @RequestParam("password")String password , Model model, HttpSession session){
        if("ghjlj".equals(username) && "123456".equals(password)){
            session.setAttribute("Login", username);
            return "redirect:/main";
        }
        else {
            model.addAttribute("msg", "用户名或密码错误");
        }
        return "index";
    }
    @RequestMapping("/out")
    public String test02(HttpSession session){
        session.removeAttribute("Login");
        return "redirect:index";
    }
}

```



- 然后再写一个拦截器 实现handlerInterceptor接口。

```java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("Login");
        if(login == null){
            request.setAttribute("msg", "未登录没有权限访问");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }
        return true;
    }
}
```



 		**这里只需要重写这个preHandle方法， 就是进入下一个之前， 然后我们做一些判断， null就重定向原来的登录页面， 并返回msg， 否则就放行**



- 然后是在

```java
@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index",
                "/toLogin","/css/**","/img/**","/js/**");
    }
```



​		**这里需要先拦截所有的请求， 然后选择 一些需要放行， static下的css、img、js静态资源也需要放行**



### 员工列表管理



- 首先我们先把dashboard与list页面的那些链接都改好 ， 侧边栏的链接到对应的请求， 在Controller里加上list页面的请求。 



- 我们在dashboard与list页面里面会有大量的重复代码， 这里的侧边栏与顶部栏都是一样的， 然后我们就可以使用thymeleaf的fragment ， 我们把重复的部分都放在一个html文件里面， 语法： 只需要在 class后面加上 

   **th:fragment="topbar"**

  ![boot8](img\boot8.png)



- 然后我们直接在 需要用到的地方 直接引用， **<div th:insert="~{components/component::topbar}"></div>** ，<div th:replace="~{components/component::sidebar(active='list')}"></div> 使用insert或者replace语法插入，括号之前需要加上~， 括号里面就是 定义的 这个fargment的地址。 



- 我们还需要设置高亮， 就是当前所在的页面， 侧边栏对应的位置高亮， ，只需要携带一个参数， 然后在 我们的组件里面接收， 并且判断是在哪个页面下， 然后使用 active 高亮， 这个参数可以是在前端页面 通过thymeleaf 模板 在连接后面加上一个()， 里面写参数， 如上代码。 对应的 组件里面接收：

```html
<a th:class="${active == 'main' ? 'nav-link active' : 'nav-link' }" th:href="@{/main}">
    
<a th:class="${active == 'list' ? 'nav-link active' : 'nav-link' }" th:href="@{/emps}">
```



​		**这里判断使用的是三目运算符， 要注意语法格式。**

- 最后就是员工展示， 通过 Controller 去获取 Dao里的 数据， 并获取， 所有的员工放在集合里传递给前端， 这时候我们就可以使用thymeleaf的 th:each来遍历里面的数据， 并且通过#dates.format格式化date类型。

```html
<tr th:each="emp:${emps}">
	<td th:text="${emp.getId()}"></td>
	<td th:text="${emp.getName()}"></td>
	<td th:text="${emp.getEmail()}"></td>
	<td th:text="${emp.getSex() == 1 ? '男':'女'}"></td>
	<td th:text="${emp.getDepartment().getName()}"></td>
	<td th:text="${#dates.format(emp.getBrith(), 'yyyy-MM-dd : HH:mm:ss')}"></td>
	<td>
		<button class="btn btn-sm btn-primary">修改</button>
		<button class="btn btn-sm btn-danger">删除</button>
	</td>
</tr>
```



- 列表添加功能 ：增加 ， 更新， 删除。 这里我们如果前端传对象到后端 ， 我们只传 id ， 然后通过 id在后端查询， 删除等功能 ， 要注意时间显示的格式， 以及格式化， 一些前端的判断需要注意格式。



- 这里我们使用restful风格来写删除功能：

  - 首先写Controller

  - ```java
        @GetMapping("/delete/{id}")
        public String delete(@PathVariable("id") Integer id){
            employeeDao.deleteEmployee(id);
            return "redirect:/emps";
        }
    ```

  - 然后写html的链接

  - ```html
    <a th:href="@{/delete/{id}(id=${emp.getId()})}"><button class="btn btn-sm btn-danger">删除</button></a>
    ```

    **这里的连接的格式需要注意**





- 404问题， 我们只需要在templates下创建一个error文件夹， 然后把我们的404页面丢进去， 当我们浏览器状态码是404的时候， 就会自动跳到那边， 还有500 也是一样。



## 如何写一个网站



- 前端；
- 设计数据库；
- 前端能够独立运行；
- 数据接口如何对接：json
- 前后端联调测试
- 需要有一套自己收悉的后台模板
- 前端界面通过框架搭建；
- 可以运行。





## JDBC



- 我们正常创建一个web项目， 导入jdbc依赖， mysql依赖

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```



- 配置datasource参数， 然后我们就可以链接数据库了。可以查看一下源码。 

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/malajava?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
```



- JdbcTemplateConfiguration这个配置类注入了JdbcTemplate到spring中，我们可以直接使用， 这是一个jdbc的模板类， 可以直接使用里面的功能。 

```java
@RestController
public class DataController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/que")
    public String select(){
        String s = jdbcTemplate.queryForList("select * from user").toString();
        return s;
    }
    @GetMapping("/add")
    public String add(){
        String sql = "insert into user(id, name, age) values(4, 'ghj', 12)";
        jdbcTemplate.update(sql);
        return "ok";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id){
        String sql = "update user set name = ?, age = ? where id = "+ id;
        Object[] objects = new Object[2];
        objects[0] = "kkk";
        objects[1] = 21;
        jdbcTemplate.update(sql,objects);
        return "ok";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        String sql = "delete from user where id = ?";
        Object j = new Integer(4);
        jdbcTemplate.update(sql,j);
        return "ok";
    }
}

```



​		**这里我们可以通过？来现有一个占位， 然后通过下面的Object给？传参， 这样的效果也是一样的。**



## Druid



- 我们引入Druid数据源， 不能使用SpringBoot3.0.0快照版， 降版本到2.6.7即可使用。 



- 首先导入maven， log4j也要导入。

  ```xml
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.2.8</version>
  </dependency>
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.2.8</version>
  </dependency>
  
  <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
  </dependency>
  ```



- 然后添加yml配置

  ```yml
  spring:
    datasource:
      username: root
      password: 123456
      url: jdbc:mysql://localhost:3306/malajava?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: com.alibaba.druid.pool.DruidDataSource
  
      initialSize: 5
      minIdle: 5
      maxActive: 20
      maxWait: 60000
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
  
      #配置监控统计拦截的filters，stat：监控统计、log4j：日志记录、wall：防御sql注入
      #如果允许报错，java.lang.ClassNotFoundException: org.apache.Log4j.Properity
      #则导入log4j 依赖就行
      filters: stat,wall,log4j
      maxPoolPreparedStatementPerConnectionSize: 20
      useGlobalDataSourceStat: true
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
  
  ```

  

- 配置监控器， 自己写一个config文件， 并注入到Spring中去。

```java
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 因为SpringBoot包含了servlet， 没有对应的web.xml文件， 所以我们不能设置到web.xml
     * 但是因为他在SpringBoot里面， 我们可以通过@Bean设置到里面， 自己加一些配置。
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<>();
        map.put("loginUsername","ghj");
        map.put("loginPassword","123456");
        map.put("allow","");
        bean.setInitParameters(map);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<>();
        map.put("exclusions","*.jsp, *.css, /druid/*");
        bean.setInitParameters(map);
        return bean;
    }
}

```



​		**这里如何让yml配置文件与我们的config绑定在一起， 就是以前的操作， 加上一个@ConfigurationProperties(prefix = "spring.datasource")， 然后注入到Spring中**



​		**然后是配置 监视器，设置登录名， 密码， 以及允许哪个用户访问， 这里就是允许所有的， 这些配置都可以通过一个map来配置， 只需要 key是对应的属性名， 然后value就是值。 **

​		**最后就是配置过滤器，map.put("exclusions","*.jsp, *.css, /druid/*")， 表示哪些请求将会被过滤。 **



![boot9](img\boot9.png)





## SpringBoot的默认扫描的位置



- 默认位置就是@SpringBootApplication所在的包， 所以我们在这个包内建的component， @Repository， @Service， @Controller都可以直接扫描到， 不需要手动配置ComponentScan。

- 加上这个配置

  ```java
  @SpringBootApplication(scanBasePackages = { "com.ghj"})
  ```



- 点开这个scanBasePackages， 我们可以看到里面默认的配置就是 @SpringBootApplication所在的包下

![boot10](img\boot10.png)



## 整合mybatis



- 老规矩， 先导依赖

```xml
		<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```



​		**这里可以看到mybatis是直接以mybatis开头， 不是spring开头， 所以我们配置对应的yml文件时候也是以mybaitis开头**



- 配置对应的yml文件

```yml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/malajava?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  type-aliases-package: com.ghj.pojo
  mapper-locations: classpath:mybatis/mapper/*.xml



```



​		**前部分是链接数据库的， 后部分就是配置mybatis， 配置别名扫描的包， 配置mapper文件的位置。**



- 接下来就是老套路，pojo实体类，  mapper层， service层， controller层。



## Spring Security



​			一般访问网站会有权限设置， 有些页面并不是所有人都可以访问， 我们可以对不同的用户设置一些相应的权限，实现这功能。 



- 首先导入依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```



- 把静态资源抄一份。

- 配置controller，这里有一些新套路， 一共九个页面， 我们只用三个@RequestMapping就好了， 通过传过来的id， 对String进行拼接， 实现跳转到不同的页面。

  ```java
  @RequestMapping("/level1/{id}")
  public String level1(@PathVariable("id")int id){
      return "views/level1/" + id;
  }
  @RequestMapping("/level2/{id}")
  public String level2(@PathVariable("id")int id){
      return "views/level2/" + id;
  }
  @RequestMapping("/level3/{id}")
  public String level3(@PathVariable("id")int id){
      return "views/level3/" + id;
  }
  ```



- 然后就是配置config， 这里继承一个WebSecurityConfigurerAdapter类，然后设置里面的属性。 

  ```java
  @EnableWebSecurity
  public class MyConfig extends WebSecurityConfigurerAdapter {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
          http.authorizeHttpRequests()
                  .antMatchers("/").permitAll()
                  .antMatchers("/level1/**").hasRole("vip1")
                  .antMatchers("/level2/**").hasRole("vip2")
                  .antMatchers("/level3/**").hasRole("vip3");
  
          http.formLogin();
      }
  
      @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                  .withUser("ghjlj").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                  .and()
                  .withUser("ghj").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
                  .and()
                  .withUser("lj").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3");
      }
  }
  
  ```

  



​		**@EnableWebSecurity一定要加这个注解， 然后通过 链式编程，设置每个界面的访问级别 ，再根据用户名与密码来设置用户的级别， 这里的密码一定要有BCryptPasswordEncoder， 代表加密的意思， 然后还要设置对应的级别，这里的http.formLogin()表示 如果未登录访问到没有权限的页面， 那么就会跳转到登录页面，是系统给定的一个登录页面， 并不是我们自己写的。  **



​		**还有注销页面， 我们只需要在配置类上加上一行代码就可以了， 而且这个页面也是系统自带的。**

```java
       http.logout().logoutSuccessUrl("/");
```



### thymeleaf 整合 security



- 导入依赖：

```xml
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
    <version>3.0.4.RELEASE</version>
</dependency>
```



- 再index.html上整合这两个 ,这里的isAuthenticated()就是判断 是否登录的意思sec就是整合后的标签。

```html
<div sec:authorize="!isAuthenticated()">
    <a class="item" th:href="@{/login}">
        <i class="address card icon"></i> 登录
    </a>
</div>

<div sec:authorize="isAuthenticated()">
    <a class="item">
        用户名<span sec:authentication="name"></span>
        角色<span sec:authentication="principal.authorities"></span>
    </a>
</div>
<div sec:authorize="isAuthenticated()">
    <a class="item" th:href="@{/logout}">
        <i class="address card icon"></i>注销
    </a>
</div>
```



- 需要加上命名空间：xmlns:sec="http://www.thymeleaf.org/extras/spring-security"



- 还可以设置展示的页面， 根据vip级别来展示。

```html
<div class="column" sec:authorize="hasRole('vip1')">
    <div class="ui raised segment">
        <div class="ui">
            <div class="content">
                <h5 class="content">Level 1</h5>
                <hr>
                <div><a th:href="@{/level1/1}"><i class="bullhorn icon"></i> Level-1-1</a></div>
                <div><a th:href="@{/level1/2}"><i class="bullhorn icon"></i> Level-1-2</a></div>
                <div><a th:href="@{/level1/3}"><i class="bullhorn icon"></i> Level-1-3</a></div>
            </div>
        </div>
    </div>
</div>
```



​		**这里就是通过hasRole('vip1')这个方法来判断是否包含了这个vip1， 如果有就展示， 下面的也是一样。**



### 首页定制及记住我 



- 首页定制， 我们只需要把上面的代码改一点点就好了， 把首页映射到我们自己的首页， 而下面这个代码就是为了 注销可以成功， 如果没有这个的话那么可能注销会404。

```java
http.formLogin().loginPage("/login");

http.csrf().disable();
```



- 记住我选项 

```java
http.rememberMe().rememberMeParameter("rememberMe");
```



```html
<div class="field">
    <input type="checkbox" name="rememberMe"> 记住我
</div>
```



​		**我们只需要加上这个两个配置 ， 记住我选项， 然后后面的就是获得的参数， 前端加上一个复选框， 然后参数名字需要一至， 这样后端才可以 接收到。记住我的原理其实就是浏览器的一个cookie， 他会记住我们之前的 数据， 然后下次登录就会记住我们的账号信息， 不用登陆， 如果删除这个cooki ， 那么就不会有这个效果**



- 在记住我之前，并没有这个cooki：

![boot11](img\boot11.png)



- 勾选记住我登录之后 ， 就会有这个cookie：



![boot12](img\boot12.png)





## shiro



- 这也是一个为程序提供安全的一个东西， 就和之前的Spring Security类似。
- **这里面有三个要素， Realm， Subject， 和shiroSecurityManager** 



### 第一个shiro程序， 非Web程序

- 首先我们去shiro的官网， 然后找到quickstar， 然后去github上打开，如果是直接在官网上复制代码， 可能会出问题，  找到对应的文件夹， 把里面的依赖都导入进来。

```xml
<dependencies>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.9.0</version>
        </dependency>

        <!-- configure logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>1.7.36</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.17.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.17.2</version>
        </dependency>
</dependencies>
```





- 然后是配置文件， resources下的文件复制过来。 这里有一个.ini文件， 我们需要下载一个插件 Ini， 然后重启idea。 

```ini
# =============================================================================
# Tutorial INI configuration
#
# Usernames/passwords are based on the classic Mel Brooks' film "Spaceballs" :)
# =============================================================================

# -----------------------------------------------------------------------------
# Users and their (optional) assigned roles
# username = password, role1, role2, ..., roleN
# -----------------------------------------------------------------------------
[users]
# user 'root' with password 'secret' and the 'admin' role
root = secret, admin
# user 'guest' with the password 'guest' and the 'guest' role
guest = guest, guest
# user 'presidentskroob' with password '12345' ("That's the same combination on
# my luggage!!!" ;)), and role 'president'
presidentskroob = 12345, president
# user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
darkhelmet = ludicrousspeed, darklord, schwartz
# user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
lonestarr = vespa, goodguy, schwartz

# -----------------------------------------------------------------------------
# Roles with assigned permissions
# roleName = perm1, perm2, ..., permN
# -----------------------------------------------------------------------------
[roles]
# 'admin' role has all permissions, indicated by the wildcard '*'
admin = *
# The 'schwartz' role can do anything (*) with any lightsaber:
schwartz = lightsaber:*
# The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
# license plate 'eagle5' (instance specific id)
goodguy = winnebago:drive:eagle5
```





​		**这里的users就是角色，lonestarr = vespa, goodguy, schwartz就表示 lonestarr这个用户， 密码是vespa， 然后拥有  goodguy, schwartz这两种角色， 其他的也是；  然后roles就是角色， admin = *就代表 admin可以做任何事情。 其他的也是这样理解。 **

- 最后就是去复制Quickstar的代码， 复制完会有爆红， 只要把对应的类导入， 然后爆红的import直接删除， 然后直接运行， 就ok了。



```java

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author 86187
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if ("aValue".equals(value)) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(false);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        if(currentUser.isPermitted("lightsaber: eat shirt")){
            log.info("you are a fucking genius!!");
        }
        else {
            log.info("you are rubbish!!");
        }

        //all done - log out!
        currentUser.logout();

        System.exit(0);
    }
}
```



​		**这里就是 先把ini这个文件放在一个工厂里 ， 这个工厂去获得一个 securityManager管理者，并且放到SecurityUtils里面， 然后由着个去获得 角色，  这个session.setAttribute貌似没啥用， 没有也会有结果， 然后下面就是获取当前用户， 然后登录用户， 然后根据用户拥有的角色， 又通过角色的功能来判断打印一些东西。 **



### shito第一个web程序



- 首先导入依赖， 导入一个启动器就行， web启动器肯定是要的。

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring-boot-web-starter</artifactId>
            <version>1.9.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    </dependencies>
```



- 然后我们就开始配置 shiro ， 首先创建一个UserRealm， 继承了AuthorizingRealm， 这个就是用来配置Realm

```java
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了参数===>PrincipalCollection");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了参数===>AuthenticationToken");
        return null;
    }
}
```



- 再写一个配置类， 里面主要是为了获取到shiroFilterFactoryBean， 并且设置 SecurityManager， 这里方法里的参数 是可以通过@Qualifier来获取的， 就是通过名字来注入值进去， 也可以直接写一个@Autowired， 也可以注入。

```java
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getSecurityManager") DefaultWebSecurityManager de){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(de);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager de = new DefaultWebSecurityManager();
        de.setRealm(userRealm);
        return de;
    }

    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
```



### 拦截功能

​		我们设置一些页面的权限， 让他们需要对应的权限才可以访问， 由之前的Spring Security 知道，我们没有登录的话会跳到对应的登录页面， 所以我们也需要一个登陆页面， 并且设置好他。



- 我们只需要改一下这个方法， 然后加上对应的登陆页面， 还有对应的controller。

```java

@Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getSecurityManager") DefaultWebSecurityManager de){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(de);
        /**
         * anno : 无需认证就可访问；
         * authc： 必须认证才可以访问；
         * user： 必须拥有记住我才可以访问
         * perms： 拥有对某个资源权限才可以访问
         * role：拥有某个角色权限才可以访问
         *
         */

        //拦截设置
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录页面
        bean.setLoginUrl("/toLogin");
        return bean;
    }

```





通过以上方法我们只是进行了拦截， 并没有进行登录验证， 以及权限授予的功能。 



- 我们先写上一个login表单提交的请求， 然后来处理 ：

```java
@RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //登录
            subject.login(token);
            return "hello";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            e.printStackTrace();
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
```



​		**这里就是跟之前的非Web项目一样，获取当前用户然后登录 ** ， 我们先跑一下看看。

![boot13](img\boot13.png)



​		**这里没有验证， 所以都是登录失败， 用户名错误， 但是这里可以看到进入到上面的方法了， 有输出，所以， 验证就是在这里面进行。**



- 验证用户名与密码，只需要修改一下之前的验证方法， 这里我们模拟的数据， 需要注意的是， 我们这个方法是可以拿到 之前用户的信息的， 因为这里也没有什么获取之前用户信息的代码， 只需要获取模拟数据里的信息， 就可以验证， 所以就是之前的 controller 传过来的 信息。

```java
 @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了参数===>AuthenticationToken");
        String name = "root";
        String password = "123456";
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //验证用户名；
        if(!token.getUsername().equals(name)){
            return null;
        }
        //验证密码，shiro自动帮我们做了
        return new SimpleAuthenticationInfo("",password,"");
    }
```



### 整合数据库



- 数据库那套操作， Durid， mybatis， mysql， log4j， lombok， 全都往里面导进去就完了， 然后就配置yml文件， druid的， 还有mybatis的，写pojo层， mapper层，service层，再进行测试，   测试成功之后，我们直接在之前的验证登录环节加上数据库查询， 就不需要模拟数据。

```java
@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了参数===>AuthenticationToken");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //验证用户名；
        People people = peopleService.selectByName(token.getUsername());
        if(people == null){
            return null;
        }
        //验证密码
        return new SimpleAuthenticationInfo("",people.getPwd(),"");
    }
```





### 授权操作



- 我们给配置类的ShiroFilterFactoryBean添加一些页面的权限限制， 

```java
@Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getSecurityManager") DefaultWebSecurityManager de){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(de);
        /**
         * anno : 无需认证就可访问；
         * authc： 必须认证才可以访问；
         * user： 必须拥有记住我才可以访问
         * perms： 拥有对某个资源权限才可以访问
         * role：拥有某个角色权限才可以访问
         *
         */

        //拦截设置
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        System.out.println(filterMap);

        //设置登录页面
        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }

```



​		**这里要注意就是最后一个put要放在最后， 要不然会拦截失败， put这里面的意思就是制定一些页面， 然后 只有 拥有value后面的这些权限才可以访问， 这样我们的people就都被拦截了。 **



- 我们要给people加权限， 要改变数据库， 增加一个perms的列， 然后加上一些数据， 这里要记得去修改 pojo， 然后我们就是要授权了。 

```java
public class UserRealm extends AuthorizingRealm {

    @Autowired
    PeopleServiceImpl peopleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权===>PrincipalCollection");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取people
        Subject subject = SecurityUtils.getSubject();
        People curPeople = (People) subject.getPrincipal();
        //给用户授权
        info.addStringPermission("user:add");
        info.addStringPermission(curPeople.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证===>AuthenticationToken");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //验证用户名；
        People people = peopleService.selectByName(token.getUsername());
        if(people == null){
            return null;
        }
        //验证密码
        //把people传到授权的方法里面。
        return new SimpleAuthenticationInfo(people,people.getPwd(),"");
    }
}

```



​		**送数据库里面读出权限， 然后给people赋上权限， 这里就是要注意要如何 在授权方法里面获得当前的用户， 是通过下面的验证方法传入people， 然后通过SecurityUtils.getSubject() ， 获得subject ， 再通过getPrincipal()获得用户， 这样我们就可以查询到数据库里的 用户权限， 并且通过info.addStringPermission(curPeople.getPerms()) 给他赋上权限。**



### 整合thymeleaf

- 首先导入依赖：

```xml
<dependency>
    <groupId>com.github.theborakompanioni</groupId>
    <artifactId>thymeleaf-extras-shiro</artifactId>
    <version>2.1.0</version>
</dependency>
```



- 然后需要在配置类里面加一个东西来整合， 这个类就是专门整合的。

```java
/**
     * 使用ShiroDialect 整合 shiro 与 thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
```



- 然后我们就可以在前端做一些验证的方式， 把当前用户有权限的地方都展示， 没权限的就不展示。



```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>首页</p>
<br>
<div shiro:hasPermission="user:add">
    <a th:href="@{/user/add}">add</a>
</div>
<div shiro:hasPermission="user:update">
    <a th:href="@{/user/update}">update</a>
</div>
<br>
<hr>
<div shiro:authenticated="">
    <a th:href="@{/outLogin}"><button class="btn-danger" th:text="注销"></button></a>
<!--    <a class="ui-button" th:text="${session.get('isLogin') != null ? '注销' : ''}" th:href="@{/outLogin}"></a>-->
</div>
<div shiro:notAuthenticated="">
    <a th:href="@{/toLogin}"><button class="btn-success" th:text="登录"></button></a>
<!--    <a class="ui-button" th:text="${session.get('isLogin') == null ? '登录' : ''}" th:href="@{/toLogin}"></a>-->
</div>
</body>
</html>
```



​		**这里记得导入命名空间， shiro:hasPermission="user:add"这个方法就是判断 当前用户是否有user:add权限， 有的话这个div就显示， 否则不显示；shiro:authenticated=""这个方法就是判断是否登录认证，如果有就展示 ， shiro:notAuthenticated=""这个就是相反的意思， 这个登录验证我们以前也做过， 可以用session来做， 登陆了就setAttribute， 注销就remove， 然后前端判断就可以， 就是我注释的代码。  **



## swagger



​		介绍：主流的Api框架， RestFul Api文档在线自动生成工具=> Api文档与Api定义实时更新。他会扫描我们的controller还有接口， 然后更新在页面上。



- 导包

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```



​		**这里的springBoot的版本需要降到2.5.6， 然后上面包的版本也只用上面的2.9.2， 要不然会报错， 或者打不开页面。**



- 配置一个swagger配置类， 只需要加上一个注解@EnableSwagger2

```java
@EnableSwagger2
@Configuration
public class SwaggerConfig {
}

```



- 运行测试， 输入请求swagger-ui.html

![boot14](img\boot14.png)



​		**这里就相当于之前的Durid， 自己的页面， 他有默认配置， 所以我们不配也可以。**



- 设置 swagger：

  - 这里我们首先要知道需要一个Docket实例才可以设置swagger， 所以我们就注入一个Docket。 
  - 但是Docket只有一个构造方法，所以我们还是需要有一个DocumentationType对象。 

  ```java
  public Docket(DocumentationType documentationType) {
          this.apiInfo = ApiInfo.DEFAULT;
          this.groupName = "default";
          this.enabled = true;
          this.genericsNamingStrategy = new DefaultGenericTypeNamingStrategy();
          this.applyDefaultResponseMessages = true;
          this.host = "";
          this.pathMapping = Optional.absent();
          this.apiSelector = ApiSelector.DEFAULT;
          this.enableUrlTemplating = false;
          this.vendorExtensions = Lists.newArrayList();
          this.documentationType = documentationType;
      }
  ```

  

  - DocumentationType可以直接获取静态对象， 这里我们选SWAGGER_2

  ```java
  public static final DocumentationType SWAGGER_12 = new DocumentationType("swagger", "1.2");
      public static final DocumentationType SWAGGER_2 = new DocumentationType("swagger", "2.0");
      public static final DocumentationType SPRING_WEB = new DocumentationType("spring-web", "1.0");
  ```

  

  - 然后新建了一个Docket实例我们还要设置一些值， 可以通过apiInfo(ApiInfo apiInfo)这个方法， 于是我们又要获得一个ApiInfo对象， 这个对象有一个静态方法获得默认的对象， 我们模仿他。

  ```java
  static {
          DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
      }
  ```

  

  - 这里又有一个DEFAULT_CONTACT的Contact类的实例， 我们去new一个， 这个很简单，就是设置要展示的用户的名字， url， 还有邮箱。

  ```java
  public Contact(String name, String url, String email) {
          this.name = name;
          this.url = url;
          this.email = email;
      }
  ```

  

  - 然后ApiInfo里的别的东西我们也可以设置玩玩。

  

- 最后的config就是这样了：

```java
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Contact contact(){
        return new Contact("郭欢军","https://www.baidu.com","2367792309@qq.com");
    }

    private ApiInfo apiInfo(){
        /**
        *标题；
        *描述；
        *版本号；
        *访问的地址；
        *Contact；
        */
        return new ApiInfo(
                "郭欢军的Swagger",
                "什么东西啊",
                "2.0",
                "https://www.baidu.com",
                contact(),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
}

```



- swagger更多的配置：

```java
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Contact contact(){
        return new Contact("郭欢军","https://www.baidu.com","2367792309@qq.com");
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "郭欢军的Swagger",
                "什么东西啊",
                "2.0",
                "https://www.baidu.com",
                contact(),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    @Bean
    public Docket docket(Environment environment){

        //通过获得环境的profiles.active的值， 然后进行判断是否在定义的profiles里面获得boolean， 在判断是否开启swagger。
        Profiles of = Profiles.of("dev", "test");
        boolean b = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置是否开启
                .enable(b)
                //select下就只有这三个方法；
                .select()
                //RequestHandlerSelectors选择器，basePackage通过包扫描, withClassAnnotation通过类注解扫描，withMethodAnnotation方法注解
                .apis(RequestHandlerSelectors.basePackage("com.ghj.controller"))
                //ant就是过滤 ， 只扫描该路径下的请求
                //any就是所有的，none就是所有都不。
                .paths(PathSelectors.ant("/ghj/**"))
                .build();
    }
}

```



​		**在Docket里面有很多配置的， 都可以玩玩。Environment就是当前环境，acceptsProfiles(of)就是判断当前的 profile.active 是否被包含在of里面。**



- 设置groupName()， 这个就是页面上的右上角那个default， 我们可以给他设置名字， 如果有多个Docket， 我们就可以有多个页面， 多个groupName， 可以在前端切换， 也就是配置了多个环境。 

```java
@Bean
    public Docket docket(Environment environment){

        //通过获得环境的profiles.active的值， 然后进行判断是否在定义的profiles里面获得boolean， 在判断是否开启swagger。
        Profiles of = Profiles.of("dev", "test");
        boolean b = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置是否开启
                .groupName("ghj")
                .enable(b)
                //select下就只有这三个方法；
                .select()
                //RequestHandlerSelectors选择器，basePackage通过包扫描, withClassAnnotation通过类注解扫描，withMethodAnnotation方法注解
                .apis(RequestHandlerSelectors.basePackage("com.ghj.controller"))
                //ant就是过滤 ， 只扫描该路径下的请求
                //any就是所有的，none就是所有都不。
                .build();
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("lj");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("郭欢军");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("罗静");
    }
}
```



- 设置Api文档， 我们的controller会被展示到前端页面 ， 之前就可以看到， 然后还可以展示一些model， 就像实体类， 还可以设置 标题， 信息 给类，方法，字段：
- 新建一个实体类， 这里的@ApiModel(value = "user 实体类")就会在前端展示这个类的信息， 以及字段的。但是前提是得扫描的得到， 因为里设置了select()， 所以正常情况下扫描不到， 需要某一个controller的返回值是这个实体类类型， 那么就可以扫描到， 要么也可以不加select()， 默认情况下可以扫描。 

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "user 实体类")
public class User {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}

```



- 当然我们的controller也可以有注解

```java
@Api(tags = "hello控制类")
@Controller
public class MyController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation(value = "shb")
    @ResponseBody
    @PostMapping("/hello")
    public String hello2(){
        return "hello2";
    }
    @ApiOperation(value = "什么")
    @ResponseBody
    @PostMapping("/user")
    public User user(){
        return new User();
    }
    @ApiOperation(value = "获取")
    @ResponseBody
    @GetMapping("/user2")
    public User user2(User user){
        return user;
    }
}
```



​			**@Api(tags = "hello控制类")是注解类上的，@ApiOperation(value = "什么")注解方法上的。 **



- swagger更强大的还是 ， 可以直接线上测试， 就不用去自己写的页面上测试， 直接在swagger上就可以。![boot15](img\boot15.png)





## 异步任务



​		一般有的网站会有发邮件或者发短信的功能， 点击发送， 其实并没有接收到， 但是上面还是显示说发送成功， 说明这个时候开了另一个线程， 这个线程就去干发送的事情， 原来的线程就提示你说发送成功， 如果说要等信息发完了，才来提示你说发送成功， 那么可能会很慢， 很影响体验， 所以开一个线程是很有必要的。



- 在spring中只要是异步任务只需要两个注解， 下面的本来是应该等三秒才会显示数据正在处理， 完了之后才显示hello， 加了的话就是两个线程开跑， 请求完就直接hello， 然后等另一个线程跑完才会显示数据正在处理。



- 一个service层的类

```java
@Service
public class HelloService {

    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据处理中....");
    }
}
```

 

- controller

```java
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        helloService.hello();
        return "hello";
    }
}

```



- Application

```java
@EnableAsync
@SpringBootApplication
public class SpringBoot09TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09TestApplication.class, args);
    }

}
```



​		**只需要在Application加上@EnableAsync， 然后在需要开启多线程的方法上加一个@Async就可以实现异步。**



## 邮件任务



- 首先导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```



- 然后需要去qq邮箱官网去获得一个 密码， 在设置， 账户里面找到 下面这个服务开启， 然后会获得一个密码， 这个密码就是下面你要用的密码， 如果用自己的qq密码是没用的。

![boot16](img\boot16.png)



- 然后配置yml文件， 这里的密码就是刚刚的， 然后下面的properties是键值对的形式 ， 必须写成这样的格式。

```yml
spring:
  mail:
    username: 2367792309@qq.com
    password: emyneybtnmkoebgg
    host: smtp.qq.com
    properties: {mail.smtp.ssl.enable: true}
```



- 最后测试， 简单邮件测试。

```java
@SpringBootTest
class SpringBoot09TestApplicationTests {


    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setSubject("我是姜金征");
        simpleMessage.setText("来我办公室喝茶！");
        simpleMessage.setTo("2367792309@qq.com");
        simpleMessage.setFrom("2367792309@qq.com");
        mailSender.send(simpleMessage);

    }

    @Test
    public void test1() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("是什么");
        helper.addAttachment("别点", new File("D:/Pictures/lj.jpg"));
        helper.setText("<h1 style='color: red'>你好呀</h1>", true);

        helper.setFrom("2367792309@qq.com");
        helper.setTo("2367792309@qq.com");
        mailSender.send(mimeMessage);
    }

}

```



​		**首先我们要有一个JavaMailSenderImpl类，这个就相当于是一个发送器， 然后就是写邮件了，可以调用mailSender的send()方法， 看看它可以send那些类， 然后去创建这些类，  这里先用SimpleMailMessage， 然后还需要设置 发送者， 接收者， 发送邮件的标题， 邮件的内容， 然后就可以发送； 下面的是用的MimeMessage， 然后他有一个helper ， 直接把这个MimeMessage丢进helper里面， 然后通过helper设置他，后面的true代表支持多文件发送，  可以额外加一些附件， 还可以加文本带html格式， 也会展示出来， 最后把这个MimeMessage丢进send方法里面就可以了。 **



## 定时执行任务



​		设置一个时间模式， 只要程序在运行， 当前时间符合该格式，那么这个程序就会执行， 不需要调用，只需要加一个注解。



- 首先我们要用这个 ， 就直接加一个@Enablexxxx， 在Application， 这里我们加@EnableScheduling。

```java
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBoot09TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09TestApplication.class, args);
    }

}

```



- 然后我们创建一个service类， 要加上@Service注解， 然后在执行的方法上加一个@Scheduled(cron = "10 * * * 5 0")注解， 就代表每个5月的星期天的 第10秒他都会执行一次。

```java
@Service
public class SchedulingService {

    @Scheduled(cron = "10 * * * 5 0")
    public void scheduling(){
        System.out.println("你好呀.......");
    }
}

```



- 下面是一些匹配的规则， 以及例子。

![boot17](img\boot17.png)





![boot18](img\boot18.png)





![boot19](img\boot19.png)





## Dubbo + zookeeper



- 首先安装zookeeper， 官网下载 https://zookeeper.apache.org/releases.html ；
- 安装注意事项：
  - 直接解压后在bin下双击zkServer.cmd， 如果可以打开 ， 那没事了， 如果不能 ，那么就在config下复制一份zoo_sample.cfg文件名叫zoo.cfg， 然后去双击他， 要是还不行， 报错：ZooKeeper audit is disabled， 那就编辑zkServer.cmd文件， 加上下面这个， 要是还不行， 那就去百度吧。
  - ![boot21](img\boot21.png)
  - 需要先打开服务端显示成功， 然后再打开客户端的cmd， 就会连接起来， 然后可以执行一些命令， 
  - ![boot22](img\boot22.png)
