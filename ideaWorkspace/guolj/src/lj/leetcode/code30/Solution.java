package lj.leetcode.code30;
import java.util.*;
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans=new ArrayList<>();
        Map<String,Integer> set=new HashMap<String,Integer>();
        for(String w:words){
            set.put(w,set.getOrDefault(w,0)+1);
        }
        int n=words[0].length(),nn=words.length,m=n*nn;
        int x=s.length();
        if(x<m){return ans;}
        for(int i=0;i<=x-m;i++){
            Map<String,Integer> ss=new HashMap<String,Integer>();
            String k=s.substring(i,i+m);
            for(int j=0;j<m;j+=n){
                String add=k.substring(j,j+n);
                ss.put(add,ss.getOrDefault(add,0)+1);
            }
            System.out.println(ss);
            if(ss.equals(set)){ans.add(i);}
        }
        return ans;
    }
}
