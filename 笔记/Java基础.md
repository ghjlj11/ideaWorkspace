# 笔记

## Java 的特点

- 简单：省略了C++ 中大量的具有争议的内容
- 面向对象
- 分布式 ： Spring Cloud
- 健壮性 ： 有更严格的 检查 ； 在 编译阶段与运行阶段都会有检查
- 安全性
- 可移植性（跨平台） ： 写过一次之后，如果换一个平台（Linux、Mac、Windows、Unix） ，平台上有Java的运行环境，则不用再次书写内容，可以直接运行 。
- 多线程 ： 多个线程处理任务，而且还处理的很好。（一直是一个重点 ， 也是一个难点）参考书：**Java并发编程的艺术**、**Java多线程编程核心技术**
- 动态性

## 软件安装

- JDK的安装
  - 下载：[Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/)
  - 安装：注意的点：
    - 不要修改JDK 的安装路径
    - 在系统变量中设置 JAVA_HOME 属性 ，指向 Java 的安装目录
    - 修改系统变量 Path ， 添加 内容`%JAVA_HOME%\bin` 。
- IDE的安装

## 反编译

在Java中，可以使用 `javap` 命令来执行反编译 ， 可以看到的是 虚拟机命令。此时，需要注意：以后学习虚拟机的话，可以使用该命令进行查看具体的操作。
在out文件里可以找到相应的class文件。

## 注释
Java中的注释分为三种：
- 单行注释 ； 形式如下：
  ```text
    // 我是一个单行注释 
    java代码
  ```
  
- 多行注释 ， 形式如下：
  ```text
  /* 我是一个 多行注释  */
  java代码
  ```

- Javadoc 注释 形式如下：
  ```text
  /**
   * java doc 注释 
   * @param  
  */
  java代码 
  ```

## Idea 中常用的插件
- Alibaba Java Coding Guidelines  用来检测代码的可读性。会有各种各样的建议。
- Chinese(Simplified) Language  ： 语言汉化包
- Jclasslib Bytecode Viewer ： 用来 进行查看 字节码指令的工具 （学习Java虚拟机使用）
- Leetcode Editor ： 力扣 的支持 
- Translation : 翻译！
- Free Mybatis Plugin ： 以后学习 Mybatis 的时候使用 
- SequenceDiagram ： 用来生成序列图（ 当方法调用特别多的时候，可以快速查看方法的调用情况 ）
- Rainbow Brackets ： 彩虹括号！
- Codota AI .....  :  机器人帮你写代码！

## JDK 、JRE、 JVM

- JDK ： Java 开发工具集，其中包含了 JRE ，最大。
- JRE ： Java 运行环境， 其中包含了 JVM ， 第二。
- JVM ： Java 虚拟机！将Java代码翻译成计算机可识别的语言。最小。

## Java API 文档
- ctrl键可以查看Java源码。
- API ： Application Programming  Interface  应用程序接口 ， 是一些预先定义的接口（如函数、HTTP接口），或指软件系统不同组成部分衔接的约定。
- Java API 文档 ： Java 内部所有的接口或类 中的方法 的说明文档！
- 下载路径 ： https://www.oracle.com/java/technologies/javase-jdk17-doc-downloads.html

## Java 中的关键字 
关键字就是 具有一些特定意义的单词， 而这些单词已经在Java 内部使用了，所以一般不能直接使用。
static public void ....
保留字 ： goto、const ； 就是目前Java 中暂时没有使用 ，可能以后会使用； 但是也不允许开发者使用。

## 标识符
标识符就是 Java 中对 变量、类、接口、枚举、记录、方法等命名的一个 字符序列 。 

字符序列 ： 由多个字符组成 的一个 序列 ；例如：Person , eat , school_name 

粗暴理解 ： 自己能决定起名字的地方就是一个 标识符 

### 起名的规则
在Java 中， 只能由 字母、数字、下划线、美元符号 组成，不能以数组开头 。可以使用中文，但是不建议 。 

从狭义上来说， 只能有  字母 、下划线 组成 

### 常用的操作 
- 包名： 全部小写，如果有单词分割，则使用 下划线 进行链接 ；比如：software 。
  - 注意：如果一个项目中，存在多级包的话，那么 中间使用 英文句号 来分割 ；例如：ecut.model ; ecut.xxxxxxx
- 类名、接口名、记录名：首字母大写，如果有多个单词，则 每个单词的 首字母 大写！
- 变量名、方法名 ： 全部小写；如果有多个单词，则第一个单词的首字母小写，后续的每个单词的首字母大写；
  - 例如：eat() ; doHomework() 
- 常量名 ： 全部大写， 如果有多个单词，则单词与单词之间使用 下划线进行链接 
  - 例如：String SCHOOL_NAME = "东华理工大学" ; 
- 以后在取名的时候，需要保证整个名字都具有一定的含义 ，争取做到“见名知意”
  - 存在特殊情况 ： 在声明 基本数据类型的时候，仅可以采用字符： m n i j 
  - 在循环中，一般循环的变量 可以是 m n i j k 

## 数据类型
Java 是一个 强类型语言 ， 每一个变量都有自己明确的类型 。如果类型不一样，一般不会轻易的重新赋值。

但是 在JDK1.8 之后，提出了 类型推断，也就是可以使用 `var` 关键字来声明一个变量，这个变量的类型由Java自动推断。

在Java 中，分为两种数据类型 ： 基本数据类型 、 引用数据类型 。 

在 Java 中 ， 除了 基本数据类型，都是 引用数据类型 。

### 变量的分类 
变量
- 成员变量 
  - static 修饰的变量 （静态变量 ） ： 在当前类中一直有效
  - 实例变量 ： 在一个对象中有效 。
- 局部变量 ： 在一定的区域内是有用的
  - 形参 ： 在一个方法中有效
  - 方法中的局部变量 ： 在 一个方法中有效
  - 代码块 中的局部变量 ： 在 一个具体的代码块中 有效  

### 变量的声明
语法规则 ：
```text
数据类型 变量名( 同时也是一个标识符 ) [ = 具体的值 ] ;
```
单独声明一个变量 ：
```text
var s ; 
```
声明变量并赋值 ：
```text
var s  = "可口可乐其实不怎么好喝，百事才好喝！"  ; 
```
声明完变量之后，连续赋值 ： 不允许使用 var 来连续声明变量 
```text
String  s , b , shi , ni ; 
s = b = shi = ni  = "是不是你！"  ; 
```

## 基本数据类型 
基本数据类型可以分为 ： 数值类型 、字符类型、boolean 类型 

