package lj.leetcode.code67;

class Main {
    public static void main(String[] args){
        String a="1010",b="1011";
        Solution A=new Solution();
        System.out.println(A.addBinary(a,b));
    }
}
class Solution {
    public String addBinary(String a, String b) {
        int n=a.length();
        int m=b.length();
        int temp=0;
        StringBuffer res=new StringBuffer();
        int x=Math.max(n,m);
        for(int i=1;i<=x;i++){
            int k=(n-i)>=0?(a.charAt(n-i)-'0'):0;
            int l=(m-i)>=0?(b.charAt(m-i)-'0'):0;
            int sum=k+l+temp;
            res.append(sum%2);
            temp=sum/2;
        }
        if(temp!=0){
            res.append(1);
        }
        return res.reverse().toString();
    }
}
