package com.ghj.test.service.impl;

import com.ghj.test.Exception.MyException;
import com.ghj.test.mapper.TestMapper;
import com.ghj.test.service.TestService;
import com.ghj.test.service.TestServiceWrapper;
import com.ghj.test.utils.ThreadPollExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.AppContext;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * Description: 测试多线程事务
 *
 * 多线程事务一致性：
 * 使用CountDownLatch阻塞线程，如果有线程抛出异常，则修改AtomicBoolean的值为true，线程结束业务代码后CountDownLatch减一
 * 直到所有线程执行完毕，CountDownLatch不阻塞，此时所有线程进入最终确认状态，即确认AtomicBoolean，如果检测到某一个线程抛出异常，
 * 则全部线程接下来抛异常，否则就继续执行。
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/15 21:33
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper testMapper;

    @Resource
    TestServiceWrapper testServiceWrapper;

    @Autowired
    ApplicationContext applicationContext;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testThread() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        AtomicBoolean isThrow = new AtomicBoolean(false);
        // 通过spring容器拿到自身实例，从而避免调用自身一些方法时，spring注解失效
        // TestService testServiceImpl = applicationContext.getBean(TestService.class);
        long startTime = System.currentTimeMillis();
        CompletableFuture.runAsync(() -> {
            Map<String, Object> insertData = new HashMap<>();
            insertData.put("id", "123");
            insertData.put("name", "123");
            insertData.put("code", "123");
            insertData.put("address", "123");
            insertData.put("age", 123);
            insertData.put("brith", new Date());
            try {
                testServiceWrapper.insertWrapper(insertData, countDownLatch, isThrow);
            } catch (Exception e) {
                throw new RuntimeException("异常1");
            }
        }, ThreadPollExecutorUtil.getThreadPoll());


        CompletableFuture.runAsync(() -> {
            Map<String, Object> updateData = new HashMap<>();
            updateData.put("id", "1");
            updateData.put("name", "hh");
            updateData.put("code", "hh");
            updateData.put("address", "hh");
            try {
                testServiceWrapper.updateWrapper(updateData, countDownLatch, isThrow);
            } catch (Exception e) {
                throw new RuntimeException("异常2");
            }
        }, ThreadPollExecutorUtil.getThreadPoll());


        CompletableFuture.runAsync(() -> {
            try {
                testServiceWrapper.deleteWrapper("2", countDownLatch, isThrow);
            } catch (Exception e) {
                throw new RuntimeException("异常3");
            }
        }, ThreadPollExecutorUtil.getThreadPoll());
        Map<String, Object> insertData = new HashMap<>();
        insertData.put("id", "999");
        insertData.put("name", "999");
        insertData.put("code", "999");
        insertData.put("address", "999");
        insertData.put("age", 999);
        insertData.put("brith", new Date());
        testMapper.insert(insertData);
        countDownLatch.countDown();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("main 耗时: " + (endTime - startTime) + " ms");
        if (isThrow.get()) {
            throw new Exception("haha main");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void aa() throws Exception {
        Map<String, Object> insertMap = new HashMap<>();
        insertMap.put("id", "222");
        insertMap.put("name", "222");
        insertMap.put("code", "222");
        insertMap.put("address", "222");
        insertMap.put("age", 222);
        insertMap.put("brith", new Date());
        testMapper.insert(insertMap);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("id", "1");
        updateMap.put("name", "xixi");
        testMapper.update(updateMap);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            insertData();
            return 0;
        }, ThreadPollExecutorUtil.getThreadPoll());
        future.get();
        if (1 == 1) {
            throw new RuntimeException("22");
        }
        testMapper.delete("222");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "666");
        map.put("name", "666");
        map.put("code", "666");
        map.put("address", "666");
        map.put("age", 666);
        map.put("brith", new Date());
        testMapper.insert(map);
        if (1 == 1) {
            System.out.println("\n 223344 \n");
            throw new MyException("ssss");
        }
    }
}
