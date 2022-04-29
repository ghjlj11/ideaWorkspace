package lj.leetcode.code38;

public class Solution {
    public String countAndSay(int n) {
        String s=String.valueOf(n);
        StringBuffer sb=new StringBuffer("1");
        sb=hh(n,sb,1);
        return String.valueOf(sb);
    }
    public StringBuffer hh(int n,StringBuffer sb,int index){
        if(index==n){return sb;}
        StringBuffer ss=new StringBuffer();
        int l=0,r,s=sb.length();
        while (l<s){
            r=l;
            int k=1;
            while (r<s-1&&sb.charAt(l)==sb.charAt(r+1)){
                k++;
                r++;
            }
            ss.append(k);
            ss.append(sb.charAt(l));
            l=r+1;
        }
        return hh(n,ss,index+1);
    }
}
