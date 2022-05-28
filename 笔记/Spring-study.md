# Spring-study


## 优点
- Spring是一个开源的免费的框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IOC），面向切面编程（AOP）！
- 支持事务的处理，对框架整合的支持！

 总结一句话：Spring就是一个轻量级的控制反转（IOC） 和面向切面编程（AOP）的框架！



## 开始



### 理解IOC（控制反转）

- Spring中的IoC就是DI依赖注入， 底层需要的技术有，xml文件解析或者注解扫描， 反射，工厂模式，通过解析xml文件然后使用反射的技术构建bean， 并把参数注入到bean中， 然后通过工厂模式生产出bean， 我们就可以直接从工厂中取出bean使用。

- 将创建对象的过程，以及对象调度过程交给Spring容器来做，不用自己手动new 容器就是Spring的beans，他会自己创建对象，我们写好程序后，不用管程序，只需要修改beans里的参数就可以实现创建想要的对象。



![img](img\watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAaXZhbjgyMDgxOQ==,size_10,color_FFFFFF,t_70,g_se,x_16.png)

我们创建具有一定联系的对象



![img](img\c9899cc96f1445cbb0e0ec785e03d77e.png)



通过IOC容器来实现具有依赖关系的对象之间的解耦



![img](img\91b4c7ce1be947dda69f76b24be165e1.png)



### 导入父工程依赖

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.17</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.17</version>
        </dependency>
    </dependencies>
```



### 创建beans.xml文件

- 这里是所有创造的类的创建中心

当想要某一个类时，可以直接从这个容器获取

```java

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
```



- 初始化beans.xml文件，beans会调用构造函数，默认无参构造，一个bean默认只能创建一个对象，后面调用都是在拿这个创建的对象（即Spring默认为单例模式），创建的对象的名字就是id里面的值，也可以是name里的值，可以拥有多个名字，property标签里面是类里的字段名，value就是值，默认的单例模式下，bean会自己创建对象，无论后面是否调用该对象。

- 如果字段是自己创的某一个类，则使用ref标签设置对象类型，即使用该类型在beans中的id。

```xml
<property name="user" ref="SqlUser"/>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="hello" class="com.ghj.pojo.Hello"/>
    <property name="name" value="Spring"/>
    <property name="age" value="5"/>

</beans>
```



## 依赖注入

- **Spring在构建ClassPathXmlApplicationContext时候就把所有的bean都创建好了**

### 概念

- 依赖注入（Dependency Injection,DI）。
- 依赖 : 指Bean对象的创建依赖于容器 . Bean对象的依赖资源 .
- 注入 : 指Bean对象所依赖的资源 , 由容器来设置和装配 .

### 构造器注入

- 使用constructor-arg则采用有参构造函数，使用property则是使用setter注入

```xml
    <bean id="hello" class="com.ghj.pojo.Hello">
<!--        <constructor-arg name="name" value="nie"/>-->
<!--        <constructor-arg name="age" value="4"/>-->
        <property name="name" value="Spring"/>
        <property name="age" value="5"/>
<!--        <constructor-arg type="java.lang.String" value="sha"/>-->
<!--        <constructor-arg index="1" value="2"/>-->
<!--        <constructor-arg index="0" value="ghj"/>-->
    </bean>