### 数值类型
每一个数字类型都有自己的范围和长度限制， 不受具体的系统影响。

在 Java 中，如果声明了一个 整数，默认是 int 类型 ， 注意：如果声明的数字在 byte 与 short 范围之中，就会自动变成 byte 与 short ； 
如果声明了一个 小数（浮点数） ， 默认是 double 类型。

#### 整数类型
一共有 4 个， 分别是 ： byte、short 、int 、long 

占用字节：
- byte 占用 1 字节， 共计 8 位 ， 其中 最高位是 符号位，那么有效数字就是 7位，范围就是 ： -2^7 ~ 2^7 - 1 
- short 占用 2 字节， 共计 16 位 ， 其中 最高位是 符号位，那么有效数字就是 15 位，范围就是 ： -2^15 ~ 2^15 - 1 
- int 占用 4 字节， 共计 32 位 ， 其中 最高位是 符号位，那么有效数字就是 31 位，范围就是 ： -2^31 ~ 2^31 - 1 
- long 占用 8 字节， 共计 64 位 ， 其中 最高位是 符号位，那么有效数字就是 63 位，范围就是 ： -2^63 ~ 2^63 - 1 

在声明整数的时候，可以使用 不用的进制来声明：二进制、八进制、十进制、十六进制。

为了防止 long 类型的数据 与 int 类型的数据 混淆， 所以在 long 类型数据的 后边，加上了 `l` 或 `L` 从而表示long 类型的数据，建议加上 `L`。

#### 小数类型 （浮点数类型）
一共有2个 ， 分别是 float 、double ; 在给对应的变量赋值的时候，可以直接使用一个具体的小数，或科学计数法

- float ：单精度浮点数 , 有效位数8位 ，占用4个字节，采用IEEE754标准存储 在声明的时候，需要在值后边加上指定的符号 `F` 或 `f` ;
- double : 双精度浮点数，有效位数16位 ， 占用8个字节，采用IEEE754标准存储

在进行计算的时候， 不够准确。
### 字符类型 
有一个 ： char , 采用 英文单引号 括起来的单个字符 ，占用空间 2 字节 。

Java 中采用 Unicode 编码 ，可以存放一个字符（字母、汉字、符号） ; 可以在 char 类型中，使用 unicode 形式的 字符。

char 类型中，允许使用 转义字符 。形式: `\英文字母或特殊符号` ; 比如常见的 ：`\n` 、`\t` 、`\\` 
### boolean 类型
有一个 ： boolean , 用来表示逻辑判断的结果，仅可以是 true、false。

在 Java 中， 仅可以使用 true 和 false 来表示 boolean 类型的值；不能使用 0 和 非0 来表示 boolean 类型的值。

注意 ： boolean 占用 1 bit ， 但是在Java 中， 都是以 字节 为单位，尽管 boolean 占用了1bit ，最终也是占用了1字节。

0为false，1为true。
### 基本数据类型转换

- 自动类型转换 ： 容量小的类型会自动转换成容量大的数据类型
  - 特殊情况 ： char 类型在 进行运算的时候， 会转换成 int 
  - 有多种类型的数据混合运算时，系统首先自动将所有数据转换成容量最大的那种数据类型，然后再进行计算。
  - byte,short,char之间不会相互转换，他们三者在计算时首先转换为int类型。
- 强制类型转换 ： 自动类型转换的逆过程，将容量大的数据类型转换为容量小的数据类型。使用时要加上强制转换符：`()`，但可能造成精度降低或溢出,格外要注意。

- 练习 ： 使用 excel 表格 总结 八种基本数据类型之间的 转换规则 

## 运算符 
### 算数运算符
```text
+ - * / % ++ -- 
```

### 位运算符
```text
>> : 右移
<< ： 左移 
>>> ： 无符号右移
^（异或） ~（取反） &（位与） |（位或） 
```

### 比较运算符
```text
> < >= <= != == 
```
比较运算符 返回的结果是 boolean 类型的结果 。

== 比较的注意点  ：
1. 如果比较的是 数值类型 ， 如果数据类型 不一样 ， 只要值相等，就返回 true 。
2. 如果比较的是 引用数据类型， 必须指向同一个对象 ， 才返回true 。
3. 如果两个引用对象直接使用`==`进行比较，那么比较的就是变量中的 值（地址）
4. 如果 两个引用对象 `同源` ， 则可以使用 == 比较 。

同源 ： 直接或间接的拥有 同一个 父类 或者是 具有一定的 父子 关系   。

!= 不等于的比较情况与 `==` 情况差不多 
### 逻辑运算符
```text
&& || ! 
```
逻辑运算符 操作的是 boolean 类型的值 ， 结果返回的是 boolean 类型的值 

- && ： 逻辑与 ： 都是true是 true , 有一个是 false 结果就是 false ；
- || : 逻辑或 ， 有真（true）就是真（true） ， 注意：如果第一个的值 是false ， 那么就继续向后运行；如果第一个值是true ，则直接返回true
- ! : 逻辑非 ， 取反 ，true 取反就是 false 

### 三目运算符（ 三元运算符 ）
语法是：
```text
boolean类型 ? 表达式1 : 表达式2
```
boolean 类型是true ，运行表达式1 ； 如果是boolean 类型是 false ， 则运行表达式2 。

如果想要 接收 三目运算符，那么 具体的类型应该是 表达式的类型 ； boolean 类型可以通过 计算或方法得出。


## 分支结构 
- if …… else …… 
  - if 后边的小括号中， 要传递一个 boolean 类型的值（boolean 类型值的产出有 ： 方法、逻辑运算符、比较运算符 ）
- switch …… case 
  - 建议 ： 每一个 case 后边都加上一个 break ， 表示 终止 当前 switch 中的case 分支 。
  - 如果每个case 后边都不加上 break ，结果就是 匹配到的case 之后所有的代码都会执行 
  - switch 中 可以有 default ， 作用是 如果没有匹配的内容，将执行 default 中的内容  ； 建议必须写！
  - switch 中的 default 一般是在 整个switch 的最后边
  - switch 括号中可以传入的类型是 ： byte、short、char 、int 、枚举、String 、以及前边四个基本数据类型的包装类（Byte、Short 、Integer、Character）
  - case 语句中的 值 一定是 确定的值 （常量），不能是 变量或 表达式 ; 值互不相同（基本常识）.
- 分支嵌套 
  - if 后边执行的代码，放入另一个 if 或 switch 
  - 一般可以用 switch 声明的 分支，都可以使用 if 来进行改造 。
