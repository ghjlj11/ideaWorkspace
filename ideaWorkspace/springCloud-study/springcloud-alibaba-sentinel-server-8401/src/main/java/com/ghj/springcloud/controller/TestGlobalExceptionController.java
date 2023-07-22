package com.ghj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ghj.springcloud.dto.CommonResult;
import com.ghj.springcloud.exception.CommonSentinelExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guohuanjun1
 * @date 2023/7/22 16:30
 */
@RestController
@RequestMapping("/exception")
public class TestGlobalExceptionController {

    @RequestMapping("/a")
    @SentinelResource(value = "global", blockHandlerClass = CommonSentinelExceptionHandler.class, blockHandler = "sentinelExceptionHandler")
    public CommonResult<String> test01(){
        return new CommonResult<>(200, "success", null, null);
    }
}
