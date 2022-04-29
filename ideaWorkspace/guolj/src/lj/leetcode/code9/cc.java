package lj.leetcode.code9;

public class cc {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int s=0,r=x;
        int d=0;
        while(r!=0){
            s=r%10;
            d=d*10+s;
            r=r/10;
        }
        return d==x;
    }
}
