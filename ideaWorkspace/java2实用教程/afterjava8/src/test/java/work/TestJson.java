package work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestJson {
    @Test
    public void test01() throws JsonProcessingException {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        // fastjson
        String s = JSON.toJSONString(set);
        Integer[] jsonObject = JSON.parseObject(s, Integer[].class);
        System.out.println(Arrays.toString(jsonObject));
        System.out.println(s);
        // jackson
        ObjectMapper jackson = new ObjectMapper();
        String s1 = jackson.writeValueAsString(set);
        System.out.println(s1);
        Set set1 = jackson.readValue(s1, Set.class);
        System.out.println(set1);
    }
}
