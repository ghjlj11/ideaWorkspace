package lj.leetcode.code70;

public class Main {
    public static void main(String args[]){
        Solution2 A=new Solution2();
        System.out.println(A.climbStairs(45));
    }
}
class Solution {
    public int climbStairs(int n) {
        int ans=pp(n,0);
        return ans;
    }
    private int pp(int n,int x){
        if(n==0){
            x+=1;
            return x;
        }
        if(n>=1){
            x=pp(n-1,x);
        }
        if(n>=2){
            x=pp(n-2,x);
        }
        return x;
    }
}
class Solution2 {
    public int climbStairs(int n) {
        int ans=0;
        int a=0,b=1;
        for(int i=1;i<=n;i++){
            ans=a+b;
            a=b;
            b=ans;
        }
        return ans;
    }
}
