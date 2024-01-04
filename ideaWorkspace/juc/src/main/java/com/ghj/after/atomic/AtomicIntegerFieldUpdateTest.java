package com.ghj.after.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Description: 测试AtomicIntegerFieldUpdater
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/4 22:49
 */
public class AtomicIntegerFieldUpdateTest {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName("zs");
        bankAccount.setAddress("sc");
        bankAccount.setMoney(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread( () -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.increment(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(bankAccount.getMoney());
    }
}