- JDK17 新增了 switch 的 preview 版本 。该版本在未来可能会留下，也有可能会删除 。（switch 增强）

## 循环结构 
循环结构 ： 在满足一定条件的情况下， 反复 执行某些东西（代码）

循环四要素 ：
1. 初始条件
2. 判定条件 
3. 迭代部分 （步进） step  : 有增加或减少 （ 有增量 ）
4. 循环体 ： 被 反复执行的代码 




## 数组

### 概念
数组(Array)，是多个**相同类型数据**按一定顺序排列 的集合，并使用**一个名字命名**，并通过**编号**的方式 对这些数据进行统一管理。

数组是一个 **引用数据类型** 。但是数组中的元素 可以是 任意类型 。

数组的长度一旦固定，则不可以更改。 

可以通过下标（索引）的方式来 获取（赋值） 数组指定的元素， 这种方式很快。

分类 ： 一维数组 、 二维数组 ....

### 一维数组 
#### 声明语法 
```
数据类型[] 变量名 ; 建议使用这种方式

数据类型 变量名[] ; 
```
注意：在声明变量的时候，不可以声明数组的长度 。

#### 初始化
- 动态初始化 ： 数组的声明与 赋值 是分开的。
- 静态初始化 ： 数组的声明与具体赋值是在一起的。

#### 数组中的常用概念
- 元素 ： 就是数组中的 内容 ，也就是 数组通过下标访问到的那个 东西 ，其中的内容就是元素 。
- 下标 ： 如果想要访问到数组中的元素，可以通过 非负整数 的索引变量来访问，索引又称下标。下标从0开始，最大的下标是 (数组的长度-1)
- 长度 : 数组的长度是 在初始化的时候就已经确定了， 可以通过 length 来进行获取

#### 数组的默认初始值
- byte 的默认值是 0
- short 的默认值是 0 
- int 的默认值是 0 
- long 的默认值是 0L
- float 的默认值是 0.0F
- double 的默认值是 0.0 
- char 的默认值是 \u0000
- boolean 的默认值是  false
- 引用数据类型 的默认值是 null 

#### 数组的内存模型
示例：
```text
int[] ints = new int[2] ; 
```
- 在 栈中 创建空间，用于 存放变量 ： `int[]` ints ; 
- new 负责在 堆（heap） 中 创建一个区域 ， 大小由 `int[2]` 这个决定 。
- 已经在堆中创建了指定的区域，通过 `=` 赋值运算符，将在堆中创建的区域的首地址赋值给变量 `ints`

### 二维数组 以及 多维数组 
语法如下：
```text
二维数组 ： type[][] vars = new type[length][length] ;

多(n)维数组 ： type[]*n vars = new type[length]*n ; 
```
注意：在Java中，一般不会超过 2维数组。

## 数组的复制

使用核心API :
```text
System.arraycopy( 原数组 , 原数组的起始位置 , 目标数组 , 目标数组的开始位置 , 个数 ) 
```

## java.util.Arrays 
- Arrays.asList( T... a ) : 将 相同类型的多个参数 封装到 List 中 
- Arrays.binarySearch : 使用 二分搜索算法 来 搜索指定的数组 
- Arrays.compare : 比较两个数组 ,返回值可能是 0 ， 负数，整数
- Arrays.copyOf  与 Arrays.copyOfRange 
- Arrays.equals : 相等 
- Arrays.fill : 填充 ,将数组中的元素全部以 固定的值 进行填充
- Arrays.hashCode ： 计算数组的指定的hash值 有自己的想法
- Arrays.sort() : 按照升序进行排序 
- Arrays.toString();把数组打印成字符串输出。


## 面向对象编程 （OOP）
- 熟悉面向对象程序设计的概念 
- 面向对象程序设计的重点就是类的设计，类的设计，其实就是类的成员设计。
### 创建一个类 
语法：
```text
[修饰符] class 类名{ 类体：包含各种成员 }
```
注意： class 表示的是一个 类 ， 其中修饰符可以有，也可以没有 ； 类名一般是 首字母大写；如果有多个单词构成一个类，那么每个单词的首字母都大写。

- 创建类的对象
```text
类的名字 变量名 = new 类的名字() ; 
```
标准语法：
```text
数据类型 变量名 【= new 构造方法() 】 ; 
```
- 访问对象中的字段和方法
```text
对象.字段 
对象.方法
```
`.` 表达的含义是 ： `的`

### this 关键字
- `this`关键字 可以理解成 `我` ， 本身的含义 指代 当前对象 。

- this关键字可用来引用当前类的 实例变量 。  谁调用就是谁 
- this关键字可用于调用 当前类中实例方法(隐式)。 在实例方法中，可以直接调用 其他的实例方法，而不用书写this关键字 ， 会自动加上， 但是建议书写！
- this()可以用来调用当前类的 构造函数。 
- this关键字可作为调用实例方法中的 参数传递。 调用实例方法的时候，可以将 this 作为参数进行传递， 此时的this 依旧是 当前对象。
- this关键字可作为参数在构造函数调用中传递。  此时 this 依旧是一个 当前对象
- this关键字可用于从方法返回当前类的实例。 在方法中 ， 使用 this 作为一个返回 。返回当前对象，谁调用就返回谁！

总结  : 
- this 关键字 代表当前对象 .
- this() : 调用 本类中的其他构造方法.

### 内存分析 之 创建对象
```text
class Phone{
    String name = "中兴" ; 
    static double weight = 5.6 ; 
    double height ; 
}
Phone phone = new Phone() ; 
```

当执行 `Phone phone = new Phone() ; ` 的时候，首先会在 `栈（stack）` 中 声明 `Phone`类型的变量 `phone`  ， 
然后在堆（heap）中开辟空间【是new 的作用】 ，会为 Phone 类中的字段（Field）设置默认值（前提是没有进行指定值） 
并 执行 `Phone()` 中的代码（构造器中的代码），最后通过 `=` 将 堆中的地址 复制给 `phone` 变量。

static 修饰的内容会被放在方法区（Method area）中

### 方法
语法：
```text
【修饰符】 返回类型 方法名( [ 形参列表] ){
    // 方法体 
}
```
注意的点 ： 方法内部不能再次定义方法 ， 但是可以调用方法。如果方法没有任何返回，则返回类型一定要写，是 `void` 。返回类型可以是 基本数据类型，也可以是引用数据类型。 
方法的调用过程中，可以 调用 该类中的 字段 与方法 ； 如果在方法中，调用了自身， 此时这个方法被成为 递归方法， 一定要注意，递归要有终止条件。

