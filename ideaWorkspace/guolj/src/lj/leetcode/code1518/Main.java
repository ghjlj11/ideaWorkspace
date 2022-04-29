package lj.leetcode.code1518;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int bottle=n,sums=n;
        while(bottle>=3){
            sums+=bottle/3;
            bottle=bottle/3+bottle%3;
        }
        System.out.println(sums);
    }
}