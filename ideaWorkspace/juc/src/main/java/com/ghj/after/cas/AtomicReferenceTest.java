package com.ghj.after.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * Description: 测试AtomicReference，可以保证里面的值的原子性，但是不能保证值里面的属性原子性
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/25 22:33
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User ghj = new User("ghj", 23);
        User lj = new User("lj", 22);
        atomicReference.set(ghj);
        // 预期值为ghj，更新值为lj
        System.out.println(atomicReference.compareAndSet(ghj, lj) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(ghj, lj) + "\t" + atomicReference.get().toString());

        for (int i = 0; i < 10; i++) {
            new Thread( () -> {
                for (int j = 0; j < 100; j++) {
                    lj.setAge(lj.getAge() + 1);
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(atomicReference.get());
    }
}
