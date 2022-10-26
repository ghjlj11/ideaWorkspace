# Arthas-study

用于实时检测java项目的工具，可以查看慢请求， 请求相关的类， 以及方法的入参， 返回值，以及异常 



## 下载与安装

```bash
curl -O https://arthas.aliyun.com/arthas-boot.jar
```



> 启动

在arthas的安装目录下执行

```bash
java -jar arthas-boot.jar
```

注意 ： 启动后必须要有正在运行的java进程， 否则会启动失败。

> 卸载

```bash
rm -rf ~/.arthas/
rm -rf ~/logs/arthas
```



## 基本命令



```bash
# 显示当前会话信息， 进程id等
session 
# 查看版本号 
version 
# 退出当前客户端， 不影响其他会话
quit
exit
# 退出arthas服务
stop
# 获取快捷键
keymap
# 反编译类
jad demo.MathGame(包名.类名)
```





> 基本术语

```bash
           target : the object                                                                                                                     
            clazz : the object's class                                                                                                             
           method : the constructor or method                                                                                                      
           params : the parameters array of method                                                                                                 
     params[0..n] : the element of parameters array                                                                                                
        returnObj : the returned object of method                                                                                                  
         throwExp : the throw exception of method                                                                                                  
         isReturn : the method ended by return                                                                                                     
          isThrow : the method ended by throwing exception                                                                                         
            #cost : the execution time in ms of method invocation 
```



## watch



> 观测点

watch会有四个观测时间， 分别是`-b`：方法调用之前， `-e`方法抛出异常之后，`-s`方法返回之后，`-f`方法结束后，默认打开的是`-f`。



> 方法入参，方法出参

方法入参：参数进入方法之前的值。

方法出参：参数执行完方法之后的值。 



> 参数

当使用`-b`方法调用 ，前返回值为空，该方法也不会抛出异常。参数`params`表示的是方法入参， 其余的时候`params`都是表示方法出参。



`-x`表示输出值的属性遍历的深度（默认是1级），`-n`表示只执行该方法几次， `-E`表示开启正则表达式匹配

基本使用：

```bash
watch [观测时间点参数(该参数也可以写在末尾)]  class-pattern(类名表达式)  method-pattern(方法名表达式)   [express(观察对象表达式)]   [condition-express(条件)]   [参数（-x, -n, -b, -s等）]


例如：
watch -b com.ghj.controller.MyController test01 "{params, returnObj}" "true" -x 4
```



当不写观察对象表达式时， 默认是 `params` , `target`, `returnObj`, 不写条件表达式时默认是 `true`。



## trace

使用trace追踪方法，可以查看方法的执行时间，方法里调用的别的方法的信息， 属于哪个线程， 是否是守护线程，类加载器等等

使用`trace -h`查看帮助文档



> 参数

`-n`，`-E`同watch， `--skipJDKMethod`是否跳过检查JDK的方法， 默认为false， watch同样有该参数

```bash
基本使用：
trace [参数]  类名表达式  方法名表达式  [条件]  [参数]

例如：
trace --skipJDKMethod false com.ghj.controller.MyController test01 '#cost>0'
```



## stack

查看方法调用栈，即一直往上查看是谁调用该方法， 以及查看方法的被哪个线程调用，线程是否是守护线程，优先级，类加载器等

`stack -h`帮助文档



> 参数

`-n`同上，



```bash
# 基本使用
stack [参数] 类名表达式  方法名表达式  [条件表达式]  [参数]

# 示例
stack demo.MathGame primeFactors 'params[0]<0' -n 3
```





