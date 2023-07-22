package com.ghj.springcloud.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.ghj.springcloud.dto.CommonResult;

/**
 * sentinel 公共异常处理
 * @author guohuanjun1
 * @date 2023/7/22 16:08
 */
public class CommonSentinelExceptionHandler {
    public static CommonResult sentinelExceptionHandler( BlockException blockException){
        CommonResult result = new CommonResult<>();
        String msg = "";
        result.setCode(4444);
        // 依据不同的异常，判断是因为什么规则而限流的
        if (blockException instanceof FlowException) {
            msg = "请求被限流了";
        } else if (blockException instanceof ParamFlowException) {
            msg = "请求被热点参数限流";
        } else if (blockException instanceof DegradeException) {
            msg = "请求被降级了";
        } else if (blockException instanceof AuthorityException) {
            msg = "没有权限访问";
        }
        result.setMessage(msg);
        return result;
    }
}
