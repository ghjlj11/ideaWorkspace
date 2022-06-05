package myTest.myJVM;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class MyRobot {
    public final String s = "12345" ;
    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println(l / 1024 / 1024);
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println(l1 / 1024 / 1024);
        String x = "sd";
        switch (x){
            case "sx":
                System.out.println("sx");
                break;
            case "sd":
                System.out.println("sd");
                break;
        }
    }
}
