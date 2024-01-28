package com.ghj.after.javaobject;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.Date;

/**
 * <p>
 * Description: 测试Jol工具，用于展示对象内存布局的工具
 * <p>
 * 一个对象在jvm中由对象头、实例数据、对齐填充组成
 * 对象头：   包含了 对象标记（Mark Word）8字节 ，类元信息（8字节，压缩指针后4字节）
 *             对象标记：包含 哈希码、GC标记、GC次数（1字节）、同步锁标记、偏向锁持有者
 *             类元信息：包含该对象所属类信息
 *
 * 实例数据： 对象所有字段占用空间的总和，与方法无关，如果字段是基本数据类型，大小就是基本数据类型占用字节的大小，如果字段是对象类型就是4字节。
 *
 * 对齐填充： java对象在jvm中都是占用8的整数倍字节的空间，如果以上不足8整数倍，就会添加对齐填充，直到有8的整数倍。
 *
 * @author guohuanjun1
 * @date 2024/1/14 21:39
 */
public class JolTest {
    public static void main(String[] args) {
        // 查看当前jvm信息
        System.out.println(VM.current().details());
        // 查看对象头填充
        System.out.println(VM.current().objectAlignment());
        // 占用16字节
        Object o = new Object();
        TestPojo testPojo = new TestPojo();
        // 展示对象内存占用
        System.out.println(ClassLayout.parseInstance(testPojo).toPrintable());
    }


    static class TestPojo {
        Integer a;
        boolean f = false;
        double d;
        long l;
        String s = new String("dgdsdfhsfgjhjgkfhjkliloyolyofstgtsar");
        Date date = new Date();

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
