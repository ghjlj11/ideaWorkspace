# SpringMVC Study



## 1、准备阶段

- 构建一个maven项目
- 导入依赖

```xml
<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.17</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2.1-b03</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
```



- 创建一个子模块， 并且为子模块勾选支持Web框架。



**我们需要在模块设置里，找到工件，在WEB-INF下面创建一个lib文件夹，把我们的依赖全部导入进去**



- 创建一个继承了HttpServlet的类，并且重写该 doGet和 doPost方法

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("add")){
            req.getSession().setAttribute("msg","执行了add方法");
        }
        if(method.equals("delete")){
            req.getSession().setAttribute("msg","执行了delete方法");
        }
        req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```



其中的**req.getParameter("method") **， 方法可以从url中获取method的值， 然后在经过下面的判断， 输出 在 **req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req,resp) **经过这个跳转页面上， **req.getSession().setAttribute("msg","执行了add方法")**， 则是表示加入了一条信息，名字是**msg**, 里面的内容是**执行了add方法**



- 在web目录下新建一个jsp文件夹，在jsp文件夹下新建一个test.jsp文件， 这个文件就是将要跳转的页面， 也就是客户端提交method字段之后可以看到的页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
</body>
</html>

```



这里的**${msg}**就是代表可以获得，刚刚传递过来的 数据里面的 **msg**，也就是**"执行了add方法"**。



- 然后我们还是要设置核心的web.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.ghj.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
    <welcome-file-list>
        <welcome-file>form.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```





这里的hello就是com.ghj.servlet.HelloServlet这个类的名字， **<url-pattern>/hello</url-pattern>**设置url，就表示当我们在url中对应的输入/hello， 此时我们会经过这个控制器来控制我们跳转到哪个页面 ，<welcome-file>form.jsp</welcome-file>这个表示我们的欢迎页面， 也就是我们刚启动tomcat网页给我们展示的页面， 我们可以自行设置， 这个默认就是index.jsp文件。



form.jsp文件：

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/hello" method="post">
    <input type="text" name="method">
    <input type="submit">
</form>
</body>
</html>
```



也可以通过这种方式来将method提交给HelloServlet。





## SpringMVC底层原理

![image-20220412184418928](img\image-20220412184418928.png)



上面的实线的部分都是Spring帮我们做好的， 我们做的只有虚线部分。



来通过第二个项目来说明 ： 



```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```



这里我们将由 **DispatcherServlet**来当我们的前端控制器，这里的<url-pattern>/</url-pattern>表示用户提交的所有请求， 都需要经过这个控制器， 来确定需要给哪个**页面控制器/处理器**。 

-  说明： 这里的 **/**表示会匹配到用户的所有请求不包括jsp页面， 而**/***则表示会匹配所有请求，也包括jsp页面。

