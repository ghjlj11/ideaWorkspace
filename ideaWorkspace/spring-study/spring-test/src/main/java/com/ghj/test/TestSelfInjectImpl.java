package com.ghj.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/12 11:07
 */
@Component
public class TestSelfInjectImpl implements TestSelfInject{

    @Autowired
    TestSelfInject testSelfInject;

    @Override
    public void test() {
        testSelfInject.out();
    }

    @Override
    public void out() {
        System.out.println("haha");
    }
}
