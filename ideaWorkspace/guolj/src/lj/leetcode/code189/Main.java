package lj.leetcode.code189;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
//        int[] x = {1,2,3,4,5,6};
//        int k = 4;
//        Solution a = new Solution();
//        a.rotate(x,k);
//        System.out.println(Arrays.toString(x));
        int x = -9 , y = -18;
        Main m = new Main();
        System.out.println(m.te(x,y));
    }
    private int te(int x , int y){
        return y > 0 ? te(y, x % y) : x;
    }
}