​		url-pattern匹配优先级：精确匹配>最长路径匹配（/*）>后缀匹配（Tomcat中的web.xml默认定义了jsp的servlet）>缺省匹配（/）

​		<load-on-startup>1</load-on-startup>为启动级别。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>

    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <bean id="/Hello" class="com.ghj.controller.HelloController"/>
</beans>
```



这里的**HandlerMapping**是用来映射控制器的， 就是前端控制器 可以通过这个**HandlerMapping**来将请求提交到映射的**页面控制器/处理器**上面， 也就是通过这个来找到我们的 **Controller**，然后再通过 **HandlerAdapter** 来找到相应的类， 在这也就是通过**“/Hello”**,来找到 **HelloController**这个类，也就是**Controller**的底层还是对应的类 ， 通过这个类的方法再去获取资源以及视图的名字，再封装到**ModelAndView**， 再通过**HandlerAdapter** ，解析到前端控制器**DispatcherServlet**，**DispatcherServlet**会去调用视图解析器，就是上面的**InternalResourceViewResolver**这个类， 这个视图解析器就会根据**ModelAndView**里面的一些数据，再解析视图名字， 通过拼接视图的名字找到视图，也就是上面我们设置的属性**prefix**、**suffix**以及**setViewName**，然后前端控制器就可以找到相应的视图， 再将相应的数据， 也就是**addObject**里的数据， 渲染到页面中去，最后**DispatcherServlet**就会根据结果去调用视图， 来展示给用户。 

```java
public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","HelloSpringMVC");
        mv.setViewName("hello");
        return mv;
    }
}
```



- 总的来说也就是用户提交请求到前端控制器， 再去找到相应的页面控制器/处理器， 再返回模型与视图， 经过解析里面的数据以及拼接视图名字给前端控制器， 再去调用视图展示给用户。 这里面的核心就是前端控制器与页面控制器/处理器。



## 使用注解开发

我们只需要在spring的xml文件中配置注解扫描



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.ghj.controller"/>
    <mvc:default-servlet-handler />

    <mvc:annotation-driven />

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```





- 这里的 <context:component-scan base-package="com.ghj.controller"/>就是代表要扫描的包。

  

- <mvc:default-servlet-handler />这个设置会自动帮我们配置**HandlerMapping（控制器映射器）**与**HandlerAdapter（控制器适配器）**，**前端控制器**我们一样在web.xml里面配置，这样我们就只需要写Controller和jsp页面就可以。

```java
@Controller
public class HelloController {

    @RequestMapping("/hh")
    public String hello(Model model){

        model.addAttribute("msg", "Hello SpringMVC");
        return "hello";
    }
}
```



我们可以**@Controller**来将这个类设置为 一个控制器， 就是与之前的实现Controller接口是一样的， 然后通过**@RequestMapping("/hh")**，我们就可以为这个方法设置请求路径， 就相当于是在Spring的xml文件里面注册了这个类的bean，id = “/hh”， 这个注解同样可以写在类上面， 这样的话我们要访问这个方法就要加上类的路径，这里的**return "hello";**就是设置返回试图的名字， 然后还是会经过视图解析器来拼接他的名字。





### 通过表单给控制器传入参数

我们可以自定义一个表单，然后提交路径写上我们的 Controller 相应的url映射路径， 这样我们的Controller 就可以来处理这些提交的数据， 再来确定要 返回的视图， 提交的数据的名字需要与控制器中 参数的一致

例如：

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/ll/hh" method="post">
    <input type="text" name="method">
    <input type="submit">
</form>
</body>
</html>

```







```jav
@Controller
@RequestMapping("/ll")
public class HelloController {

    @RequestMapping("/hh")
    public String hello(String method, Model model){
        if("add".equals(method)){
            model.addAttribute("msg", "Hello SpringMVC");
            return "hello";
        }
        else {
            return "form";
        }
    }
}
```



这样我们就可以处理一些数据。 



## RestFul风格

- 安全性；
- 高效性；
- 简洁性；



我们可以设置

```java
@Controller
@RequestMapping("/ll")
public class HelloController {

    @RequestMapping(value = "/hh/{method}",method = RequestMethod.POST )
    public String hello(@PathVariable String method, Model model){
        if("add".equals(method)){
            model.addAttribute("msg", "Hello SpringMVC");
            return "hello";
        }
        else {
            return "form";
        }
    }
}
```



- 这里的value = "/hh/{method}"这里就是表示url需要是这种形式， 才可以到这个控制器， method = RequestMethod.POST则表示提交的方法。



- 我们还可以通过更简洁的方式来表示

  - @GetMapping(value = "/hh/{method}")表示匹配以GET方式提交到这个路径的请求

  - @DeleteMapping(value = "/hh/{method}")表示匹配以Delete方式提交到这个路径的请求

  - @PutMapping

  - @PostMapping

  - @PatchMapping

    

- 在参数前面加@PathVariable这个注解就一定要加上@RequestMapping("/hh/{method}")，表示参数需要在url中传递。 我们之前在url中写入参数是以 ？arg=value&arg2=value2，这种形式传入的， 但是如果我们用了RestFul风格， 我们就直接/value2/value2这种形式，这样就会让数据更安全一点没有这个暴露。这种方式提交表单数据需要在前端页面提交路径改成/ll/hh/${method}。



## 转发与重定向

平时我们跳转页面默认的方式就是转发(forward)， 通过视图解析器也是转发的方式， 如果要选择重定向的方式就需要改成（redirect），重定向没有访问WEB-INF下的文件的权限， 就算是写全部路径也不能访问， 

我们可以注释掉视图解析器来测试。



```java
@Controller
public class HelloController2 {

    @RequestMapping("/h1/t1")
    public String test1(Model model){
        return "redirect:/WEB-INF/jsp/index.jsp";
    }
}

```



如果将redirect改成forward，那也可以访问到对应的页面。

转发与重定向的区别：



![image-20220414151743826](img\image-20220414151743826.png)



![image-20220414151806158](img\image-20220414151806158.png)



## 接受前端的数据



如果说， 前端传来的数据 与方法里的参数名字一样， 那么可以直接传， 如果名字不一样， 那么我们可以定义 @RequestParam("method")String name，表示前端传来的method这个数据， 我们用name来接受



```java
@RequestMapping("/hh/kk")
    public String test01(@RequestParam("method")String name){
        if("add".equals(name)){
            return "hello";
        }
        else {
            return "form";
        }
    }
```



如果前端传来的是一个实体类，我们也可以接受， 实体类里的属性， 需要在url里面传入， 如果没有则是默认的值。



```java
@GetMapping("/hh/k2")
    public String test2(User user){
        System.out.println(user);
        return "hello";
    }
```





## 乱码问题



当我们在前端中提交中文数据到后端， 就可能会出现乱码问题， 原因还是前端提交的数据编码格式， 与后端解析的编码格式不对， 所以我们需要设置字符集， 添加一个过滤器



### 自己写过滤器



我们在ghj包下创建一个filter包里面在创建一个过滤器,实现filter接口。

```java
package com.ghj.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

```



这里写utf-8和UTF-8都可以。

这样我们的请求的解析就会被utf-8字符集格式解析。



在web.xml中设置过滤器过滤的页面， 也就是哪一些请求会经过他。

```xml
<filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>com.ghj.filter.EncodingFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```



这里的**/***就代表所有请求都要经过这个过滤器， 包括jsp页面。



