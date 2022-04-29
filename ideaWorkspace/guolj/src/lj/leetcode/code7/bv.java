package lj.leetcode.code7;

public class bv {
    public int reverse(int x) {
        int n, k = 0, last=0;
        n = x;
        while (n != 0) {
            if(k>Integer.MAX_VALUE/10||k<Integer.MIN_VALUE/10){
                return 0;
            }
            k = k * 10 + n % 10;
            n = n / 10;
        }
            return k;
    }
}
