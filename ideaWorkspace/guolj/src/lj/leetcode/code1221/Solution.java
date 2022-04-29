package lj.leetcode.code1221;

public class Solution {
    public int balancedStringSplit(String s) {
        int res = 0,k=0;
        for(int i=0;i<s.length();i++){
            char a=s.charAt(i);
            if(a=='L'){k++;}
            else{k--;}
            if(k==0){res++;}
        }
        return res;
    }
}