方法的返回 由 `return` 关键字 进行返回， return 表示一个方法的结束 ； 当有返回的时候，方法可以被变量所接收。

#### 形参与实参
形参 ： 形式参数 ， 在声明方法中， 在 ( ) 中书写的 参数 。

实参 ： 实际参数， 调用方法中 直接传入的参数

#### 方法的返回值 
利用 return 进行返回 ；形式：`return 数据` 。 

return 表示一个方法的结束 ， 如果 方法的返回值 是 void ，则 不需要书写 return 。 
如果方法中存在指定的返回类型，则可以利用 return 关键字 进行将指定的数据类型返回。

方法的返回值 一旦是 void ， 则 不可以使用变量接收； 如果返回值不是void ，则可以 使用与 方法相同的返回类型的变量接收 。

可以利用 return 表示一个方法的结束，但是，有时候 在循环中 使用 return来让循环结束。

#### 方法的属主
从 逻辑上看， 方法只能属于 对象 或 类 。

- static 修饰的方法 应该是由 类名 来调用
- 没有使用 static 修饰的方法（实例方法） 通过对象 来调用。

#### 方法重载
**同一个类**中有**两个或两个以上**方法**同名不同参**的方法，称为方法重载

特点：
- 同一个类 ， 如果存在继承，那么需要保证 继承来的方法 与 本类中自己的方法 同名不同参
- 同名 ： 方法名相同
- 不同参 ： 参数 个数、顺序、类型  不一样 
- 返回值 与 修饰符 不做要求 。

#### 可变长参数
参数在 方法调用的时候出现 ； 而参数的个数，相同类型可以有多个 ， 但是 可以不用在 方法调用时 传递那么多的参数。

形式  ：
```text
在方法中的形参列表中使用 ： 数据类型... 形参的名字
```

通过 可变长参数 的 toString形式 ， 可以发现，与数组的格式是一样的，那么就可以将可变长参数当成数组来处理。

而数组的长度是由 调用方法传入的实参 个数 来决定的 ， 而实参 可以是 0 个 、1 个 、多个都可以。

可变长参数一定是在 形参的最后一位， 并且一个方法中，只能存在一个 可变长参数。

#### 参数传递机制
在Java 中， 只存在一种 参数传递机制 ： 值传递。即将实际参数值的副本（复制品）传入方法内，而参数本身不受影响。

- 基本数据类型 : 将实参基本数据类型变量的“数据值”传递给形参
- 引用数据类型 : 将实参 引用数据类型变量 的 ”地址值“ 传递给形参 

### 递归的想法
递归的想法： 方法内部调用 方法自身。

终止的时间 ： 有了终止条件 ，有return ； 内存溢出 : java.lang.StackOverflowError 。

### 封装
通过某种手段 , 限制 访问 类的 成员 . 可以通过 公开的方法 间接的访问 类的成员.

在Java 中, 封装就是 将 一些内容 藏起来 , 不让 其他的类 直接访问 到该内容,而是通过其他的手段 间接的访问.

一句话 : 该藏的藏起来, 该露的露出来 . 

通过 权限修饰符 可以将 一些内容 藏起来 . 注意:权限修饰符一共有 3个, 但是 访问级别有 4 个 .

权限修饰符 : private ( 私有的 ) , protected( 受保护的  ) , public( 公开的 )

访问级别 : private , 缺省的( 来自于C++ , C++ 中关键字为default , 在Java 中不存在对应的关键字) 或 默认的, protected , public  

private : 修饰的内容只能在 本类中访问 , 只要超过个范围 , 则不允许访问.

默认的(缺省的) : 修饰的内容只能在 本类 和 同包 访问

protected : 修饰的内容只能在 本类  同包 和 子类 访问

public : 任意访问.

需要参看 Person.java  , 主要参看 get/set 方法 .

属性与字段的区别 : https://blog.csdn.net/chenchunlin526/article/details/71424844
- 字段是 在 类中声明的, 同时做为 类的成员而出现 
- 属性 是 get 方法 去掉get 之后, 首字母变小写之后 留下的单词 .

### Java 中的 包 
包 就是 文件夹 . 包的关键字 : package  ; 而 package 关键字出现的位置 是 java文件中 除了 注释 的第一行! 使用包的好处是可以快速搜索.

一般在读 包的时候, 只会说 后边的 . java.lang 包, 就读作 lang 包 

包的作用:
- 如同文件夹一样，包也采用了树形目录的存储方式。同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。因此，包可以避免名字冲突。
- 可以进行访问权限的区分 , 主要是 protected 和 默认的 访问权限.
- 可以将 具有相同结构 或 相同 功能 的类 或 接口 放在一起 , 便于查找.

包可以有多级结构 , 像 文件夹一样可以有多级 . 每一级之间采用 `.`( 英文句号) 进行分割 , 如果存在多级包结构 , 那么 对应的格式为 : `package 包1.包2.包3.包4` ,
例如 : java.lang.reflect ; 如果存在类名 , 可以通过 包 进行确定是哪一个类 , 例如 : `java.lang.reflect.Method` . 

import : 导入 ; 主要用来导入一些 不同 包下的类 或 接口 . 形式 : `import 包名.类名`  ,  `import 包名.接口名` . 
如果想要导入一个包下的所有类和接口, 则 需要使用 通配符`*` , 来表示所有. 但是 一般的情况,如果使用较少, 则 不会使用 通配符. 

import  是在 package 之后出现 , 可以有任意多个 import 语句, 但是只能有一个 package 语句. 

### 构造
构造 又称 构造方法、构造器 （Constructor） 、构造函数 。是一个类里用于创建对象的特殊子程序 。

构造就是为了 构建一个对象 而存在 。 并且 经常接收一些参数，用来为 实例变量（非static 修饰的 字段 ） 赋值 。

构造的语法是 ：
```text
【修饰符】 类名( 【形参】 ){ 方法体 ;}
```
如果一个构造方法 没有任何参数，此时可以被称为 无参构造 。

构造方法与普通方法之间的区别 ：
- 构造方法没有任何返回值 ， 也就是说没有返回类型； 普通方法存在 返回类型
- 构造方法的名称 是 类名 ， 如果不是类名就会变成一个普通的方法； 普通方法的方法名 是可以随便书写。

构造之间的重载 ：
- 所有的构造方法的名字都是 类名 。 —— 同名
- 构造方法中的 参数的 个数、类型、顺序 不同 。—— 不同参
- 对修饰符没有任何要求 。

默认构造 ： 当创建一个类的时候， 如果没有明确书写构造方法，则 JDK 会自动加上一个 public 修饰 无参构造 。
但是，如果一旦书写了任何一种构造方法，那么JDK 就不会再次提供 构造方法了。所以， 在添加构造方法的时候，一般 需要提供一个 public 修饰的无参构造。

