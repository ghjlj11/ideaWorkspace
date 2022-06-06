# SB



## ANSI、Unicode、UTF-8

## 	ANSI码

ANSI编码是一种对ASCII的拓展：ANSI编码用0x00~0x7f （即十进制下的0到127）范围的1 个字节来表示 1 个英文字符，超出一个字节的 0x80~0xFFFF  范围来表示其他语言的其他字符。也就是说，ANSI码仅在前128（0-127）个与ASCII码相同，之后的字符全是某个国家语言的所有字符。值得注意的是，两个字节最多可以存储的字符数目是2的16次方，即65536个字符，这对于一个语言的字符来说，绝对够了。还有ANSI编码其实包括很多编码：中国制定了GB2312编码，用来把中文编进去另外，日本把日文编到Shift_JIS里，韩国把韩文编到Euc-kr里，各国有各国的标准。受制于当时的条件，**不同语言之间的ANSI码之间不能互相转换，这就会导致在多语言混合的文本中会有乱码。**GBK就是ANSI里的一种。

##    **Unicode编码**


​     为了解决不同国家ANSI编码的冲突问题，Unicode应运而生：如果全世界每一个符号都给予一个独一无二的编码，那么乱码问题就会消失。这就是Unicode，就像它的名字都表示的，这是一种所有符号的编码。
 Unicode标准也在不断发展，但最常用的是用**两个字节表示一个字符**（如果要用到非常偏僻的字符，就需要4个字节）。现代操作系统和大多数编程语言都直接支持Unicode。但是问题在于，原本可以用一个字节存储的英文字母在Unicode里面必须存两个字节（规则就是在原来英文字母对应ASCII码前面补0），这就产生了浪费。那么有没有一种既能消除乱码，又能避免浪费的编码方式呢？答案就是UTF-8！

##    Utf-8编码

​    这是一种**变长**的编码方式：它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度，当字符在ASCII码的范围时，就用一个字节表示，保留了ASCII字符一个字节的编码做为它的一部分，如此一来UTF-8编码也可以是为视为一种对ASCII码的拓展。值得注意的是unicode编码中一个中文字符占2个字节，而UTF-8一个中文字符占3个字节。**从unicode到uft-8并不是直接的对应，而是要过一些算法和规则来转换。**
 在计算机内存中，统一使用Unicode编码，当需要保存到硬盘或者需要传输的时候，就转换为UTF-8编码。
 用记事本编辑的时候，从文件读取的UTF-8字符被转换为Unicode字符到内存里，编辑完成后，保存的时候再把Unicode转换为UTF-8保存到文件。



## git



- 对于error: failed to push some refsto‘远程仓库地址’
   1 使用如下命令

