package com.ghj.springcloud.service;

import com.ghj.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
@Service
public class FeignServiceDe implements FallbackFactory {
    @Override
    public FeignService create(Throwable cause) {
        return new FeignService() {
            @Override
            public String addDept(Dept dept) {
                return "null";
            }

            @Override
            public Dept queryById(Long id) {
                return new Dept()
                        .setDept_no(id)
                        .setD_name("该服务由于服务降级， 暂时暂停服务")
                        .setDb_source("无数据");
            }

            @Override
            public List<Dept> queryAll() {
                return new ArrayList<>();
            }
        };
    }
}