```



- 多个beans.xml文件可以被包含在一个xml文件里面；

  ```xml
  
      <import resource="beans.xml"/>
      <import resource="beans2.xml"/>
  ```

### Setter注入

- 基于 Setter 的 DI 是通过容器在调用无参数构造函数或无参数静态工厂方法来实例化 bean 后调用 bean 上的 setter 方法来完成的。

- 定义一个Student类与Address类

  - Address类

    ```java
    public class Address {
        private String address;
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        @Override
        public String toString() {
            return "Address{" +
                    "address='" + address + '\'' +
                    '}';
        }
    }
    
    ```

  - Student类

    ```java
    public class Student {
        private String name;
        private  Address address;
        private String[] books;
        private List<String> hobby;
        private Map<String, String> card;
        private Set<String> games;
        private String wife;
        private Properties info;
    
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", address=" + address +
                    ", books=" + Arrays.toString(books) +
                    ", hobby=" + hobby +
                    ", card=" + card +
                    ", games=" + games +
                    ", wife='" + wife + '\'' +
                    ", info=" + info +
                    '}';
        }
    
        public String getName() {
            return name;
        }
    
    
    
        public Address getAddress() {
            return address;
        }
    
        public void setAddress(Address address) {
            this.address = address;
        }
    
        public String[] getBooks() {
            return books;
        }
    
        public void setBooks(String[] books) {
            this.books = books;
        }
    
        public List<String> getHobby() {
            return hobby;
        }
    
        public void setHobby(List<String> hobby) {
            this.hobby = hobby;
        }
    
        public Map<String, String> getCard() {
            return card;
        }
    
        public void setCard(Map<String, String> card) {
            this.card = card;
        }
    
        public Set<String> getGames() {
            return games;
        }
    
        public void setGames(Set<String> games) {
            this.games = games;
        }
    
        public String getWife() {
            return wife;
        }
    
        public void setWife(String wife) {
            this.wife = wife;
        }
    
        public Properties getInfo() {
            return info;
        }
    
        public void setInfo(Properties info) {
            this.info = info;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
    
    ```

- 此时在beans中给这些不同类型的参数注入

  ```xml
  <bean id="address" class="com.ghj.pojo.Address">
          <property name="address" value="China"/>
      </bean>
      <bean id="student" class="com.ghj.pojo.Student">
          <property name="name" value="ghj"/>
          <property name="address" ref="address"/>
  
          <property name="books">
              <array>
                  <value>红楼梦</value>
                  <value>西游记</value>
                  <value>水浒传</value>
              </array>
          </property>
  
          <property name="hobby">
              <list>
                  <value>唱</value>
                  <value>跳</value>
                  <value>rap</value>
                  <value>篮球</value>
              </list>
          </property>
  
          <property name="games">
              <set>
                  <value>LOL</value>
                  <value>OK</value>
                  <value>DoTa</value>
              </set>
          </property>
  
          <property name="card">
              <map>
                  <entry key="IDCard" value="21341242355"/>
                  <entry key="moneyCard" value="345665433456"/>
              </map>
          </property>
  
          <property name="info">
              <props>
                  <prop key="driver">1921806</prop>
                  <prop key="url">192180611</prop>
                  <prop key="user">1921806</prop>
                  <prop key="password">1921806</prop>
              </props>
          </property>
  
          <property name="wife">
              <null/>
          </property>
      </bean>
  ```

- 可以给元素设置null值 。

- 使用p命名，与c命名。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:p="http://www.springframework.org/schema/p"
         xmlns:c="http://www.springframework.org/schema/c"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  
      <bean id="user" class="com.ghj.pojo.User" p:name="hgj" p:age="21"/>
  
      <bean id="user2" class="com.ghj.pojo.User" c:name="lj" c:_1="33"/>
  </beans>
  ```

  p就是取代了上面的property，而c取代了constructor-arg。



## Bean的作用域

- 单例模式：

  ```xml
  <bean id="user" class="com.ghj.pojo.User" p:name="hgj" p:age="21" scope="singleton"/>
  ```

  bean默认的模式，一个bean只生成一个对象，就算不调用也会生成，static类型的。

- 原型模式：

  ```xml
  <bean id="user" class="com.ghj.pojo.User" p:name="hgj" p:age="21" scope="prototype"/>
  ```

  原型模式就是只有要调用的时候才会生成一个对象，并且生成的都是新的对象。
  
  

- 请求模式

  ```xml
  <bean id="user" class="com.ghj.pojo.User" p:name="hgj" p:age="21" scope="request"/>
  ```

  在一个请求中使用同一个bean

- 会话模式

  ```xml
  <bean id="user" class="com.ghj.pojo.User" p:name="hgj" p:age="21" scope="session"/>
  ```

  在一个会话中使用的是同一个bean



## Bean的生命周期

- 首先创建一个实例

```java
@Data
@AllArgsConstructor
public class Computer {
    private Integer id;
    private String name;
    private CPU cpu;
    static{
        System.out.println("static....");
    }
    public void init (){
        System.out.println("初始化....");
    }
    public void destroy(){
        System.out.println("销毁了....");
    }
    public Computer(){
        System.out.println("执行了 无参构造");
    }
}

```



- 通过xml注入到Spring里面去

```xml
<bean name="cpu" class="com.ghj.pojo.CPU"></bean>
    <bean name="computer" class="com.ghj.pojo.Computer" init-method="init" destroy-method="destroy">
        <property name="name" value="ghj"/>
        <property name="cpu" ref="cpu"/>
        <property name="cpu.id" value="23"/>
        <property name="cpu.name" value="kkk"/>
    </bean>
```



- 然后直接测试

```java
public class MyTest {
    @Test
    public void t1(){
        System.out.println("-------------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println("-------------------");
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println("-------------------");
        System.out.println(computer);
        context.close();
    }
}
```



- 测试结果是

```txt
-------------------
static....
执行了 无参构造
初始化....
-------------------
-------------------
Computer(id=null, name=ghj, cpu=CPU(id=23, name=kkk))
销毁了....

进程已结束,退出代码0

```



bean的生命周期

- 首先创建实例
-  为bean的属性设置值，和对其他bean引用（调用set方法）
-  把bean的实例传递bean后置处理器的方法（postProcessBeforeInitialization）
- 执行指定的init方法
- 把bean的实例传递bean后置处理器的方法（postProcessAfterInitialization）
- bean对象的使用， 调用
- bean销毁

-- 如果想要实现bean的前后置的方法， 需要有一个类实现了BeanPostProcessor接口， 然后重写该方法， 并且把这个类注入到Spring里面去就可以了。

## 自动装配



- byName

  ```xml
     <bean id="people" class="com.ghj.pojo.People" autowire="byName">
  ```

  通过名字自动装配。

  当一个bean节点带有 autowire byName的属性时。

  1. 将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。
  2. 去spring容器中寻找是否有此字符串名称id的对象。
  3. 如果有，就取出注入；如果没有，就为空。

- byType

  ```xml
     <bean id="people" class="com.ghj.pojo.People" autowire="byType">
  ```

  通过类型自动装配。

  当一个bean节点带有 autowire byType的属性时。

  1. 将查找spring容器中是否有class值为该类型的对象，如com.ghj.pojo.Cat，就是Cat类型。

  2. 如果有，就取出注入；如果有多个，就会异常。

     

- 使用注解开发

  

  - 需要先配置xml文件

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd">
    
        <context:annotation-config/>
    </beans>
    ```

    

  - 创建Cat，Dog，people类

    ```java
    public class Cat {
        public  void shout(){
            System.out.println("miao");
        }
    }
    ```

    

    

    

    ```ja
    public class Dog {
        public  void shout(){
            System.out.println("wang");
        }
    }
    ```

  

  - **使用@Autowired都可以不用set方法， 他是基于反射实现的， 并不是通过set**

  ```java
  public class People {
      @Autowired(required = false)
      @Qualifier(value = "dog2")
      private Dog dog;
      @Autowired(required = false)
      @Qualifier(value = "cat2")
      private Cat cat;
  
      @Override
      public String toString() {
          return "People{" +
                  "dog=" + dog +
                  ", cat=" + cat +
                  '}';
      }
  
      public Dog getDog() {
          return dog;
      }
  
      public Cat getCat() {
          return cat;
      }
  
  }
  
  ```

  - 如果只使用  @Autowired 则默认是用byType自动装配，如果byType有多个bean，则再通过byName来查找。

  - required = false 可以设置该属性可以为空， 默认该值为true， 即默认不可以为空。

    ```xml
    <!--    <bean id="dog2" class="com.ghj.pojo.Dog"/>-->
    <!--    <bean id="dog" class="com.ghj.pojo.Dog"/>-->
    <!--    <bean id="cat" class="com.ghj.pojo.Cat"/>-->
    <!--    <bean id="cat2" class="com.ghj.pojo.Cat"/>-->
        <bean id="people" class="com.ghj.pojo.People">
    ```

    此时cat与dog都没有创建，如果不设置required = false则会报错，如果设置了，在测试中输出getCat与getDog则输出都为null

    

  - @Qualifier(value = "cat2") 采用byName查找来自动装配，这个需要与@Autowired一起使用才可以，不然会报错。

## 使用注解开发

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.ghj"/>
    <context:annotation-config/>

</beans>
```

 需要添加扫描才可以使用这些注解  。

- base-package 后面添加要扫描的包，就是含有注解的包；

- 可以通过配置 或者注解@Scope("prototype")来设置bean是单例模式还是常规的模式。



​		**Component（组件）就相当于在spring容器里面注册了一个Bean， 里面的属性的值就是 @Value里的值。@Value既可以直接写在属性上， 也可以写在对应的set方法上。 使用这个Component就一定要有 <context:component-scan base-package="com.ghj"/>扫描包的配置， 或者配置类里有这个@ComponentScan("com.ghj.pojo")注解。**

- ```java
  @Component
  @Scope("prototype")
  public class User {
      //@Value("kk")
      public String name;
  
      @Value("lj")
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

  @Component ， 一般写在Pojo层， 就相当于在xml文件中写了一个bean，这个类的id默认是类名小写， 基本数据可以用@Value 或者在set函数上写 @Value来设置， 引用数据类型可以 用@Autowired 。

- @Repository， 一般写在Dao层 。

- @Controller， 一般写在Controller层

- @Service， 一般写在Service层。

## 使用java的方式配置Spring



User类

```java
@Component
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public User() {
    }

    @Value("ghj")
    public void setName(String name) {
        this.name = name;
    }

}

