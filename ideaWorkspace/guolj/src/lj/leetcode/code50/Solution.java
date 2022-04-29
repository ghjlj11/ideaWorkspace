package lj.leetcode.code50;

public class Solution {
    public double myPow(double x, int n) {
        double res=1.0;
        double m=1.0;
        if(x!=1&&n!=0){
            if(n>0){
                res=x;
                while(n>1){
                    if(n%2!=0){
                        n++;
                        m*=res;
                    }
                    res*=res;
                    n/=2;
                }
                return res/m;
            }
            if(n<0){
                res=x;
                while(n<-1){
                    if(n%2!=0){
                        n--;
                        m*=res;
                    }
                    res*=res;
                    n/=2;
                }
            }
            return m/res;
        }
          return res;
    }
}
