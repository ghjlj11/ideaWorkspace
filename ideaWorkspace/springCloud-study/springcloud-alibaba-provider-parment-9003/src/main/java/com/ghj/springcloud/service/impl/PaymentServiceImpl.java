package com.ghj.springcloud.service.impl;


import com.ghj.springcloud.service.PaymentService;
import com.ghj.springcloud.pojo.MyDept;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * @author guohuanjun1
 * @date 2023/7/23 16:36
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    private static final HashMap<Long, MyDept> STATIC_MAP;
    static {
        STATIC_MAP = new HashMap<>();
        STATIC_MAP.put(1L, new MyDept(1L, "haha1", "beijing"));
        STATIC_MAP.put(2L, new MyDept(2L, "haha2", "shanghai"));
        STATIC_MAP.put(3L, new MyDept(3L, "haha3", "wuhan"));
        STATIC_MAP.put(4L, new MyDept(4L, "haha4", "changsha"));

    }
    @Override
    public MyDept getById(Long id) {

        return STATIC_MAP.get(id);
    }
}
