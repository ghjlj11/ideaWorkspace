package lj.leetcode.code43;
public class Main {
    public static void main(String[] args){
        String n="9133",m="0";
        Solution A=new Solution();
        System.out.println(A.multiply(n,m));
    }
}
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        String a=singleMultiply(num2.charAt(num2.length()-1),num1,0);
        for(int i=num2.length()-2;i>=0;i--){
            a=addStrings(singleMultiply(num2.charAt(i),num1,num2.length()-1-i),a);
        }
        return a;
    }
    private String singleMultiply(char single,String num,int n){
        StringBuffer res=new StringBuffer();
        while(n>0){
            res.append(0);
            n--;
        }
        int sin=single-'0',temp=0;
        for(int i=num.length()-1;i>=0;i--){
            int x=temp+sin*(num.charAt(i)-'0');
            int k=x%10;
            temp=x/10;
            res.append(k);
        }
        if(temp>0){
            res.append(temp);
        }
        return res.reverse().toString();
    }
    public String addStrings(String num1, String num2) {
        StringBuffer res=new StringBuffer();
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