```



config配置文件



```java
package com.ghj.config;

import com.ghj.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 86187
 * 使用配置类两种方式获得User实例：1.配置类加@Bean就可以 ； 2.扫描包（@ComponentScan）+ @Component；
 * 1. @Configuration就相当于一个Beans， @Bean就相当于在Spring里面写了一个Bean， 获得这个Bean需要这个方法名， 就是getUser；
 * 2. 就相当于是05模块里的内容，在xml文件中写了scan，直接扫描包；
 */
@Configuration
@Import(GhjConfig2.class)
@ComponentScan("com.ghj.pojo")
public class GhjConfig {

    public User getUser(){
        return new User();
    }
}

```



- **@Configuration**注解就相当于 是配置文件里的 beans ， 可以在里面注册bean。
-  **@Bean， 需要在@Configuration配置下** ， 就是相当于在beans里面注册了一个bean， 而这个bean里注入的值 ， 都在User类里面配置好了 ， 通过getUser的方式 ， 来返回给 需要用到的地方。 



Test文件

```java
import com.ghj.config.GhjConfig;
import com.ghj.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        //如果完全使用配置类，我们就只能通过ApplicationConfig上下文来获取容器；
        ApplicationContext context = new AnnotationConfigApplicationContext(GhjConfig.class);
        User user  = context.getBean("user", User.class);
        System.out.println(user.getName());
    }
}

