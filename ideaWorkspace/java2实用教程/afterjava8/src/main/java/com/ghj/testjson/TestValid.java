package com.ghj.testjson;


import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author guohuanjun1
 * @date 2023/7/24 11:12
 */
public class TestValid {
    public static void main(String[] args) {
        TestValid testValid = new TestValid();
        testValid.testValid(new Dept());
    }

    public void testValid(@Validated Dept dept){
        System.out.println(dept);
    }
}
