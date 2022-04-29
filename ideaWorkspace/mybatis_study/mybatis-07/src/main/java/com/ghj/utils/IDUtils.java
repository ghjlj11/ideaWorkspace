package com.ghj.utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author 86187
 */
public class IDUtils {
    public static String getID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
