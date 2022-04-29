package com.ghj.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * @author 86187
 */
public class JsonUtil {
    public static String getJson(Object o, String dateFormat) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleDateFormat str = new SimpleDateFormat(dateFormat);
        objectMapper.setDateFormat(str);

        return objectMapper.writeValueAsString(o);
    }

    public static String getJson(Object o) throws JsonProcessingException {
        return getJson(o, "yyyy-MM-dd HH:mm:ss");
    }
}
