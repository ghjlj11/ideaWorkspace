package lj.leetcode.code91;

class Main {
    public static void main(String[] args){
        Solution A=new Solution();
        String s="301";
        System.out.println(A.numDecodings(s));
    }
}
class Solution {
    public int numDecodings(String s) {
        int length=s.length();
        if(s.charAt(0)=='0'){
            return 0;
        }
        int[] dp=new int[length];
        dp[0]=1;
        char[] array=s.toCharArray();
        int a,b;
        if(1<length){
            a=array[1]-'0';
            b=(array[0]-'0')*10+array[1]-'0';
            if(a==0){
                if(b<=20&&b>0){
                    dp[1]=1;
                }
                else {
                    return 0;
                }
            }
            else {
                if(b<=26){
                    dp[1]=2;
                }
                else {
                    dp[1]=1;
                }
            }
        }
        for(int i=2;i<length;i++){
            a=array[i]-'0';
            b=(array[i-1]-'0')*10+array[i]-'0';
            if(0<a&&a<=9){
                dp[i]=dp[i-1];
            }
            if(10<=b&&b<=26){
                dp[i]=dp[i]+dp[i-2];
            }
        }
        return dp[length-1];
    }
}
