package lj.leetcode.code1688;

public class Solution {
    public int numberOfMatches(int n) {
        int sum=0;
        while (n>1){
            if(n%2==0){
                sum+=n/2;
                n/=2;
            }
            else {
                sum+=n/2;
                n=(n+1)/2;
            }
        }
        return sum;
    }
}
