package com.ghj.springcloud.controller;


import com.ghj.springcloud.service.PaymentService;
import com.ghj.springcloud.dto.CommonResult;
import com.ghj.springcloud.pojo.MyDept;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guohuanjun1
 * @description:
 * @date 2023/7/9 15:40
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/getDept")
    public CommonResult<MyDept> getDept(@RequestParam(value = "id", required = false) Long id){
        CommonResult<MyDept> commonResult = new CommonResult<>();
        MyDept myDept = paymentService.getById(id);
        return commonResult.setCode(200).setMessage("success").setData(myDept);
    }
}
