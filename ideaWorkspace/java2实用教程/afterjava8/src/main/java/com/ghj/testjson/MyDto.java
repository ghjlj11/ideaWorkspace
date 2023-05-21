package com.ghj.testjson;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author 86187
 */
@Data
public class MyDto {
    private Boolean is;
    private String s1;
    private String s2;
    private String s3;
    public String getUniqueKey(){
        String res = "";
        System.out.println("hahahahaha");
        if(is != null){
            if(is){
                res = this.s1;
            }
            else {
                res = JSONObject.toJSONString(this.s2) + JSONObject.toJSONString(this.s3);
            }
        }
        return res;
    }
    @Override
    public String toString(){
        System.out.println("wewe");
        return s3;
    }
}
