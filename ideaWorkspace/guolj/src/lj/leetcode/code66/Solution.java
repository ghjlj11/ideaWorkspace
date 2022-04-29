package lj.leetcode.code66;

public class Solution {
    public int[] plusOne(int[] digits) {
        int n=digits.length;
        digits[n-1]+=1;
        for(int i=n-1;i>0;i--){
            if(digits[i]==10){
                digits[i-1]+=1;
                digits[i]=0;
            }
            else {
                break;
            }
        }
        if(digits[0]==10){
            int[] d=new int[n+1];
            d[0]=1;
            d[1]=0;
            for(int j=2;j<n+1;j++){
                d[j]=digits[j-1];
            }
            return d;
        }
        else {
            return digits;
        }
    }
}
