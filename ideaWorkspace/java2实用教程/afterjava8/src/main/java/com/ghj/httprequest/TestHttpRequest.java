package com.ghj.httprequest;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guohuanjun1
 * @date 2023/8/29 10:18
 */
public class TestHttpRequest {
        public static void main(String[] args) {
                HttpClient httpClient = HttpClientBuilder.create().build();
                String url = "";
                HttpPost httpPost = new HttpPost(url);
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("1", "www");
                map.put("2", "ww");
                map.put("3", "qq");
                map.put("4", "ee");
                list.add(map);
                System.out.println(list.toString());
        }
}
