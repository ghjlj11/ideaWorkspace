package com.ghj.service;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/9/25 14:59
 */
@Data
@Component
public class Test02 {
    @Resource
    Test01 test01;

    private String v;

}
