package com.ghj.consumer.controller;


import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 86187
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    /**
     * 该注解配置与xml基本一样， 同样包含接口全限定类名， 版本号， 重新请求次数， 超时时间等配置
     */
    @DubboReference(interfaceClass = UserProviderService.class, check = false)
    UserProviderService userProviderService;


    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        return userProviderService.getUsers();
    }
}
