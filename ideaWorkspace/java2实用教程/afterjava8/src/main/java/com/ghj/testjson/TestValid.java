package com.ghj.testjson;


import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * @author guohuanjun1
 * @date 2023/7/24 11:12
 */
@Validated
public class TestValid {
    public static void main(String[] args) {
        TestValid testValid = new TestValid();
        Dept dept = new Dept();
        dept.setAddress("123");
        testValid.testValid(null);
    }

    @Validated
    public void testValid(@NotNull Dept dept){
        System.out.println(dept);
    }
}