**如果说我们是以GET方式提交请求的，我们可以不用配置过滤器，也是可以成功输出中文**



### 使用Spring的过滤器

Spring有自带的过滤器，我们也可以使用， 只需要在web.xml文件上加上以下配置就可以

```xml
<filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```



这样我们一样可以解决前端请求中文的乱码问题。



## JSON交互处理

json交互处理的意思就是， 前后端接受和发送的实体类的数据格式都保持一致， 这样一方发送数据，另一方就可以通过这个格式来解析这个数据， 因为实体类是不可以在servlet请求中传递，需要解析成可以在servlet中传递的，比如可以传字符串， 就像LeetCode里面将二叉树序列化的题目。



- 在java中我们通过servlet传送一个实体类的toString形式到前端是：

User(name=???, id=123456, age=21)

而用JSON传递就是：

{"name":"???","id":123456,"age":21}

以大括号包围的键值对的形式。对应的Controller的代码

```java
@Controller
public class Controller01 {

    @ResponseBody
    @RequestMapping("/ll/jj")
    public String test1() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user  = new User("郭欢军", 123456, 21);
        return objectMapper.writeValueAsString(user);
    }
}

```



**@ResponseBody的意思就是不会走视图解析器，直接把数据返回在body里面**

- 我们还应该解决JSON乱码的问题，在Spring的xml文件里面加上以下代码：

```xml
<mvc:annotation-driven>
   <mvc:message-converters register-defaults="true">
       <bean class="org.springframework.http.converter.StringHttpMessageConverter">
           <constructor-arg value="UTF-8"/>
       </bean>
       <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
           <property name="objectMapper">
               <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                   <property name="failOnEmptyBeans" value="false"/>
               </bean>
           </property>
       </bean>
   </mvc:message-converters>
</mvc:annotation-driven>
```



我们的@ResponseBody可以不走视图解析器， 在类上面加上@RestController就可以让整个类下面的方法都不走视图解析器，直接返回一个字符串。



- 我们一样可以去传递集合：

