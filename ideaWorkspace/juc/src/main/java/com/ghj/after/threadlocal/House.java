package com.ghj.after.threadlocal;

/**
 * <p>
 * Description: 测试ThreadLocal，一个对象中有一个ThreadLocal对象时，每个线程调用改对象时，都会有一个线程私有的ThreadLocal，不会被其他线程修改、获取
 *
 * 线程使用ThreadLocal后，应该调用remove()方法销毁ThreadLocal对象，否则在线程池场景下，线程会复用，当前业务结束了，
 * 下一个业务开始时ThreadLocal还是上一个业务结束时候的值
 *
 * ThreadLocalMap： 每个Thread里面都会有ThreadLocalMap对象，里面就是用来存储ThreadLocal的set方法里的值，
 * 存储方式也是k，v键值对，k为ThreadLocal对象，v为ThreadLocal的set方法的参数，get的时候，会以当前ThreadLocal为key去寻找value。
 * <p>
 * @author guohuanjun1
 * @date 2023/10/22 20:41
 */
public class House {
    public ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(threadLocal.get() + 1);
    }
}
