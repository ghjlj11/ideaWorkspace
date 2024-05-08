package com.ghj.testhashcode;

import com.ghj.pojo.TestHashCodePojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/23 16:36
 */
public class TestHashCode {
    public static void main(String[] args) {
        TestHashCodePojo testHashCode1 = new TestHashCodePojo();
        String name1 = "123";
        testHashCode1.setName(name1);
        testHashCode1.setAge(2);
        TestHashCodePojo testHashCode2 = new TestHashCodePojo();
        String name2 = new String("123");
        testHashCode2.setName(name2);
        testHashCode2.setAge(2);
        System.out.println(testHashCode1.hashCode());
        System.out.println(testHashCode2.hashCode());
        System.out.println(name1 == name2);
        System.out.println(name1.hashCode() + "-----" + name2.hashCode());
        HashMap<String, String> map1 = new HashMap<>(5);
        map1.put("123", "123");
        map1.put("456", "456");
        map1.put(new String("eee"), "eee");
        HashMap<String, String> map2 = new HashMap<>(5);
        map2.put("123", "123");
        map2.put("456", "456");
        map2.put(new String("eee"), "eee");
        System.out.println(map1.hashCode());
        System.out.println(map2.hashCode());
        System.out.println(map1.equals(map2));
        List<String> list;
        System.out.println(list == null);
    }
}