[{"name":"郭欢军","id":123456,"age":21},{"name":"郭军","id":1234,"age":2},{"name":"郭欢","id":1256,"age":1},{"name":"郭欢","id":1256,"age":1}]

- 还有Date类：

"2022-04-14 21:50:33"



### 创建工具类JsonUtil

因为我们，每个方法里面都需要new一个ObjectMapper，并且需要调用它的writeValueAsString()方法， 这样我们就会有很多重读的代码，我们可以下、自己创建一个JsonUtil工具类， 这样每次都只需要调用这里面的方法就行：

```java
public class JsonUtil {
    public static String getJson(Object o, String dateFormat) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleDateFormat str = new SimpleDateFormat(dateFormat);
        objectMapper.setDateFormat(str);

        return objectMapper.writeValueAsString(o);
    }

    public static String getJson(Object o) throws JsonProcessingException {
        return getJson(o, "yyyy-MM-dd HH:mm:ss");
    }
}
```



这样我们就只需要调用里面的getJson()方法，并把对象丢进去就可以了。



## 使用fastjson

就是阿里巴巴自己写的一个json，速度更快



对应的一些方法：

```java
System.out.println("*******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(userList);
        System.out.println("JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);

        System.out.println("\n****** JSON字符串 转 Java对象*******");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

        System.out.println("\n****** JSON对象 转 Java对象 ******");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);
```





## 整合ssm框架



- 在maven文件中导入需要的依赖

```xml
<dependencies>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>

        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.9</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.7</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.18</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.18</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```



**要记得在项目配置里面加入lib目录，并且导入依赖**



### 创建一个books的表：

```mysql
create database ssmbuild;

use ssmbuild;

drop table if exists books;

create table books(
    bookID int(10) not null auto_increment comment '书id' key ,
    bookName varchar(100) not null comment '书名',
    bookCounts varchar(11) not null comment '数量',
    detail varchar(200) not null comment '描述'
);

insert into books (bookID, bookName, bookCounts, detail) VALUES (1,'Java',1,'从入门到放弃'),
                                                                (2,'MySQL',10,'从删库到跑路'),
                                                                (3,'Linux',5,'从进门到坐牢');
```





### 编写Mybatis层的代码

- 首先先写一个Books实体类，

- 首先编写DAO层代码， 编写Mybatis.xml的文件，加入扫描包下的别名配置。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>


    <typeAliases>
        <package name="com.ghj.pojo"/>
    </typeAliases>


    <mappers>
        <mapper class="com.ghj.dao.BookMapper"/>
    </mappers>
</configuration>
```

### 编写Spring层的代码



- 编写service层代码，可以理解为是dao层的代理层， 接口对应着实现类，在类里面定义一个通过spring注册的接口的实现类，并且通过spring注入值，  对应的业务也是去调用这个类的方法。**注意这里需要spring注入的是dao接口类， 是spring-dao.xml文件里面注册的，所以需要把这些spring文件关联起来，才可以注入进去，需要在项目结构里面配置**，还需要配置事务管理器。



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.ghj.service"/>

    <bean id="bookServiceImpl" class="com.ghj.service.BooksServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```





- **编写spring-dao.xml文件，配置连接池， 配置sqlSessionFactory，这里的sqlSessionFactory是重点，把mybatis与spring结合在了一起， <property name="configLocation" value="classpath:mybatis-config.xml"/>。**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath:database.properties"/>

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <property name="autoCommitOnClose" value="false"/>
        <property name="checkoutTimeout" value="10000"/>
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.ghj.dao"/>
    </bean>
</beans>
```



​		**这里的最后一个bean就是扫描dao层下的接口，不用自己写实现类， 因为spring可以通过反射来注册进去，就不像之前需要自己写实现类，然后在实现类里面定义一个sqlsession，再来调用接口里面的方法， 这样我们就可以直接调用接口的方法**



​		**之前我们整合spring和mybatis时候， 需要自己定义一个实现类实现接口，这个实现类需要私有化private SqlSessionTemplate sqlSession， 这样就可以 通过sqlSession.getMapper 来获得这个接口的方法；  也可以去继承extends SqlSessionDaoSupport这里面会有一个自己的sqlSession， 其实都是为了去拿到一个sqlSession。**



​		**然而现在我们可以直接把接口注册进去， 不需要再写 impl实现类。**



​		**这里就是接口注入。**



### 编写SpringMVC层的代码

- 配置web.xml文件，过滤器， 前端控制器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```