```



**两种配置的方式：**

​		**1、 通过@ComponentScan("com.ghj.pojo")， 和@Component， 不需要配置@Configuration， 就相当于是上一个笔记。**

​		**2、通过@Configuration， 和getUser方法 再加上注解@Bean ， 就相当于 在 beans里面注册bean， 但是在 测试时候 获得这个bean， 需要的名字是方法名 ， 即getUser， 不是通过 user名字。**



## 代理模式

- 代理模式就是，我们需要使用某个对象的功能时， 我们不直接使用他，而是创建一个代理对象， 代理对象拥有其所有的功能， 或者增强其功能，通过代理对象实现功能。

### 静态代理

创建一个目标类， 然后创建一个代理类， 静态代理针对于单个的类，如果需要为另一个类代理的话，就需要再创建一个代理类。



### 动态代理

- 动态代理可以实现，不管往里面放什么类，都会对其进行代理，其原理就是通过反射内部创建一个代理的类。



#### java自带的代理Proxy

- **基于接口的代理方式**

我们先创建一个可以生成动态代理实现InvocationHandler接口的类， 这个需要重写invoke（）方法， 放进一个需要被代理的对象则会生成一个相应的代理对象， 这个过程可以用创建一个getProxy（）方法， 使用Proxy类的获得代理实例的静态方法 ： 

```java
Proxy.newProxyInstance(this.getClass().getClassLoader(),
        target.getClass().getInterfaces(),
        this);
