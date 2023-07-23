package com.ghj.springcloud.service;

import com.ghj.springcloud.dto.CommonResult;
import com.ghj.springcloud.pojo.MyDept;
import org.springframework.stereotype.Service;

/**
 * @author guohuanjun1
 * @date 2023/7/23 17:00
 */
@Service
public class SentinelServiceDe implements SentinelService{
    @Override
    public CommonResult<MyDept> getDept(Long id) {
        return new CommonResult<MyDept>().setCode(999).setMessage("failed").setData(new MyDept(999L, "服务降级", "服务降级"));
    }
}
