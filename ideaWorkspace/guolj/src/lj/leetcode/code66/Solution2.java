package lj.leetcode.code66;

public class Solution2 {
    public int[] plusOne(int[] digits) {
        int n=digits.length;
        for(int i=n-1;i>=0;i--){
            if(digits[i]==9){
                digits[i]=0;
            }
            else{
                digits[i]+=1;
                return digits;
            }
        }
        digits=new int[n+1];
        digits[0]=1;
        return digits;
    }
}
