package com.ghj.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

/**
 * @author 86187
 */
public class MyResponseEntity {
    int k = 23;
    public static ResponseEntity getResponseEntity(Object k, HttpStatus status){
        return ResponseEntity.status(status).header("content-type", "application/json;charset=UTf-8").body(k);
    }
}
