package lj.leetcode.code58;

public class Solution {
    public int lengthOfLastWord(String s) {
        int m=s.length()-1;
        while(s.charAt(m)==' '){
            m--;
        }
        int i=m;
        while (i>=0&&s.charAt(i)!=' '){
            i--;
        }
        return m-i;
    }
}
