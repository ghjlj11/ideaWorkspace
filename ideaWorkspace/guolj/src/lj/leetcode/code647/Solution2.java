package lj.leetcode.code647;

/**
 * @author 86187
 */
public class Solution2 {
    public int countSubstrings(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int sum = 0 ;
        for(int i = 0 ; i < len ; i ++){
            sum += sub(chars, i - 1, i) + sub(chars, i , i);
        }
        return sum;
    }
    private int sub(char[] chars, int l, int r){
        int sum = 0;
        int len = chars.length;
        while ( l >= 0 && r < len && chars[l] == chars[r]){
            l --;
            r ++;
            sum ++;
        }
        return sum;
    }
}
