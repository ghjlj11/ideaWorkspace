package lj.leetcode.code5234;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        String s = words[0];
        int len = words.length;
        res.add(s);
        for (int i = 1 ; i < len ; i ++){
            if (!pk(s, words[i])) {
                res.add(words[i]);
                s = words[i];
            }
        }
        return res;
    }
    private boolean pk(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        if(s1.equals(s2)){
            return true;
        }
        int[] k1 = new int[26];
        int[] k2 = new int[26];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0 ; i < c1.length ; i ++){
            k1[c1[i] - 'a']++;
            k2[c2[i] - 'a']++;
        }
        for(int i = 0 ; i < 26 ; i ++){
            if(k1[i] != k2[i]){
                return false;
            }
        }
        return true;
    }
}
