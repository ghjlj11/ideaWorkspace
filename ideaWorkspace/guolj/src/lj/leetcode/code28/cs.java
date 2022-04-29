package lj.leetcode.code28;

public class cs {
    public int strStr(String haystack, String needle) {
        int n=haystack.length();
        int m=needle.length();
        if(needle.isEmpty()){return 0;}
        int[] next=new int[m+1];
        haystack=" "+haystack;
        needle=" "+needle;
        char[] h=haystack.toCharArray();
        char[] d=needle.toCharArray();
        for(int i=2,j=0;i<=m;i++){
            while(j>0&&d[i]!=d[j+1]){
                j=next[j];
            }
            if(d[i]==d[j+1]){
                j++;
            }
            next[i]=j;
        }
        for(int i=1,j=0;i<=n;i++){
            while(j>0&&h[i]!=d[j+1]){
                j=next[j];
            }
            if(h[i]==d[j+1]){
                j++;
            }
            if(j==m){return i-m;}
        }
        return -1;
    }
}
