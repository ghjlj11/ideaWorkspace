package lj.leetcode.code69;

public class Main {
    public static void main(String[] args){
        int x=2147395599;
        Solution A=new Solution();
        System.out.println(A.sq(x));
    }
}
class Solution {
    public int mySqrt(int x) {
        int res;
        if(x==0||x==1){
            return x==0?0:1;
        }
        if(x>=2147395600){
            return 46340;
        }
        int r=46341,l=1;
        int k=(l+r)/2;
        while (true){
        while (k*k>x){
            if((k-1)*(k-1)<=x){
                return k-1;
            }
            r=k-1;
            k=(l+r)/2;
        }
        while (k*k<x){
            if((k+1)*(k+1)>=x){
                return (k+1)*(k+1)==x?(k+1):k;
            }
            l=k+1;
            k=(l+r)/2;
        }
        if(k*k==x){
            return k;
        }
        }
    }
    public int sq(int x){
        int l=1,r=x,k=(l+r)/2;
        if(x==0){
            return 0;
        }
        while (l<r-1){
            if((long)k*k>x){
                r=k;
            }
            else if((long)k*k<=x){
                l=k;
            }
            k=(l+r)/2;
        }
        return (l+r)/2;
    }
}