构造方法本身没有任何返回 , 但是 new + 构造方法 有返回值 .


### 继承 
如果一个类别B“继承自”另一个类别A，就把这个B称为“A的子类”，而把A称为“B的父类别”也可以称“A是B的基类”。

继承可以让 子类 继承 父类中的 所有的 字段 和 方法. Java 中使用 `extends` 来表示继承关系 . 子类可以添加 父类中 没有的东西 .

在名义上可以继承 父类中的所有东西,但是 能不能访问 由 访问 权限 决定 . 

如果一个类 没有 指定 父类 ( 就是没有 extends 这个关键字 ) , 那么 该类的默认父类是 `java.lang.Object` ;  
`java.lang.Object` 类 , 是 所有的类 父类( 直接 或 间接 ) . 

在 Java 中 , 不支持 多继承 , 只有单继承 . 就表示 extends 后边只能存在一个类，只有一个直接父类。

子类 is-a 父类 ; 父类与子类之间的关系 是`is-a`

#### super 关键字 

this 表示我, super 表示 我爹 

super关键字是一个引用变量，用于引用直接父类对象 . super 关键字 可以代表 父类的一些东西 . 

用法 :
- 可以表示父类的 实例对象 . 表示父对象 . 注意:有可能是 直接父级对象 或 间接父级对象 . 
- super() ; 表示调用父类的构造方法 . 在 子类中的构造中使用 , 是放在 子类构造中的第一行! 如果没有书写,则JDK 会 隐式(偷偷摸摸的) 添加这一行 

### 重写( override )

重写( override )与重载( overload )的异同

重写 : 同名( 方法名相同 ) 同参( 参数相同 ) 同返回( 返回类型相同 ) 
- 发生在 继承 关系中 
- 方法名相同 , 参数 相同 ( 参数的个数 类型 顺序 )
- 返回类型 : 
  - 基本数据类型 : 完全一致 
  - 引用数据类型 : 保证 "同源"
- 修饰符 : 子类重写方法的修饰符 不能 比 父类方法中的修饰符 要小 ( 访问权限 )

### 多态
多态是**同一个行为**具有多个**不同表现形式或形态**的能力。

分类 :
- 运行时多态 : 发生的基础 是继承 . 同时 保证 子类重写了 父类 的 方法. 本质是 父类类型指向子类对象 ( 父类变量指向了 子类对象 )
- 编译时多态 : 是由 重载 决定的. 在 .java 文件 编译阶段 表现出 多种形态 . 实际上 是由 参数的多样化 决定的.

运行时类型与编译时类型 : 
- 运行时类型 :  Human h = new Chinese() ; 在 运行阶段, 引用的 是 Chinese 类型, 那么 运行时类型就是 Chinese . Human h = new Hindu() ; 在运行阶段 , 引用的是 Hindu 类型, 那么对应的运行时类型就是 Hindu.
- 编译时类型  :  Human h = new Chinese() ; 此时的 h 的类型是 Human, 这个类型就是编译时类型  Human h = new Hindu()  , 此时 h 的编译时类型时 Human .

#### instanceof 
含义 : xxx 是 YYY 吗 ? 返回值是 boolean 类型  ； 判断变量的运行时类型 

语法 : 
```text
变量 instanceof 类 
```
这个变量 是 这个类型吗 ? 返回boolean 值 

JDK17 的优化方案 : 参看 oop.polymorphism.runtime.TestRuntime.java 中的 45 ~ 47 行 .
```text
if( x instanceof Type  typeVar ) {
    typeVar.method ; 
}
```
x 是 一个要 判断的 变量 ; Type 是具体的类型, typeVar 就是 Type类型的变量 , method 是 Type 类中的方法. 

#### 引用强制类型转换的本质
```text
Hindu h3 = (Hindu) h2 ;
```
将栈中存放的对象的地址( h2 中存放的地址) 赋值给另外一个变量( h3 ) 并转换成目标类型（Hindu）


### static 关键字 
static 表示 静态的 ， 表示 类加载 初始化阶段  ， 可以修饰 变量、方法、代码块、内部类（内部接口） 。

static 修饰的内容 是通过  类名.变量（方法） 直接使用

- 修饰变量 ( 静态变量， 静态成员变量， 类初始化变量， 类初始化字段 ) ， 一般是 在 类中 直接使用， 表示 该变量 在 类 初始化的时候 就已经定义好了。
- 修饰方法 ( 静态方法 ) ， 一般是 在类中直接使用， 表示 该方法 在 类 初始化的时候 就已经定义好了。 
- 修饰代码块 ： 就可以表示 类已经被加载了， 类加载阶段就会执行的代码。

JDK1.5 引入了 static import ; 基本没有人用。 形式 ：`import static java.lang.Math.*;`  ， 那么在 使用的时候就可以不用书写类名了。

### 变量分类 
- 变量的作用域 ： 离变量最近的花括号{}。
- 成员变量
  - static修饰 （静态成员变量 、类初始化成员变量 ） ： 加载到 方法区中， 伴随着 类的存在而存在。
  - 实例变量（没有使用 static 修饰的变量 ） ： 随 对象存在而存在， 加载到 堆空间 中 ， 作用范围是 整个类体括号 
- 局部变量
  - 在 方法中 或  代码块中定义 ; 作用范围在声明的范围之内 ； 加载到栈中 

### 代码块
代码块与 变量的分类是差不多的。

语法：
```text
[修饰符] { 
此时就是一个代码块了！
}
```

- 成员代码块
  - 静态代码块（类初始化代码块） 使用 static 修饰的代码块 
  - 实例代码块 ： 没有使用 static 进行修饰的代码块 
- 局部代码块 ： 在 方法内部 直接 声明的代码块  ， 很少使用。

执行顺序
- 一个类中存在 代码块、static代码块、构造方法 的时候， 先 static 代码块， 再 代码块， 最后是构造。
- 一个类中存在 多个 代码块 ， 多个 static代码块 、 构造的时候， 先 static 代码块， 再 代码块， 最后是构造。 
  - 多个代码块的时候，会按照 书写顺序（从上到下）执行 
  - 当 static 代码块 执行的时候，就可以认为 这个类 已经被加载到 jvm 中了。
- 存在继承的时候，代码块 、 static 代码块 、 构造的执行顺序  ; Father 是 Son 的父类 。
  - Father#static_code_statement
  - Son#static_code_statement
  - Father#instance_code_statement
  - Father#constructor
  - Son#instance_code_statement
  - Son#constructor

