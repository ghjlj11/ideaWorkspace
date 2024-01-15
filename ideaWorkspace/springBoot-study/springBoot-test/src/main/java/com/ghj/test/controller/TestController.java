package com.ghj.test.controller;

import com.ghj.test.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/15 21:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    @RequestMapping("/thread")
    public void thread () throws Exception{
        testService.testThread();
    }

    @RequestMapping("/aa")
    public void aa () throws Exception{
        testService.aa();
    }

}
