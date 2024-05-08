package com.ghj.controller;

import com.ghj.pojo.TestValidEntity;
import com.ghj.service.TestValidService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/27 15:02
 */
@RequestMapping("/test")
@RestController
public class TestValidController {

    @Resource
    TestValidService validService;

    @RequestMapping("/test")
    public String test() {
        TestValidEntity testValid = new TestValidEntity();
        testValid.setName("122");
        testValid.setAge(54);
        validService.test(testValid);
        return "hello";
    }
}
