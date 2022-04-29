package lj.leetcode.code461;

/**
 * @author 86187
 */
public class Solution {
    public int hammingDistance(int x, int y) {
        int res = 0 ;
        while ( x > 0 || y > 0 ){
            res += ( x % 2 == y % 2 ) ? 0 : 1 ;
            x >>= 1;
            y >>= 1;
        }
        return res;
    }
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount( x ^ y);
    }
    public int hammingDistance3(int x, int y) {
        int res = 0 ;
        int c = x ^ y;
        while (c > 0){
            res += c % 2 == 0 ? 0 : 1 ;
            c >>= 1;
        }
        return res;
    }
    public int hammingDistance4(int x, int y) {
        int res = 0 ;
        int c = x ^ y ;
        while (c != 0 ){
            res ++;
            c &= c - 1 ;
        }
        return res;
    }
}
