package com.ghj.testswitch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author guohuanjun1
 * @date 2023/8/17 14:40
 */
public class TestSwitch {
    public static void main(String[] args) {
        List<String> infos = new ArrayList<>();
        infos.add("1");
        infos.add("2");
        infos.add("67");
        infos.add("4");
        infos.add("5");
        for (String info : infos) {

            switch (info){
                case "1":
                    System.out.println("11111");
                    break;
                case "2":
                    System.out.println("22222");
                    break;
                case "3":
                    System.out.println("33333");
                    break;
                case "4":
                    System.out.println("44444");
                    break;
                default:
                    System.out.println("默认");
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
