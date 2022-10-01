# RabbitMQ-study



## 介绍

功能介绍

- 流量消峰

当用户访问时候， 就会进入一个消息队列， 从而避免当访问人数过多时候服务器宕机， 但是与此同时的缺点就是，当访问的人多的时候， 那么就会变卡， 因为都在队列里面排着队， 会给用户带来不好的体验。

- 应用解耦

<img src="img/rabbit_mq/mq1.png">

订单系统的完成需要支付系统、库存系统、物流系统三个系统的支持。

正常情况下： 如果说其中一个系统出故障， 那么就会导致订单系统不能正常执行。

使用MQ后 ：订单系统接收到订单后只须把请求给队列， 至此订单系统就完成了任务， 再由队列给请求到对应的系统， 如果其中一个系统故障， 那么队列会一直监督他，直到正常执行后。



- 异步处理

当A需要调用B时候， B处理需要一段时间， 此时A需要知道B是否执行完，以及执行完的信息， 就需要A提供一个接口， B执行完之后就调用该接口通知A， 或者A每隔一段时间就去查询B的结果， B也需要提供一个查询接口， 这样就很不优雅。 我们使用了MQ之后， A调用完B就直接执行后面的流程， 等B执行完， 就把结果放入MQ， MQ再把结果给到A。



> 四大核心概念

- 生产者
- 交换机
- 队列
- 消费者



## 安装

下载erlang语言环境， 然后下载安装socat， 最后安装rabbitmq，推荐使用docker。

<img src="img\rabbit_mq\mq2.png">



- 开启前要配置hosts文件 ：`vim /etc/hosts` ，加上主机ip，以及hostname，hostname可以用命令`hostname`查询例如

  ```bash
  43.142.32.254 ghj-study
  ```

  并且开放对应的端口 ：

  ```bash
  4369   5672   5671   15672   15671   61613   61614   1883   8883
  ```

  

- 启动完成之后进入后台管理页面， 就会有一个登录页面



## java开发



> 依赖

```java
    <dependencies>
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.16.0</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>
    </dependencies>
```



> 生产者代码

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


/**
 * @author 86187
 */
public class Producer {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //创建连接工厂，设置参数
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.142.32.254");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //创建链接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * 生成一个队列
         * 1 队列名称
         * 2 队列的消息是否需要持久化 ， 默认消息存在内存， 非持久化
         * 3 该队列是否只供一个消费者 ，是否消息共享 ， true 多个消费者
         * 4 是否自动删除
         * 5 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "hello rabbitmq";

        /**
         * 发送一个消费
         * 1 发送到哪个交换机
         * 2 路由的key是哪个  本次队列名称
         * 3 其他参数
         * 4 发送的消息体
         */

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println("消息发送完成");
    }
}
```



运行之后，在之前的后台管理页面就可以看到有一个消息发送过来了



> 消费者



```java
import com.rabbitmq.client.*;


/**
 * @author 86187
 */
public class Consumer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建连接工厂，设置参数
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.142.32.254");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //创建链接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * 消费者消费消息
         * 1 队列的名称， 消费哪个队列
         * 2 消费成功之后是否需要自动答应， false代表手动 ， true代表自动
         * 3 消费者消费成功的回调函数
         * 4 消费者消费失败的回调函数
         */

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            //message.getBody()获取的是消息体的字节数组
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消费者消费失败");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        
    }
}

```



运行完之后就会接收到生产者发送的消息





## WorkQueue



当有多个消费者时候， 队列里的消息会遵循轮询分配， 即一人一个， 并且一个消息只会被一个消费者处理一次。



- 首先提取出 获取 信道 channel 的方法

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



/**
 * @author 86187
 * rabbitmq获取channel
 */
public class RabbitmqConnection {
    public static final String QUEUE_NAME = "hello";
    public static Channel getChannel() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.142.32.254");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
```



- 然后写一个生产者， 使用 Scanner扫描控制台输入的信息从而 发出消息

```java
import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author 86187
 * 工作队列的生产者
 */
public class WorkProducer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        channel.queueDeclare(RabbitmqConnection.QUEUE_NAME, false, false, false, null);

        //持续扫描传入队列消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", RabbitmqConnection.QUEUE_NAME, null, message.getBytes());
        }
    }
}

```



