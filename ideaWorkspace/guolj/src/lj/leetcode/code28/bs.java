package lj.leetcode.code28;

public class bs {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0){return 0;}
        for(int i=0;i<=(haystack.length()-needle.length());i++){
            int n=0;
            int m=i;
            while (haystack.charAt(m)==needle.charAt(n)){
                n++;
                m++;
                if(n==needle.length()){return m-n;}
            }
        }
        return -1;
    }
}