**这里的前端控制器的spring配置文件应该是要加入applicationContext.xml，因为这个才是总的文件**



- 编写spring-mvc.xml的文件，配置，视图解析器， 注解扫描， handler



- 编写controller层，controller层需要调用service层的方法， 所以需要创建一个service的实现类的对象，还有对应的一些jsp文件。



**因为要保证不让别人直接访问addBook界面，所以放到WEB-INF不让别人访问，只能通过controller，servlet访问，WEB-INF下的目录只能通过请求转发访问，重定向可以阻止重复提交表单**



## Ajax



​		在我们平时很多时候登录网站， 用户名有错误会有提示说“用户名有误”，密码错误也会有提示“密码错误”， 这时候我们的页面并没有发生转发与重定向， 但是是肯定向后端发送请求的了， 因为需要判断用户名与密码的正确性， 需要调用后台的业务去数据库中查看，如果按照我们以前的方式，只可以发生页面转发或者重定向， 而这个时候我们就可以通过**JSON（注意要导入依赖并加上防止JSON乱码的配置）**，来传递后端的数据， 但是不发生页面的跳转， 只是单纯的向前端发送一些数据。



​		有了这种思想， 我们需要在用户输入账号和密码时候就要向后端发送请求， 这时候可以用Javascript的代码来实现， **onblur**事件，只要鼠标的焦点从本身离开了就会发送请求， 而这个请求需要包含请求的路径、请求的一些数据， 就比如账号和密码、还需要接受请求返回的数据。这时候我们就可以用**Ajax**来做这个事情。



- 我们首先需要下载jQuery放在web的statics的目录下。

- 对于web.xml文件我们不需要改动什么， spring的xml文件需要加上 <mvc:default-servlet-handler/>，这个可以过滤静态资源，然后加上注解扫描，以及JSON的乱码问题。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.ghj.controller"/>
    <mvc:default-servlet-handler/>


    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```



 

- 然后我们就可以编写一个jsp页面， 这里的功能就是输入账号和密码如果正确就会有提示“ok”，否则提示“错误”。

​		**我们只需要将jsp文件不创建在WEB-INF目录下就可以直接通过url访问**



通过JavaScript一直向服务端发送请求， 然后接收数据并渲染当前页面，让客户拥有更好的体验 。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/statics/jsp/jQuery.js"></script>
    <script>
        function a1(){
            $.post({
                url:"${pageContext.request.contextPath}/a3",
                data:{"name":$("#name").val()},
                success:function (data){
                    console.log(data);
                    if(data === "ok"){
                        $("#nameS").css("color","green");
                    }
                    else {
                        $("#nameS").css("color","red");
                    }
                    $("#nameS").html(data);
                }
            });
        }
        function a2(){
            $.post({
                url:"${pageContext.request.contextPath}/a3",
                data:{"psw":$("#psw").val()},
                success:function (data){
                    console.log(data);
                    if(data === "ok"){
                        $("#pswS").css("color","green");
                    }
                    else {
                        $("#pswS").css("color","red");
                    }
                    $("#pswS").html(data);
                }
            });
        }
    </script>
</head>
<body>

<div>
    <p>用户名：<input type="text" id="name" onblur="a1()">
        <span id="nameS"></span>
    </p>
    <p>密&nbsp;&nbsp;码：<input type="text" id="psw" onblur="a2()">
        <span id="pswS"></span>
    </p>
</div>
</body>
</html>

```



- 接下来我们要写 Controller层的代码：

这里采用@RestController注解就是不会走视图解析器， 不会转发或者重定向， 只是单纯的做一些判断然后向前端发送数据， 返回的是字符串。