- 最后写一个消费者类， 利用多线程开启多个消费者

```java
import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.*;

/**
 * @author 86187
 */
public class WorkConsumers {
    public static void main(String[] args) {
        Consumers consumers1 = new Consumers();
        Consumers consumers2 = new Consumers();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPool.execute(consumers1);
        threadPool.execute(consumers2);
    }

    static class Consumers implements Runnable {
        @Override
        public void run() {
            try {
                Channel channel = RabbitmqConnection.getChannel();
                DeliverCallback deliverCallback = (consumerTag, message) -> {
                    System.out.println(Thread.currentThread().getName() + "收到消息：" + new String(message.getBody()));
                };
                CancelCallback cancelCallback = (consumerTag) -> {
                    System.out.println(Thread.currentThread().getName() + "接收消息失败!");
                };
                channel.basicConsume(RabbitmqConnection.QUEUE_NAME, true, deliverCallback, cancelCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

```



- 最后测试生产者持续发消息， 消费者都是轮询接收消息

```txt
生产者发出的消息:
aa
ss
dd
ff
gg
hh
jj

消费者接收的消息:
pool-3-thread-4收到消息：aa
pool-2-thread-4收到消息：ss
pool-3-thread-5收到消息：dd
pool-2-thread-5收到消息：ff
pool-3-thread-6收到消息：gg
pool-2-thread-6收到消息：hh
pool-3-thread-7收到消息：jj
```



这里的名字会不一样可能是因为在消息队列里面也是使用了多线程， 从而我们设置的名字没有输出，因为并不是我们这条线程去执行下面的run()方法里的东西，但是前缀是一样的。



## 消息应答



在消费者信道执行消费消息的方法上有一个参数是用来 选择是否自动应答， 应答就是当消息发送给消费者之后， 那么经过某些处理， 会应答给队列， 队列收到应答后就会把消息删除， 如果消费者没有应答， 消费者宕机了， 此时队列并没有收到应答， 就不会删除消息， 并且会发送给可以处理此消息的消费者。

> 消息丢失

 当消费者没有完成指定的任务， 就应答了， 并且此时消费者因为某种原因不能继续执行了， 队列依旧会产出消息， 那么就会出现消息的丢失， 我们必须避免这种情况。 使用自动应答，其实是消息一旦被消费者接收那么就会应答， 此时并没有完成任务， 因此， 我们要避免使用自动应答。



> 消息应答的方法

有三种应答方式， Channel.basicAck()确认应答，Channel.basicNack()否定应答， Channel.basicReject()否定应答，前两个方法有批量应答功能， 第二个参数改成true就代表批量应答， 会把当前消费者收到的未应答的消息都应答，但是尽量不适用批量应答。



> 测试

- 生产者

```java
import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 86187
 */
public class ResProducer {
    private static final String QUEUE_NAME = "ack-queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);
        System.out.println("生产者发送消息:");
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, next.getBytes(StandardCharsets.UTF_8));
        }
    }
}

```



- 消费者

```java
public class ResConsumers {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                3,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        ResConsumer consumer1 = new ResConsumer(1);
        ResConsumer consumer2 = new ResConsumer(30);

        executor.execute(consumer2);
        //executor.execute(consumer2);
    }
}

class ResConsumer implements Runnable {
    private static final String QUEUE_NAME = "ack-queue";
    private int sleepTime;
    public ResConsumer(int sleepTime){
        this.sleepTime = sleepTime;
    }
    @Override
    public void run() {
        try {
            Channel channel = RabbitmqConnection.getChannel();
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    Thread.currentThread().setName(sleepTime + "时间");
                    TimeUnit.SECONDS.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + "收到消息:" + new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println(Thread.currentThread().getName() + "接收失败回调");
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



用消费者开启多个实例， idea开启多个实例支持， 然后第一个实例使用consumer1，第二个使用consumer2，这样就有一个时间差， 然后测试， 轮询分配消息， 等consumer2在sleep的时候关闭实例， 此时确认应答没有发出，消息会被分配到consumer1上面。
