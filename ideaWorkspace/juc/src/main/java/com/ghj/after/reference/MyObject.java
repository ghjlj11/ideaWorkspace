package com.ghj.after.reference;

/**
 * <p>
 * Description: 对象被gc回收之前会执行finalize方法，这是对象最后一次自我拯救的机会
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/23 23:23
 */
public class MyObject {

    @Override
    public void finalize () {
        System.out.println(" finalize execute ...");
    }
}