```java
@RestController
public class AjaxController {

    @RequestMapping("/a3")
    public String test3(String name, String psw){
        String msg = "";
        if(name != null){
            if("lj".equals(name)){
                msg = "ok";
            }
            else {
                msg = "用户不存在";
            }
        }
        if(psw != null){
            if("12345".equals(psw)){
                msg = "ok";
            }
            else {
                msg = "密码错误";
            }
        }
        return msg;
    }
}

```



这样前端接受后台返回的数据，在经过一些处理就可以渲染这个页面，而不需要再去转发或者重定向。

![image-20220418171949835](img\image-20220418171949835.png)



## 拦截器



**拦截器（Interceptor）与过滤器（Filter）**

- 拦截器是Spring里的， 而过滤器是servlet里面的， 他们的触发时机不同。过滤器是在请求进入容器之后， servlet之前执行，以及请求结束返回，servlet处理完之后，返回前端之前执行 ； 而拦截器是在请求进入servlet之后的。 拦截器是被包裹在过滤器之间的。
- 拦截器是基于java的反射机制的，而过滤器是基于函数回调。
- 拦截器不依赖于servlet容器，而过滤器依赖于servlet容器。
- 拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。



拦截器是以AOP的方式切入到 Controller里面的，所以配置的方式与AOP类似。

我们可以写一个类实现HandlerInterceptor接口：

```java
public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("方法执行之前=====");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("方法执行之后=====");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("传递=====");
    }
}

```



这里的preHandle方法就是对请求进行判断， 如果返回为true，则继续，如果为false则终止了。



我们在xml里面配置

```xml
<mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.ghj.config.HelloInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```



**/********， 代表所有请求都会经过这个拦截器。



### 判断验证登录

可以利用拦截器来写一个验证登录的例子， 网站的首页有的是需要登录才可以看到的，如果没有登陆的话， 那么想去首页就会被拦截器拦截到登录页面， 需要先登录才行， 登陆了就可以去任何的页面。



我们首先写一个外部的页面（给所有人看的，通过这个页面进入登录页面）， 再来一个登录页面， 登录信息提交到后台， 后台在session里面加入账号的信息， 拦截器就可以通过这个信息来判断是否要进行拦截， 如果没有登录就想进去， 那么session里面没有对应的信息， 拦截器就会进行拦截，转发到登陆页面， 如果用户点击注销按钮， 就会删掉session里面对应的信息 ，这样又是会跳转到登陆页面。



拦截器（要注意去xml文配置好）：

```java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("方法执行前");
        if(request.getSession().getAttribute("username") != null){
            return true;
        }

        if(request.getRequestURL().toString().contains("login")){
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);

        return false;
    }

}

```



Controller

```java
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password){
        session.setAttribute("username",username);
        return "main";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    @RequestMapping("goOut")
    public String goOut(HttpSession session){
        session.removeAttribute("username");
        return "/main";
    }
}

```



页面就可以实现登陆验证功能。





## 文件上传与下载



- 导入依赖

```xml
<dependencies>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.4</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>
    </dependencies>
```







- 在spring.xml文件里面加上以下配置：



```xml
<bean id="multipartResolver"  class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="utf-8"/>
        <property name="maxUploadSize" value="10485760"/>
        <property name="maxInMemorySize" value="40960"/>
    </bean>
```





### 文件的上传

```java
@RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file , HttpServletRequest request) throws IOException {


        System.out.println("hahhahahahahahah");
        String uploadFileName = file.getOriginalFilename();

        //如果文件名为空，直接回到首页！
        if ("".equals(uploadFileName)){
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名 : "+uploadFileName);

        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        //如果路径不存在，创建一个
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("上传文件保存地址："+realPath);

        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(new File(realPath,uploadFileName));

        is.transferTo(os);
        return "redirect:/index.jsp";
    }
```





### 文件的下载



```jav
@RequestMapping("/download")
    public String downloads(HttpServletResponse response , HttpServletRequest request) throws Exception{
        //要下载的图片地址
        System.out.println("进入请求");
        String  path = request.getServletContext().getRealPath("/upload");
        String  fileName = "p1.png";

        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path,fileName);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return "dd";
    }
```