```



然后这个代理对象还需要可以调用被代理对象的方法， 也可以自行增加别的功能（方法），则可以用到重写的invoke方法

- 创建这个生成动态代理对象的类

  ```java
  public class ProxyInvocationHandler implements InvocationHandler {
  
      Object target ;
  
      public void setTarget(Object target) {
          this.target = target;
      }
  
      public Object getProxy(){
          return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                  target.getClass().getInterfaces(),
                  this);
      }
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
  
          log(method.getName());
          
          Object result =method.invoke(target, args);
          return result;
      }
      public void log(String name){
          System.out.println("执行了" + name + "方法");
      }
  }
  
  ```

- target 应该是要传入一个接口类（在编译上是接口就行），这样target.getClass().getInterfaces() 才可以获得其接口类型 。

- 用UserService类来进行测试

```java
public class MyTest {
    @Test
    public void test01(){
        UserService userService = new UserServiceImp();
        ProxyInvocationHandler phi = new ProxyInvocationHandler();
        phi.setTarget(userService);
        UserService proxy = (UserService) phi.getProxy();
        proxy.add();
        proxy.delete();
        proxy.select();
    }
}

```

**值得注意的是， 这里的Proxy是基于接口的代理方式，代理生成的代理类只会有传入参数中的接口的那些方法， 如果目标对象有他自己的方法， 那么就不会被代理，代理类里面没有这些独有的方法**



#### cglib

- **基于类的代理方式， 也可基于接口**

1、需要引入cglib依赖

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```

2、需要配置vm选项， 在编辑配置中修改选项，加上配置`--add-opens java.base/java.lang=ALL-UNNAMED`才可以正常运行。

- 我们建两个接口，然后一个实现类实现两个接口， 其中的`isM`方法是实现类自己的

```java
public class FooImp implements Foo, Koo{

    @Override
    public void add(int i, int j) {
        int k = i + j;
        System.out.println("结果为： " + k);
    }
    public void isM(String name){
        System.out.println("is me " + name);
    }

    @Override
    public void kkk(String a, String b) {
        System.out.println(a + "......" + b);
    }
}

```



- 测试

```java
public class TestCglib {
    public static void main(String[] args) {
        FooImp fooImp = new FooImp();
        Class[] interfaces = new Class[]{Koo.class, Foo.class};
        System.out.println(Arrays.toString(interfaces));
        System.out.println("fooImp的接口==>"+Arrays.toString(fooImp.getClass().getInterfaces()));
        Object o = Enhancer.create(fooImp.getClass(),  new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行之前啊");
                Object invoke = method.invoke(fooImp, args);
                System.out.println("执行完了啊");
                return invoke;
            }
        });
        System.out.println("o的class==>"+o.getClass());
        System.out.println("o的父类==>"+o.getClass().getSuperclass());
        System.out.println("o的接口==>"+Arrays.toString(o.getClass().getInterfaces()));
        System.out.println("--------------------------------");
        if(o instanceof Foo){
            System.out.println("是属于Foo这个接口的");
        }
        if(o instanceof Koo){
            System.out.println("是属于Koo这个接口的");
        }
        if(o instanceof FooImp f){
            System.out.println("----------");
            f.kkk("a", "b");
            f.isM("hahah");
        }
    }
}

```

**这里的create方法有重载， 可以是两个参数，也可以三个参数。如果第一个参数是目标对象的class， 那么其内部的代理类会有目标对象自己的方法， 否则只会有接口的方法。**



>  #### 动态代理的好处



![image-20220405213900973](img\image-20220405213900973.png)





## APO

### 使用Spring实现APO

- 导入依赖

```java
 <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.9</version>
        </dependency>
    </dependencies>
```



举例：

- 创建一个UserService接口

```java
public interface UserService {
    public void add();
    public String delete();
    public void update();
    public void select();
}
```

- 创建实现类UserServiceImpl

```java
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public String delete() {

        System.out.println("删除了一个用户");
        return "ghj and lj";
    }

    @Override
    public void update() {

        System.out.println("更新了一个用户");
    }

    @Override
    public void select() {

        System.out.println("查询了一个用户");
    }
}

```



