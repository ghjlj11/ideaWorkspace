package lj.LanQ.test;

import java.util.Scanner;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String g = sc.nextLine();
        long s = Long.valueOf(g);
        long o = Long.valueOf(5);
        long res = 0L;
        long num= 0L;
        Main m = new Main();
        while(s > res) {
            num += 5;
            if(num%100 == 0 ) {
                res += m.pp(num);
            }
            else {
                res += 1;
            }
        }
        if(res == s) {
            System.out.println(num);
        }
        else {
            System.out.println(-1);
        }
    }
    private int pp(long n) {
        int i = 0 ;
        while(n % 10 == 0) {
            i ++;
            n /= 10;
        }
        return i;
    }
}