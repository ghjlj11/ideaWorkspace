package com.ghj.test.service.impl;

import com.ghj.test.mapper.TestMapper;
import com.ghj.test.service.TestServiceWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/16 22:34
 */
@Service
public class TestServiceWrapperImpl implements TestServiceWrapper {

    @Resource
    TestMapper testMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    @Transactional(rollbackFor = Exception.class)
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
}
