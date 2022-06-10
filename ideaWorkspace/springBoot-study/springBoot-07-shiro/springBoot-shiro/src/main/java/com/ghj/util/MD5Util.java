package com.ghj.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author 86187
 */
public class MD5Util {
    private static String SALT = "ghj";

    public static String md5(String s){
        return new Md5Hash(s, SALT).toString();
    }
}
