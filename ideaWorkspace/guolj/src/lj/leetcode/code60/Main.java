package lj.leetcode.code60;
import java.util.*;
class Main {
    public static void main(String[] args){
        int n=4,k=9;
        Solution A=new Solution();
        System.out.println(A.st(n,k));
    }
}
class Solution {
    public String getPermutation(int n, int k){
        StringBuffer res=new StringBuffer();
        boolean[] dp=new boolean[n];
        Arrays.fill(dp,true);
        pa(res,k,n,0,0,dp);
        return res.toString();
    }
    private int pa (StringBuffer res,int k,int n,int times,int nums,boolean[] dp){
        if(nums==n){
            return times+1;
        }
        for(int i=1;i<=n;i++){
            if(dp[i-1]){
                res.append(i);
                dp[i-1]=false;
                times=pa(res,k,n,times,nums+1,dp);
                if(times==k){
                    break;
                }
                dp[i-1]=true;
                res.deleteCharAt(res.length()-1);
            }
        }
        return times;
    }
    public String st(int n,int k){
        StringBuffer a=new StringBuffer();
        StringBuffer res=new StringBuffer();
        for(int i=1;i<=n;i++){
            a.append(i);
        }
        if(jie(n)==k){
            return a.reverse().toString();
        }
        for(int i=0;i<n;i++){
            int pose=0;
            int x=jie(n-i-1);
            if(x>0){
                if(k%x==0){
                    res.append(a.charAt(k/x-1));
                    a.deleteCharAt(k/x-1);
                    for(int j=a.length()-1;j>=0;j--){
                        res.append(a.charAt(j));
                    }
                    break;
                }
                pose=k/x;
            }
            res.append(a.charAt(pose));
            a.deleteCharAt(pose);
            k=k-pose*x;
        }
        return res.toString();
    }
    private int jie(int n){
        if(n<=1){
            return n;
        }
        return n*jie(n-1);
    }
}
