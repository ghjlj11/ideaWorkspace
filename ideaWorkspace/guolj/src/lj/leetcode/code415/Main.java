package lj.leetcode.code415;

public class Main {
    public static void main(String[] args){
        String n="123",m="11";
        Solution A=new Solution();
        System.out.println(A.addStrings(n,m));
    }
}
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res=new StringBuilder();
        int i=num1.length()-1,j=num2.length()-1;
        int temp=0;
        while (i>=0&&j>=0){
            int n=num1.charAt(i)-'0';
            int m=num2.charAt(j)-'0';
            int o=(n+m+temp)%10;
            temp=(n+m+temp)/10;
            res.append(o);
            i--;
            j--;
        }
        int u=i<0?j:i;
        String num3=i<0?num2:num1;
        while (u>=0){
            int k=(temp+(num3.charAt(u)-'0'))%10;
            temp=(temp+(num3.charAt(u)-'0'))/10;
            res.append(k);
            u--;
        }
        if(temp==1){res.append(temp);}
        return res.reverse().toString();
    }
}