- 创建Log实现MethodBeforeAdvice接口与AfterLog实现AfterReturningAdvice接口两个类：

  这里的Log类实现了MethodBeforeAdvice，实现before方法，其实和之前的动态代理是一样的，代理调用被代理对象的方法， 而Log类则是表示方法的加强 ，不用去底层修改代码， 这里的AfterLog则跟Log差不多，实质还是对被代理对象 的方法的增强，只不过他们执行的顺序不一样， 因为实现的接口，实现的方法也是不一样。

```java
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" +  method.getName()
        + "方法，返回了" + returnValue);
    }
}
```



```java
public class Log implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "执行了" + method.getName() + "方法");
    }
}

```



- 使用xml文件，将这些类注册在Spring中，并用aop给UserServiceImpl环绕Log与AfterLog加强类

  aop:pointcut表示被环绕的对象的id，expression="execution(* com.ghj.service.UserServiceImpl.*(..))" 表示哪个类的哪个方法需要被环绕，这里第一个表示返回值， 后面则是类的全限定名，以及他的所有方法，以及括号里的多个参数；

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.ghj.service.UserServiceImpl"/>
    <bean id="log" class="com.ghj.log.Log"/>
    <bean id="afterLog" class="com.ghj.log.AfterLog"/>
    <aop:config>
        <aop:pointcut id="pointcut" expression="execution(* com.ghj.service.UserServiceImpl.*(..))"/>
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
```



- 创建测试类

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = context.getBean("userService", UserService.class);
        String s = bean.delete();
        bean.select();
        System.out.println(s);
    }
}
```



### 自定义切面

- 自定义创建一个DiyPointCut类

```java
public class DiyPointCut {

    public void before(){
        System.out.println("============执行方法之前===========");
    }

    public void after(){
        System.out.println("=============执行方法之后===========");
    }
}

```



然后再aplicationContext.xml文件中设置好切面，之前是以点切入， 就是一个类负责加强方法执行前的操作，而另一个负责加强方法执行后的操作， 现在则是一个类加强执行前后的操作。 此时需要在xml文件中如下设置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.ghj.service.UserServiceImpl"/>
    <bean id="log" class="com.ghj.log.Log"/>
    <bean id="afterLog" class="com.ghj.log.AfterLog"/>
    <!--<aop:config>
        <aop:pointcut id="pointcut" expression="execution(* com.ghj.service.UserServiceImpl.*(..))"/>
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>-->
    <bean id="diy" class="com.ghj.diy.DiyPointCut"/>
    <aop:config>
        <aop:aspect ref="diy">
            <aop:pointcut id="pointcut" expression="execution(* com.ghj.service.UserServiceImpl.*(..))"/>
            <aop:before method="before" pointcut-ref="pointcut"/>
            <aop:after method="after" pointcut-ref="pointcut"/>
        </aop:aspect>
    </aop:config>
</beans>
```



先在Spring中注册好切入的类， aop:aspect表示要切入的面。



### 使用注解实现AOP

- 创建AnnotationPointCut类，并在xml文件中注册

  这里的@Aspect表示该类是要切入的面，@Before则表示执行前的操作@After表示执行后，@Around表示环绕在方法前后，这两种包围是并行的，即交错执行，

  后面都要加上被包围的方法。

```java
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("============执行前===========");
    }

    @After("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("============执行后============");
    }

    @Around("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("环绕前");
        //执行方法
        Object proceed = jp.proceed();
        
         //获得方法的签名
        System.out.println(jp.getSignature());
        System.out.println("环绕后");
    }
}

```



xml文件需要加上：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.ghj.service.UserServiceImpl"/>
    <bean id="log" class="com.ghj.log.Log"/>
    <bean id="afterLog" class="com.ghj.log.AfterLog"/>

    <bean id="diy" class="com.ghj.diy.DiyPointCut"/>
    <bean id="annotationPointCut" class="com.ghj.diy.AnnotationPointCut"/>

    <aop:aspectj-autoproxy/>
</beans>
```



## 整合Mybatis

### 回忆Mybatis