### final 
final ： 最后的， 最终的 ， 不可改变的 。

final 可以修饰 变量 、 非抽象方法、 非抽象类 。
- final 修饰的变量 不可改变
- final 修饰的方法 不可 重写 
- final 修饰的类 不可 继承 

sb面试题 ： final 、finally 、 finalize() 有什么异同 ：
- 相同点 ：长得像；
- 不同点 ： 是不同的功能，final 关键字，finally 异常抛出时最后执行的语句，finalize（） 方法。

### 密封类 
参看： oop.seal 

JDK16 是预览版本， JDK17是确定的版本。 新的语法特性，有待于后期观察 和 后期应用 （可能三年后）。

继承中， 需要明确， 可以随意的继承 。 为了限制肆无忌惮的继承， 产生了密封类。 特点：指定几个类可以继承。

语法：
```text
sealed class 类名 permits 子类 {}
```
注意： 子类 有如下选择 ：
- final 修饰的类
  ```text
    final 子类 类名 extends 父类{}
  ```
  表示 该子类不会再有子类
- 子类声明自己是 密封类（加上 sealed ） ， 随后直接继承父类， 并声明子类
  ```text
  sealed class 类名 extends 父类 permits 子类 {}
  ```
  这种方案需要注意 ， 此时依旧没有打破 密封类的 标志 
- 子类声明自己不再是一个密封类了( 加上 non-sealed )
  ```text
   non-sealed class 类名 extends 父类
  ```
### 抽象类
出现抽象类的原因：
1. 不能够明确 一个类 中的具体设计 是什么样的， 可以考虑设置成抽象类
2. 不能明确一个方法 应该如何实现（方法没有方法体） ，那么 该方法可以变成 抽象方法 ， 那么拥有抽象方法的类必须是抽象类。
3. 一个类 不想被实例化（不想有对象） 就可以设置成抽象类。

关键字 ： abstract ； 与 final 关键字冲突

声明一个抽象类 ：
```text
abstract class 类名{}
```
抽象类 仅仅不可以实例化，其他的内容与其他的类没有任何区别。

抽象类中存在构造， 仅供 子类调用。

抽象类中可以有抽象方法，也可以有非抽象方法 。

抽象方法的定义：
```text
[修饰符] abstract 返回类型 方法名() ; 
```

通过创建 子类的实例 来获取 抽象类的实例 。（本质）

常用的获取 抽象类实例的 方式 ：
- 抽象类中的 static 修饰的方法
- 抽象类子类中的 static 修饰的方法
- new 抽象类的子类 
- 自己实现 抽象类，并实现其中的所有方法， 随后创建实例。

### 抽象类的应用
Number 以及 包装类 

Number 是 数值类型 的 父类 。数值类型指代的是 数值基本数据 对应的Java 引用数据 类型。

Number的方法：
- xxxValue() ; 可以将 Number 中的数值 转成 对应的XXX 类型； xxx 类型是 ： byte、short、int、long、float、double

#### 包装类 
基本数据类型都有其对应的引用数据类型，而这些引用数据类型被称为 包装类（Wrapper Class）。

对应的包装类如下：
- byte : Byte
- short ： Short
- int : Integer
- long : Long
- float : Float
- double : Double
- char : Character
- boolean : Boolean 

自动装箱与拆箱 ：
- 自动装箱 ： 基本数据类型可以自动封装成对应的引用数据类型 , 没有自动装箱的时候，需要通过 valueOf 方法进行包装。但是 本质还是通过 valueOf 来进行包装的（JVM来完成的）
- 自动拆箱 ： 基本数据类型的引用类型可以自动变成其对应的基本数据类型 

##### 通用方法
- valueOf ： 该方法是 一个 static 修饰的方法 ， 该方法可以将 对应的基本数据类型 和 字符串 包装成对应的 引用数据类型 （ 参看 oop.wrapper.TestWrapper02.java 和 oop.wrapper.TestWrapper03.java）
- toString ： 将一个 引用数据类型转成一个 字符串 
- parseXXXX ： 除了Character 都存在该方法， 该方法可以将 字符串转换成 对应的基本数据类型 

##### 常用方法
Byte 、 Short 、 Integer、Long 其中内部都有缓存（内部已经定义好的数组并已经赋值了） ，具体缓存数值为： -128 ~ 127 之间。

Integer 是可以通过 配置文件 来修改默认的 缓存 范围的。
###### Byte
Byte 是 byte 对应的包装类 。常用字段 ：
- MAX_VALUE ： 最大值
- MIN_VALUE : 最小值
- TYPE ： 获取其 Class 类型 

Byte 中的常用方法：
- compare(byte x, byte y) ： 比较大小 ； 
- compareTo( Byte another ) : 内部调用了 compare()
- XXXValue ： 其实就是Byte 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。

###### Short 
Short 是 short 对应的包装类， 常用字段：
- MAX_VALUE ： 最大值
- MIN_VALUE : 最小值
- TYPE ： 获取其 Class 类型 

Short 中的常用方法：
- compare(short x, short y) ： 比较大小 ；
- compareTo( Short another ) : 内部调用了 compare()
- XXXValue ： 其实就是Short 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。

###### Integer
Integer 是 int 对应的包装类， 常用字段：
- MAX_VALUE ： 最大值
- MIN_VALUE : 最小值
- TYPE ： 获取其 Class 类型

Integer 中的常用方法：
- compare(int x, int y) ： (x < y) ? -1 : ((x == y) ? 0 : 1);
- compareTo( Integer another ) : 内部调用了 compare 方法
- XXXValue ： 其实就是Integer 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。
- max 、min( a , b )  : 这两个都是 Integer 内部的方法， 主要是获取 最大值 或 最小值
- sum( a , b ) : 求和 
- toBinaryString() 、toHexString 、 toOctalString ： 返回 二进制 、 十六进制 、 八进制 的 字符串 

###### Long
Long 是 long 对应的包装类， 常用字段：
- MAX_VALUE ： 最大值
- MIN_VALUE : 最小值
- TYPE ： 获取其 Class 类型

Long 中的常用方法：
- compare(short x, short y) ： (x < y) ? -1 : ((x == y) ? 0 : 1); 
- compareTo( Long another ) : 内部调用了 compare 方法
- XXXValue ： 其实就是 Long 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。
- max 、min( a , b )  : 这两个都是 Integer 内部的方法， 主要是获取 最大值 或 最小值
- sum( a , b ) : 求和
- toBinaryString() 、toHexString 、 toOctalString ： 返回 二进制 、 十六进制 、 八进制 的 字符串 

