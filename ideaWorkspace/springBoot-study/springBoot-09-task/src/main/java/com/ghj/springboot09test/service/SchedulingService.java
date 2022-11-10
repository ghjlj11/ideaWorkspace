package com.ghj.springboot09test.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author 86187
 */

@Service
public class SchedulingService {

    @Scheduled(cron = "0/2 * * * 11 *")
    public void scheduling(){
        System.out.println("你好呀.......");
    }
}
