package com.ghj.xiefu;

import java.util.Scanner;

/**
 * @author guohuanjun1
 * @date 2023/8/3 12:20
 */
public class HelloWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            String s1 = s.toUpperCase();
            System.out.println("输入了：" + s);
            System.out.println("修改：" + s1);
        }
    }
}