###### Float
Float 是 float 对应的包装类， 常用字段：
- MAX_EXPONENT ： 最大 的 指数
- MIN_EXPONENT ： 最小 的指数
- MAX_VALUE ： 最大值
- MIN_VALUE ： 最小值
- NaN ： 非数字值  0.0f/0.0f
- NEGATIVE_INFINITY ： 负无穷  -1.0f/ 0.0f 
- POSITIVE_INFINITY ： 正无穷  1.0f / 0.0f 
- TYPE ： 获取其 Class 类型

常用方法：
- compare(float x, float y) ： 正常使用 判断即可， 但是 有可能传入 Float 类型的 特殊值（NaN ， NEGATIVE_INFINITY ， POSITIVE_INFINITY） ， 所以判断更加复杂
- compareTo( Float another ) : 内部调用了 compare 方法
- XXXValue ： 其实就是 Float 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。
- max 、min( a , b )  : 这两个都是 Integer 内部的方法， 主要是获取 最大值 或 最小值
- sum( a , b ) : 求和
- toHexString : 将 float 的值变成一个 16 进制的 字符串
- isFinite(float f) ： 是否是有限
- isInfinite() ： 是否是无限的对象 ： floatObject.isInfinite
- isInfinite(float v) : 是否是无限的数值
- isNaN() ： 是否 不是一个数字  . 如果对象是 NaN ， 则返回true
- isNaN(float v) ： 是否 不是一个数字 

###### Double
Double 是 double 对应的包装类， 常用字段：
- MAX_EXPONENT ： 最大 的 指数
- MIN_EXPONENT ： 最小 的指数
- MAX_VALUE ： 最大值
- MIN_VALUE ： 最小值
- NaN ： 非数字值  0.0f/0.0f
- NEGATIVE_INFINITY ： 负无穷  -1.0f/ 0.0f
- POSITIVE_INFINITY ： 正无穷  1.0f / 0.0f
- TYPE ： 获取其 Class 类型

常用方法：
- compare(double x, double y) ： 正常使用 判断即可， 但是 有可能传入 Double 类型的 特殊值（NaN ， NEGATIVE_INFINITY ， POSITIVE_INFINITY） ， 所以判断更加复杂
- compareTo( Double another ) : 内部调用了 compare 方法
- XXXValue ： 其实就是 Double 内部有一个 字段：value ， 每次调用 XXXValue 的时候，其实就是返回 value ， 有时可能对其进行操作。
- max 、min( a , b )  : 这两个都是 Integer 内部的方法， 主要是获取 最大值 或 最小值
- sum( a , b ) : 求和
- toHexString : 将 float 的值变成一个 16 进制的 字符串
- isFinite(f) ： 是否是有限
- isInfinite() ： 是否是无限的对象 ： floatObject.isInfinite
- isInfinite(v) : 是否是无限的数值
- isNaN() ： 是否 不是一个数字  . 如果对象是 NaN ， 则返回true
- isNaN(v) ： 是否 不是一个数字 

###### Boolean
Boolean 是 boolean 对应的包装类， 常用字段：
- TYPE ： 获取其 Class 类型
- TRUE : ture
- FALSE :  false 


常用方法：
- booleanValue() ： 从 Boolean 中 获取到 boolean 的值 , 其中依旧有一个 value 字段来存放
- compare( boolean x , boolean y ) : (x == y) ? 0 : (x ? 1 : -1);
- compareTo( Boolean another ) : 调用了 compare 

###### Character 
代码点( code point ) : Unicode是属于编码字符集（CCS）的范围。Unicode所做的事情就是将我们需要表示的字符表中的每个字符映射成一个数字，这个数字被称为相应字符的码点（code point）

常用方法：
- compareTo(Character anotherCharacter)  调用 compare 方法
- compare(char x, char y) : x - y; 
- isLowerCase(char ch) ： 是否是小写
- isUpperCase(char ch) ： 是否是 大写
- isWhitespace(char ch) ： 是否是空格
- toLowerCase(char ch) ： 变小写
- toUpperCase(char ch) ： 变大写

#### 数字格式化 NumberFormat
数字格式化的目的统一数字格式， 以便于后续的使用。

NumberFormat is the abstract base class for all number formats.  NumberFormat 是所有数字格式化的抽象基类。

##### 获取实例
1. 使用 NumberFormat 中的 static 修饰的方法 （ 可以使用， 但是局限性比较大 ）
   1. getCompactNumberInstance() ： 返回具有“SHORT”格式样式的默认 FORMAT 语言环境的紧凑数字格式
   
2. 使用  NumberFormat 的子类中的 static 修饰的方法

3. 创建 NumberFormat 的子类对象 。
   1. 创建 DecimalFormat 子类对象来进行格式化或解析  ， 更为简单的操作。
   
   2. ```text
      		//将数字格式化为保留小数点后四位数字，并且DOWN表示舍去第五位以后的数字没有四舍五入。
         		RoundingMode roundingMode = RoundingMode.DOWN;
         		NumberFormat numberFormat = new DecimalFormat("#.0000");
         		//设置DOWN或者UP...
         		numberFormat.setRoundingMode(RoundingMode.DOWN);
              double dd = 3.1415926;
              //将数组格式化以String形式接受
              String su = numberFormat.format(dd);
              //将String类型解析为Number类型接收
              Number df =  numberFormat.parse(su);
      ```

##### 格式化与解析
格式化（format） ： 将 指定的数字 转换成 具有特定格式的 字符串 

解析 （parse）： 将 特定格式的字符串 解析成 具体的数字 

### 内部类
概念 ： 写在 一个类 或 一个方法 内部 的 类 就可以被称为叫 内部类（嵌套类【Nested Classes】） 。

划分：
- 成员内部类
  - static 修饰的内部类 （类初始化内部类、静态内部类） 创建方式： new 外部类.内部类() ; 
  - 没有static 修饰的内部类 （实例内部类、对象内部类） 创建方法 ： 外部类的对象.new 内部类() ;
- 局部内部类 （ 也就是 匿名内部类在用 ， 其他的情况基本不用  ）
  - 在 方法中进行声明的内部类 
  - 特殊形式 ： 匿名内部类 

注意：
- 所有的内部类都有对应的 .class 文件 
- 对于静态内部类、实例内部类来说，他们对应的 .class 的名称是：  外部类类名$内部类类名.class
- 局部内部类（有名称的）来说：他们对应的 .class 文件名称是：外部类类名$Number 内部类类名.class
  - 其中的 Number 是 使用 该名称 的 内部类 在 外部类 出现的位置（第几个） 