- 编写实体类；
- 编写核心配置文件；
- 编写接口；
- 编写接口的xml文件；
- 测试



### Mybatis-Spring

- 编写Spring的核心配置文件；
- 编写Mybatis的核心配置文件；
- 编写实体类；
- 编写接口；
- 编写接口的xml文件；
- 编写接口的实现类；
- 测试；



Mybatis核心配置文件里的参数都可以在Spring配置文件中配置， 将Mybatis里的sqlSessionFactory注册在Spring里面， 然后再将Mybatis里的sqlSession也注册在Spring里面，这样Mybatis里需要的所有参数就都 可以直接在Spring里拿取。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/malajava?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/ghj/mapper/*.xml"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
    
</beans>
```





再将xml文件包含在applicationContext.xml总xml文件中



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">


    <import resource="spring-dao.xml"/>

    <bean id="userMapperImpl" class="com.ghj.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>

    
</beans>
```





相对于Mybatis，我们可以再创建一个UserMapperImpl类实现UserMapper接口，这样我们还可以 在Spring里注册这个 实现类， 这个实现类里的方法实现可以通过 Spring里注册的 sqlSession来获取对应的Mapper，这时需要有sqlSession的set方法 ， 再来实现里面的方法， 方法的主体其实还是在接口的xml文件里，

```java
public class UserMapperImpl implements UserMapper{

    private SqlSessionTemplate sqlSession ;


    @Override
    public List<User> select() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.select();
    }


    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}

```

 



这样测试类里的代码就完全是Spring的代码，不需要Mybatis的代码

```java
public class MyTest {
    @Test
    public void test01() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl impl = context.getBean("userMapperImpl", UserMapperImpl.class);
        List<User> users = impl.select();
        for (User user : users) {
            System.out.println(user);
        }
    }
}

```



### 第二种方式整合

整合的理念就是， 把Mybatis里需要的一些类，注册在 Spring里面，这样就可以完全用Spring的代码来写，而核心就是，要注册sqlSessionFactory与sqlSession类，从而可以在自己写的实现类里面直接获取，不用去自己创建一个sqlSessionFactory再去拿sqlSession，而现在可以写一个实现类， 继承自SqlSessionDaoSupport， 可以直接利用其getsqlSession()方法来获取sqlSession，而且Spring里也可以不用再去注册sqlSession，只需要注册sqlSessionFactory，因为这个SqlSessionDaoSupport类里面有一个sqlSessionFactory，通过其set方法给sqlSessionFactory赋值不为空，这样就可以获得sqlSession

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> select() {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.select();
    }
}
```





测试一样可以通过

```java
public class MyTest {
    @Test
    public void test01() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper impl = context.getBean("userMapperImpl2", UserMapper.class);
        List<User> users = impl.select();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
```



## 事务

在计算机中是指访问并可能更新数据库中各种数据项的一个程序执行单元。事务由事务开始和事务结束之间执行的全体操作组成。这组操作要么全部执行，要么都不执行。



- 事务的ACID原则：

  - 原子性（atomicity）。一个事务是一个不可分割的工作单位，事务中包括的操作要么都做，要么都不做。

  - 一致性（consistency）。事务必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。

  - 隔离性（isolation）。一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。

  - 持久性（durability）。持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。



如果一些方法没有事务的这个性质， 那么如果在这些方法中执行别的一些方法 ， 可能其中一个方法出错了， 但是这个方法之前的方法还是生效了，这样并不是我们想要的。

```java
public List<User> select() {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(2, "罗静", 21);
        mapper.add(user);
        mapper.delete(1);
        return mapper.select();
    }
```



- 如果执行这个查询方法， 里面的delete的SQL语句写错了， 那么他还是会增加这个用户。





- 在Spring中给方法添加事务时需要加入事务管理器，可以直接在bean中注册，然后可以Spring对事务的一些操作， 给一些类的方法通过AOP的方式加上事务的性质：

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="select" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <aop:config>
        <aop:pointcut id="pointCut" expression="execution(* com.ghj.mapper.UserMapperImpl2.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pointCut"/>
    </aop:config>
```



**propagation（传播）**默认为REQUIRED，一共有七种方式。
