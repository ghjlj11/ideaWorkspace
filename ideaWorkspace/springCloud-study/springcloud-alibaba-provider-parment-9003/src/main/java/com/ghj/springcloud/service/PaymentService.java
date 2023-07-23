package com.ghj.springcloud.service;


import com.ghj.springcloud.pojo.MyDept;

/**
 * @author guohuanjun1
 * @date 2023/7/23 16:34
 */
public interface PaymentService {

    /**
     * 通过id查询
     * @param id
     * @return
     */
    MyDept getById(Long id);
}
