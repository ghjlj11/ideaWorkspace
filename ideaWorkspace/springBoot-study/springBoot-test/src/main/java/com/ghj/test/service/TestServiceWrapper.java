package com.ghj.test.service;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/16 22:32
 */
public interface TestServiceWrapper {

    /**
     * insertWrapper
     * @param params
     * @param countDownLatch
     * @param isThrow
     * @throws Exception
     */
    void insertWrapper(Map<String, Object> params, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception;

    /**
     * updateWrapper
     * @param params
     * @param countDownLatch
     * @param isThrow
     * @throws Exception
     */
    void updateWrapper(Map<String, Object> params, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception;

    /**
     * deleteWrapper
     * @param id
     * @param countDownLatch
     * @param isThrow
     * @throws Exception
     */
    void deleteWrapper(String id, CountDownLatch countDownLatch, AtomicBoolean isThrow) throws Exception;
}
