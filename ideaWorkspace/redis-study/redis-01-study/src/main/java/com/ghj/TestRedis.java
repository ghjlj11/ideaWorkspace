package com.ghj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author 86187
 */
public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("43.142.32.254",6379);
        Transaction multi = jedis.multi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name", "ghj");
        String s = jsonObject.toJSONString();
        try {
            multi.set("user1",s);
            multi.set("user2",s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
        }
    }
}
