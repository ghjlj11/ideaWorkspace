package lj.random;
import java.util.Scanner;
import java.util.Arrays;
public class shuzu {
    public static void main(String[] args){
        Scanner ss=new Scanner(System.in);
        int[] ad=new int[ss.nextInt()];
        for(int i=0;i<ad.length;i++){
            ad[i]=ss.nextInt();
        }
        System.out.println(Arrays.toString(ad));
        System.out.println(10 << 1);
        // -20
        System.out.println(-10 << 1);
        // 5
        System.out.println(10 >> 1);
        // -5
        System.out.println(-10 >> 1);
        // 5
        System.out.println(10 >>> 1);
        // 2147483643
        System.out.println(-10 >>> 1);
    }
}
