



# SpringBoot - Study



## 第一个springBoot程序 



### 从官网下载 

- ![image-20220422175121144](img\image-20220422175121144.png)



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



- 然后创建我们的html页面，这里注意要加**xmlns:th="http://www.thymeleaf.org**， 这样我们就可以使用他了， 就像vue一样， th:text就是用msg绑定div里的文本。

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
- ![boot02](D:\my-study\笔记\img\boot02.png)





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

![boot3](D:\my-study\笔记\img\boot3.png)



​		**这里就可以看到了， 前面两个就是 官方说的那两个视图解析器， 而下一个就是Thymeleaf的视图解析器， 再下一个就是我们自己的视图解析器了。**





## @EnableWebMvc



- 上面官方特地强调说不能使用这个@EnableWebMvc， 那么是为什么呢 ？ 点卡这个注解， 我们就会发现这里面import了一个类， 就是**DelegatingWebMvcConfiguration**， 再点进去会发现这个类继承了**WebMvcConfigurationSupport**这个类。 

![boot4](D:\my-study\笔记\img\boot4.png)



![boot5](D:\my-study\笔记\img\boot5.png)





- 然后我们再看**WebMvcAutoConfiguration**的源码，这里面有一个条件。

![boot6](D:\my-study\笔记\img\boot6.png)





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





- 把index.html里的文字设置成， 从配置文件里面取值  注意这里的传参我们采用@{/index(l='zh_CH')}这种() 的格式传参。

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

  

  ![boot7](D:\my-study\笔记\img\boot7.png)





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



​		**这里需要先拦截所有的请求， 然后选择 一些需要放行， static下的静态资源也需要放行**
