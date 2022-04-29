package lj.leetcode.code438;

import java.util.*;

/**
 * @author 86187
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int[] dp = new int[26];
        int[] allZero = new int[26];
        for (char c : pc) {
            dp[c - 'a'] ++;
        }
        int len1 = sc.length;
        int len2 = pc.length;
        if(len1 < len2){
            return res;
        }
        int l = 0, r = 0 ;
        for( r = 0 ; r< len2 ; r ++){
            dp[sc[r] - 'a'] --;
        }
        if(Arrays.equals(dp,allZero)){
            res.add(0);
        }

        for( l = 0 ; r < len1 ; r ++, l ++){
            dp[sc[l] - 'a'] ++;
            dp[sc[r] - 'a'] --;
            if(Arrays.equals(dp,allZero)){
                res.add(l + 1);
            }
        }
        return res;
    }
    private boolean allZero(int[] dp){
        for (int i : dp) {
            if(i != 0 ){
                return false;
            }
        }
        return true;
    }
}
