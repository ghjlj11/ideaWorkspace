package com.ghj.test.service.impl;

import com.ghj.test.Exception.MyException;
import com.ghj.test.mapper.TestMapper;
import com.ghj.test.service.TestService;
import com.ghj.test.utils.ThreadPollExecutorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/15 21:33
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper testMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testThread() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        AtomicBoolean isThrow = new AtomicBoolean(false);
        CompletableFuture.runAsync(new Runnable() {
            @Override
            @Transactional(rollbackFor = Exception.class)
            public void run() {
                Map<String, Object> insertData = new HashMap<>();
                insertData.put("id", "123");
                insertData.put("name", "123");
                insertData.put("code", "123");
                insertData.put("address", "123");
                insertData.put("age", 123);
                insertData.put("brith", new Date());
                try {
                    insertWrapper(insertData, countDownLatch, isThrow);
                } catch (Exception e) {
                    throw new RuntimeException("异常1");
                }
            }
        }, ThreadPollExecutorUtil.getThreadPoll());

        CompletableFuture.runAsync(new Runnable() {
            @Override
            @Transactional(rollbackFor = Exception.class)
            public void run() {
                Map<String, Object> updateData = new HashMap<>();
                updateData.put("id", "1");
                updateData.put("name", "hh");
                updateData.put("code", "hh");
                updateData.put("address", "hh");
                try {
                    updateWrapper(updateData, countDownLatch, isThrow);
                } catch (Exception e) {
                    throw new RuntimeException("异常2");
                }
            }
        }, ThreadPollExecutorUtil.getThreadPoll());

        CompletableFuture.runAsync(new Runnable() {
            @Override
            @Transactional(rollbackFor = Exception.class)
            public void run() {
                try {
                    deleteWrapper("2", countDownLatch, isThrow);
                } catch (Exception e) {
                    throw new RuntimeException("异常3");
                }
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
        new Thread(() -> {
            insertData();
        }).start();
        if (1 == 1) {
            throw new RuntimeException("22");
        }
        testMapper.delete("222");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertWrapper(Map<String, Object> params, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception {
        try {
            testMapper.insert(params);
        } catch (Exception e) {
            isThrow.set(true);
        } finally {
            countDownLatch.countDown();
        }
        countDownLatch.await();
        if (isThrow.get()) {
            System.out.println("\n insertWrapper 异常 \n");
            throw new Exception("th ins");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateWrapper(Map<String, Object> params, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception {
        try {
            testMapper.update(params);
        } catch (Exception e) {
            isThrow.set(true);
        } finally {
            countDownLatch.countDown();
        }
        countDownLatch.await();
        if (isThrow.get()) {
            System.out.println("\n updateWrapper 异常 \n");
            throw new Exception("th upd");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteWrapper(String id, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception {
        try {
            testMapper.delete(id);
            throw new Exception("hei");
        } catch (Exception e) {
            isThrow.set(true);
        } finally {
            countDownLatch.countDown();
        }
        countDownLatch.await();
        if (isThrow.get()) {
            System.out.println("\n deleteWrapper 异常 \n");
            throw new Exception("th del");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
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
