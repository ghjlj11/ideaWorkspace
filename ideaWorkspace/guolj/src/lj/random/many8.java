package lj.random;
import java.util.Scanner;

public class many8 {

    public static void main(String[] args){
        int x;
        Scanner sc=new Scanner(System.in);
        x=sc.nextInt();
        System.out.println(eight(x));
        xiaoyu k=new xiaoyu();
        k.aa(10086);
        //System.out.println(k.m);
    }
    public static int eight(int n){
        int i,j,s,sum=0;
        for(i=0;i<n;i++){
            s=0;
            for(j=0;j<=i;j++){
                s+=8*Math.pow(10,j);
            }
            sum+=s;
        }
        return sum;
    }
}
class xiaoyu{
    int m=8;
    void aa(int n){
        System.out.println(n);
    }
}