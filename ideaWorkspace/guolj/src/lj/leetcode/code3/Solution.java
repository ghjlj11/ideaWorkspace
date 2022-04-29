package lj.leetcode.code3;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author 86187
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> sd=new HashSet<Character>();
        int n=s.length();
        int a=0,k=-1;
        for(int i=0;i<n;i++){
            if(i!=0){
                sd.remove(s.charAt(i-1));
            }
            while (k+1<n&&!sd.contains(s.charAt(k+1))){
                sd.add(s.charAt(k+1));
                k++;
            }
            a=Math.max(a,k-i+1);
        }
        return a;
    }
    public int len(String s){
        int length = s.length();
        if(length == 0){
            return 0;
        }
        Deque<Character> deque = new LinkedList<Character>();
        int max = 0;
        for(int i = 0; i < length; i ++){
            char temp = s.charAt(i);
            while (deque.contains(temp)){
                deque.removeFirst();
            }
            deque.add(temp);
            max = Math.max(max, deque.size());
        }
        return max;
    }
    public int maxLength(String s){
        int len = s.length();
        if(len == 0){
            return 0;
        }
        Set<Character> set = new HashSet<Character>();
        int l = 0;
        int max = 0;
        for(int i = 0 ; i < len ; i ++){
            if(i != 0){
                set.remove(s.charAt(i - 1));
            }
            while (l < len && !set.contains(s.charAt(l))){
                set.add(s.charAt(l));
                l ++;
            }
            max = Math.max(max, l - i);
            if(l == len){
                break;
            }
        }
        return max;
    }
}