> git pull --[rebase](https://so.csdn.net/so/search?q=rebase&spm=1001.2101.3001.7020) origin master

2 然后再进行上传:

> git push -u origin master



## 类加载，注解，反射



### 类加载

- 类 存在生命周期 ：

  > 加载、连接、初始化、使用、卸载

- 当程序主动使用一个类的时候， 那么如果在JVM 中， 没有找到这个类，那么 系统就会通过 加载、连接、初始化 三个步骤 将类进行初始化 。

- 当Java程序首次通过下面6种方式来使用某个类或接口时，系统就会初始化该类或接口 。

  > 创建类的实例。为某个类创建实例的方式包括：使用new操作符来创建实例，通过反射来创建实例，通过反序列化的方式来创建实例 调用某个类的类方法（静态方法） 访问某个类或接口的类变量，或为该类变量赋值 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象 初始化某个类的子类。当初始化某个类的子类时，该子类的所有父类都会被初始化。 JVM启动时被标明是启动类的类。简而言之，就是使用java.exe命令执行的类，也就是有main方法的类

  以上 6 种 情况 被称为 主动使用， 其他的所有情况被称为 被动使用 。**被动使用不会导致类的初始化， 不被初始化就表示类的一些static的代码也不会被执行** 。



### 反射

- 通过反射我们可以获得到类里面的任何东西， 但是不能实例化一个枚举类。

```java
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(S1.P);
        Class<S1> s1Class = S1.class;
        System.out.println(s1Class.getClassLoader());

        System.out.println("------------------------------------------------");
        //反射获得实例对象
        Class<S1> aClass = S1.class;
        Class<String> stringClass = String.class;
        Constructor<S1> constructor = aClass.getConstructor();
        constructor.setAccessible(true);
        S1 s = constructor.newInstance();
        System.out.println(aClass);

        System.out.println("------------------------------------------------");
        //反射给对象的字段赋值
        Field[] fields = aClass.getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        Field p = aClass.getDeclaredField("s");
        p.set(s, new String("222"));
        System.out.println(s);
        Field k = aClass.getDeclaredField("k");
        k.setAccessible(true);
        k.set(s,12345);
        System.out.println(s);

        System.out.println("------------------------------------------------");
        //反射获得类的方法
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        //通过名字获得声明的方法
        Method method2 = aClass.getDeclaredMethod("method4");
        method2.setAccessible(true);
        //如果获得实例方法， 那么invoke必须传一个对应的实例， 如果类方法， 那么invoke实例随意
        Object invoke = method2.invoke(new Object());
        System.out.println(invoke);

        System.out.println("------------------------------------------------");
        //通过反射获取内部类
        Class<?>[] classes = aClass.getDeclaredClasses();

//        for (Class<?> a : classes) {
//            String name = a.getSimpleName();
//            System.out.println(name);
//            if("Bk".equals(name)){
//                Constructor<?> aConstructor = a.getConstructor(null);
//                System.out.println("000000");
//                Object o = aConstructor.newInstance(null);
//                System.out.println(o);
//            }
//        }
        System.out.println(Arrays.toString(classes));
        System.out.println(Arrays.toString(aClass.getClasses()));
        //获得所属类， 方法或者字段调用。
        Class<?> declaringClass = method2.getDeclaringClass();
        System.out.println(declaringClass);

    }
}
```





### 注解



- 注解，其实是代码里的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相应的处理。

- 在 注解上 使用的注解 被称为 元注解。

  - @Documented : 被 @Documented 修饰的注解， 可以被 javadoc 工具识别，并提取到文档上 。
  - @Inherited : 基本不用
  - @Repeatable ： 使用意义不大， 仅仅表示 一个 注解 可以重复使用。
  - @Target : 标注注解 能够在哪些地方上使用 : 构造、类（接口）、成员变量、参数、方法等
  - @Retention ： 决定注解 保留到什么时候
    - SOURCE : 在 源代码中存在
    - CLASS : 在 .class 文件中存在
    - RUNTIME : 在 运行期间 存在 。

  自定义注解 ： 声明的形式 ： `修饰符 @interface 注解名称{}`

- 我们可以通过反射查看 MVC里的一些注解的 属性

```java
@SpringBootTest
class SpringBoot06SecurityApplicationTests {

    @Test
    void contextLoads() {

        //通过反射获得注解， 然后获得注解里的属性
        Class<MyController> aClass = MyController.class;
        RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
        String[] strings = annotation.value();
        System.out.println(Arrays.toString(strings));

        //反射获得方法， 方法上的注解， 注解里的属性。
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println(methods.length);
        for (Method method : methods) {
            RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
            String[] path = annotation1.value();
            System.out.println(Arrays.toString(path));
        }
    }

}

```



可以查看到RequestMapping映射的那些请求路径。





## HashMap详解



### put过程



- 首先查看该put的位置是否是null， 如果是， 那么直接插入。

- 如果不是的话判断该位置是否为链表。
  - 如果是链表， 那么就去遍历一遍链表，直到找到put的key==链表节点的key， 或者equals相等， 那么就直接替换该节点， 并且维护， 如果没有找到， 那么就直接put到后面， 然后，还需要判断链表长度是否大于8， 并且tab长度是否大于等于 64 ， 如果都满足， 那么就要转化成一棵树。
  - 如果是一棵树，那么就要去搜索， 看看是大于当前节点的hash还是小于， 大于的话， 那么就往左边搜索， 小于就往右边， 如果期间有相等的， 就是要put的key==节点的key， 或者euqals相等， 那么直接返回该节点并替换，如果期间出现hash冲突， 就是hash相同了，但是这两个key又不相等， 就去  看看这个put的key是否实现comparable接口， 以及求出put的key与该节点的key的compare之后的值， 如果是没有实现该接口， 或者比较的值不一样， 那么就通过递归去寻找该节点的左右节点下的所有节点， 看看有没有要put的节点的key， 找到了那么就直接返回 ， 没有的话那就暴力比较，调用tieBreakOrder(k, pk)， 要么返回-1， 要么1， 要么下一个往左边找， 要么就往右边， 直到找到后面的节点， 该节点左边或者右边是等于null的， 那么就看看是在左边还是右边， 就是比较的是-1， 还是1， 然后直接插入进去， 并且维护双链表关系以及树之间的关系， 然后还需要进行旋转， 因为之前破坏了平衡， 并且移动该头节点到书的根节点上。 



## LinkedHashMap

- 一个基于双端链表的Map， 继承了HashMap， 实现了Map

- 需要维护这个双端链表，有三个方法

  - **afterNodeRemoval**， 维护移除一个节点后的链表，方式很简单， 只需要对前后两个链表的after与before操作就行

  ```java
  void afterNodeRemoval(Node<K,V> e) { // unlink
          LinkedHashMap.Entry<K,V> p =
              (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
          p.before = p.after = null;
          if (b == null)
              head = a;
          else
              b.after = a;
          if (a == null)
              tail = b;
          else
              a.before = b;
      }
  ```

  

  - **afterNodeInsertion**， 维护新增一个节点后的链表，判断是否需要删除最年长的一个节点，也就是最开始的节点， **removeEldestEntry**这个方法默认返回是false， 有需要的话就可以修改。

  ```java
  void afterNodeInsertion(boolean evict) { // possibly remove eldest
          LinkedHashMap.Entry<K,V> first;
          if (evict && (first = head) != null && removeEldestEntry(first)) {
              K key = first.key;
              removeNode(hash(key), key, null, false, true);
          }
      }
  ```

  

  - **afterNodeAccess**， 维护获取（get）一个节点后的链表， 把这个节点放到最后边，在原来的位置删除， 并维护前后节点。

  ```java
  void afterNodeAccess(Node<K,V> e) { // move node to last
          LinkedHashMap.Entry<K,V> last;
          if (accessOrder && (last = tail) != e) {
              LinkedHashMap.Entry<K,V> p =
                  (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
              p.after = null;
              if (b == null)
                  head = a;
              else
                  b.after = a;
              if (a != null)
                  a.before = b;
              else
                  last = b;
              if (last == null)
                  head = p;
              else {
                  p.before = last;
                  last.after = p;
              }
              tail = p;
              ++modCount;
          }
      }
  ```

  





## 关于JVM



![image-20220602200741532](img\jvm\01.png)



#### 双亲委派模型

![02](img\jvm\02.png)



双亲委派模型的工作过程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加 

载这个类，而是把这个请求委派给父类加载器去完成，每一个层次的类加载器都是如此，因此所有的 

加载请求最终都应该传送到最顶层的启动类加载器中，只有当父加载器反馈自己无法完成这个加载请 

求（它的搜索范围中没有找到所需的类）时，子加载器才会尝试自己去完成加载。 



使用双亲委派模型来组织类加载器之间的关系，一个显而易见的好处就是Java中的类随着它的类 

加载器一起具备了一种带有优先级的层次关系。