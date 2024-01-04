package com.ghj.after.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <p>
 * Description: 账户类，测试AtomicIntegerFieldUpdater
 * 可以将对象的某一个属性设置为原子类，在并发环境下安全，效果类似AtomicInteger，但是只能作用于volatile修饰的int类型，其他不行（包括Integer）
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/4 22:52
 */
@Data
@NoArgsConstructor
public class BankAccount {
    private String name;
    private String address;
    private volatile int money;

    AtomicIntegerFieldUpdater<BankAccount> atomicIntegerFieldUpdate = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public void increment (BankAccount bankAccount) {
        atomicIntegerFieldUpdate.incrementAndGet(bankAccount);
    }
}
