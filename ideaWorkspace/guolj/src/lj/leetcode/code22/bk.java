package lj.leetcode.code22;
import java.util.ArrayList;
import java.util.List;
public class bk {
    public List<String> generateParenthesis(int n) {
        List<String> s=new ArrayList<String>();
        String k="";
        sc(s,n,n,k);
        return s;
    }
    public void sc(List<String> k,int n,int m,String h){
        if(n==0&&m==0){
            k.add(h);
            return;
        }
        if(n>0){
            sc(k,n-1,m,h+"(");
        }
        if(n<m){
            sc(k,n,m-1,h+")");
        }
    }
}
