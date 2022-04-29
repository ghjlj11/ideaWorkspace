package lj.leetcode.code5;

public class Solution {
    public String longestPalindrome(String s) {
        String c=new String();
        int n=s.length(),i,left,right,k,m;
        c=String.valueOf(s.charAt(0));
        for(i=0;i<n;i++){
            left=right=k=m=i;
            if(i+1<n&&s.charAt(i)==s.charAt(i+1)){
                k=k+1;
            while(m-1>=0&&k+1<n&&s.charAt(k+1)==s.charAt(m-1)){
                ++k;
                --m;
            }
            c=(s.substring(m,k+1).length()>c.length())?s.substring(m,k+1):c;}
            while(left-1>=0&&right+1<n&&s.charAt(left-1)==s.charAt(right+1)){
                --left;
                ++right;
            }
            c=(s.substring(left,right+1).length()>c.length())?s.substring(left,right+1):c;
        }
        return c;
    }
    public String longest(String s){
        int length=s.length();
        if(length==1){
            return s;
        }
        char[] array = s.toCharArray();
        String res=String.valueOf(array[0]);
        int num=1;
        boolean[][] dp=new boolean[length][length];
        for(int i=0;i<length;i++){
            dp[i][i]=true;
        }
        for(int i=1;i<length;i++){
            for(int j=0;j<i;j++){
                dp[i][j]=false;
            }
        }
        for(int i=length-2;i>=0;i--){
            for(int j=i+1;j<length;j++){
                if(array[i]==array[j]){
                    if((i+1<=j-1&&dp[i + 1][j - 1])||j==i+1){
                        dp[i][j] = true;
                        if (num < j - i + 1) {
                            num = j - i + 1;
                            res = s.substring(i, j + 1);
                        }
                    }
                }
            }
        }
        return res;
    }
}
