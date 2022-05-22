package lj.leetcode.code6074;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution a = new Solution();
        int i = a.percentageLetter("foobar", 'o');
        System.out.println(i);
    }
    public int percentageLetter(String s, char letter) {
        int sum = 0;
        char[] a = s.toCharArray();
        for (char c : a) {
            if(c == letter){
                sum ++;
            }
        }
        return sum * 100 / a.length;
    }
}
