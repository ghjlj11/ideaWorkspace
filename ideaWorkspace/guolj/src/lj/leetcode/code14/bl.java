package lj.leetcode.code14;

import java.util.Arrays;

public class bl {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String st="";
        int i,n;
        n=strs[0].length()<strs[strs.length-1].length()?strs[0].length():strs[strs.length-1].length();
        for(i=1;i<=n;i++){
            if((strs[0].substring(0,i)).equals(strs[strs.length-1].substring(0,i))){
                st=strs[0].substring(0,i);
            }
            }
        return st;
    }
}
