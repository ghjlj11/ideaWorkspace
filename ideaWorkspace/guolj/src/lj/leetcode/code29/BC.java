package lj.leetcode.code29;

public class BC {
    public int divide(int dividend, int divisor) {
        boolean f=false;
        if((dividend>0&&divisor>0)||(dividend<0&&divisor<0))f=true;
        if(dividend==Integer.MIN_VALUE&&divisor==-1){return Integer.MAX_VALUE;}
        if(dividend>0)dividend=-dividend;
        if(divisor>0)divisor=-divisor;
        int ans=0;
        while(dividend<=divisor){
            int d=-1,x=divisor;
            while(dividend-x<=x){
                x+=x;
                d+=d;
            }
            dividend-=x;
            ans+=d;
        }
        return f?-ans:ans;
    }
}
