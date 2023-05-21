package com.ghj.testjson;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class TestJson {
    public static void main(String[] args) {
        MyDto myDto1 = new MyDto();
        MyDto myDto2 = new MyDto();
        myDto2.setS1("s1");
        myDto2.setS2("s2");
        myDto2.setS3("s3");
        List<MyDto> list = new ArrayList<>();
        list.add(myDto1);
        list.add(myDto2);
        System.out.println(JSONObject.toJSONString(list));
        String l1;
        String l2 = null;
        String l3 = null;
        l1 = l2 + l3;
        int a = 1;
        String l4 = "" + a;
        System.out.println(l4);
        l1 = "" + myDto1 + myDto2;
        System.out.println(l1);
    }
}