- 有一个局部内部类，它连名字都没有，则它就是匿名内部类，但是它有对应的.class 文件 ； 匿名内部类对应的.class 文件名 是外部类类名$数字.class
  - 常用的场景 ：
    - 用匿名内部类实现接口
    - 用匿名内部类继承抽象类 
    - 用匿名内部类继承普通的类

#### Lambda 
本质上就是一个 匿名内部类 。而作为一个新的语法糖来实现。

Lambda表达式：在Java8中引入一种新的语法元素和操作符。这个操作符是：`->` ，该操作符被称为`Lambda操作符`或`箭头操作符`。它将Lambda分为两部分：
- 左侧：指定了Lambda表达式需要的参数列表 （其实就是抽象方法的形参列表）
- 右侧：指定了Lambda体，是抽象方法的实际逻辑，也就是Lambda表达式要执行的功能。（其实就是重写抽象方法的方法体）
### 单例模式
概念：在当前环境中只存在一个实例，一个类仅在类中声明一个类实例，该类不能在外部创建实例对象，该类语法与基本的类一致。

注意：
- 构造方法必须是私有的；
- 在类中构造有且仅有一个的实例；
- 为该实例提供public方法以供外部方法获取该实例；

### 多例

类中含有有限多个实例，其他与单例基本一致。

例如 : 月份，季节，星期...

### 枚举

基本语法：【修饰符】 enum 名字{}

Java 枚举是一个特殊的类，一般表示一组常量 ， 常量之间使用 , 进行分割。

类中可以有 字段 和 方法 ； 可以实现接口（可以实现一个或多个接口）。

枚举类 ： 默认继承 java.lang.Enum 类 （不允许 显式声明 ） ， 存在构造（但是私有）

总结：
- jdk 1.5之后可以使用enum来定义“多例”。

- 枚举的构造器默认私有。

- 枚举类的第一行代码 ， 用来缓存那几个有限的实例。

- 声明实例直接写名字， 多个实例之间用逗号分隔开来。

- 所有的枚举实例， 默认修饰符是 public static final 修饰的。

- 枚举中可以有属性、方法、带参数的构造方法【可以重载】;当提供了带参数的构造后，如果没有写无参构造，则声明枚举常量时，需要传入参数，比如MONDAY("星期一")；相当于： public static final Day MONDAY = new Day("星期一"); 

- 所有的枚举类都继承了java.lang.Enum，所以，所有的枚举类都有Enum的方法( toString 、 name方法、 索引[ordinal]),但是不能主动写 extends Enum，并且枚举类没有后代

- 声明一个public enum Season{}的枚举类 ， 创建枚举实例 Sping ，其实相当于书写 Season Spring = new Season（）；

### java.lang.Enum

该类是所有枚举的父类。

### 记录（record）

语法：
```text
[修饰符] record 记录名（类型1 变量1， 类型2 变量2 ...）{}
```



在使用的时候， 与一个类没有特别大的区别。

记录中，存在重写 toString 方法、hashCode方法、equals 方法 ； 所有的记录的 父类是 java.lang.Record ; 记录没有extends 语句。

记录名后边的 括号中的 字段 是 使用 final 修饰的。

- 记录会在类里自动生成一个有参构造； 这个构造就是记录中的规范构造。


### 常用类




#### java.lang.Object

官方声明：

```
Class Object is the root of the class hierarchy.  ( Object类是 类继承体系中的根 )
Every class has Object as a superclass. （ 每一个类都把 Object 类作为 父类【可能是直接父类、间接父类】）
All objects, including arrays, implement the methods of this class.（所有的对象，包括数组，都实现了该类中的方法）
```

常用方法：

- equals(Object obj) ： 判断 两个对象是否”相等“ ; 如果要重写 equals 方法，那么需要注意以下内容：
  - equals 方法在非空对象引用上实现相等关系 ：
    - 自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。
    - 对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true
    - 传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
    - 一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。
    - 对于任何非空引用值 x，x.equals(null) 都应返回 false。
  - 当此方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。
  - 如果不重写hashCode 方法， 可能造成以下问题：
    - 可能浪费空间
    - 如果要存储的话， 恰好是使用 hash值作为一个 具体的坐标的话，那么就 存在两个对象 （对象冲突）
- hashCode() ： 获取到一个对象的 hashCode 值
  - hashCode 常规协定：
    1. 在 java 运行期间， 对同一个对象的多次调用 hashCode 方法， 应该返回同一个整数 。（前提是 equals 的判定条件没有修改）
    2. 如果 equals 方法 返回了 true ， 那么 两个对象 应该调用 hashCode 方法时，返回的结果也一样。
    3. 如果 equals 方法 返回了 false ，那么 两个对象 调用 hashCode 方法的时候 ， 可能产生的结果一样 。
- toString() ： 返回对象的字符串表示形式 , 在其他的类中，经常会被重写
- getClass() ：获取对象运行时的类型 ， 获取到的 是 java.lang.Class 类型的对象， 该对象是 反射的入口



##### java.util.Objects

该类中提供了大量的static 修饰的方法， 这些方法可以用于对象上。

常用方法如下：

- toString( Object o ) ： 将任意一个对象的toString形式返回， 如果传入的是null ， 则返回null字符串。
- toString(Object o, String nullDefault) : 将任意一个对象的toString形式返回， 如果传入的是null ， 则返回 nullDefault 字符串
- hashCode(Object o) : 获取 对象的hashCode 值， 如果 o 是null ， 则返回0
- hash(Object... values) ： 计算出一组 数据的 hashCode
- equals(Object a, Object b) ： 判断两个对象是否相等
- deepEquals(Object a, Object b) ： 判断两个对象是否是“深度”相等
  - 内部其实调用的是 ： Arrays.deepEquals0(a, b);
  - 如果是数组， 那么就按照 数组的处理方式进行处理 ： Arrays.equals（基本数据类型的数据） 或 Arrays.deepEquals （Object 类型的数组）
  - 如果是单一的对象 则 a.equals( b )
- isNull( Object o ) : 判断 o 是否为 null ， 如果返回是 true ，则 表示 o 为 null
- nonNull(Object obj) : 判断 o 是否为 null ， 如果返回是 true , 则表示  o 不为 null
- requireNonNull(T obj) : 严格要求 T 不能是 null ， 如果为null ， 则 抛出异常 ， 这里没有异常信息
- requireNonNull(T obj, String message) ： 要求  T 不能是 null ， 如果是null 则 抛出异常， message 是异常信息
