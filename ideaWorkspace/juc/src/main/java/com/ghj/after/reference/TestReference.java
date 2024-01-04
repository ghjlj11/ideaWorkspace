package com.ghj.after.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/23 23:25
 */
public class TestReference {
    public static void main(String[] args) {
        phantomReference();
    }

    /**
     * 测试强引用，在对象指定为null后，gc回收
     */
    public static void strongReference () {
        MyObject myObject = new MyObject();
        System.out.println("gc before:" + myObject);
        myObject = null;
        System.gc();
        System.out.println("gc after:" + myObject);
    }

    /**
     * 测试软引用，在内存不足情况gc回收对象
     */
    public static void softReference () {
            SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("gc before:" + softReference.get());
        try {
            byte[] bytes = new byte[15 * 1024 * 1024];
        } finally {
            System.out.println("gc after:" + softReference.get());
        }
    }

    /**
     * 测试弱引用，弱引用的对象将在下一次gc回收时候被回收
     */
    public static void weakReference () {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before:" + weakReference.get());
        System.gc();
        System.out.println("gc after:" + weakReference.get());
    }

    /**
     * 有概率测试成功打印   虚引用队列被添加信息
     * 被虚引用的对象调用get方法时总是返回null，虚引用不决定对象的生命周期，虚引用的对象被回收以后会进入到ReferenceQueue队列中
     */
    public static void phantomReference () {
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(), referenceQueue);
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while (true) {
                list.add(new byte[1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + "    add success :");
            }
        }, "t1").start();

        new Thread( () -> {
            while (true) {
                Reference<? extends MyObject> poll = referenceQueue.poll();
                if (poll != null) {
                    System.out.println("虚引用队列被添加信息--->");
                    break;
                }
            }
        }, "t2").start();
    }
}
