package com.ghj.springcloud.service;

import com.ghj.springcloud.dto.CommonResult;
import com.ghj.springcloud.pojo.MyDept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author guohuanjun1
 * @date 2023/7/23 16:55
 */
@FeignClient(name = "nacos-payment-provider", fallback = SentinelServiceDe.class)
public interface SentinelService {
    /**
     * id查询
     * @param id
     * @return
     */
    @PostMapping("/payment/getDept")
    CommonResult<MyDept> getDept(@RequestParam(value = "id", required = false) Long id);
}
