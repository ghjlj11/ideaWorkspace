package com.ghj.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86187
 */
public class TestSleep {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        int i = 0;
        while (i < 10){
            Thread.sleep(1000);
            System.out.println(format.format(date));
            date = new Date();
            i ++;
        }
    }

}
