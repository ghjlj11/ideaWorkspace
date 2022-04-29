package lj.leetcode.code394;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86187
 */
public class Solution {
    public String decodeString(String s) {
        Deque<StringBuilder> str = new ArrayDeque<>();
        Deque<Integer> nums = new ArrayDeque<>();
        int k = 0 ;
        int len = s.length();
        char[] c = s.toCharArray();
        StringBuilder res = new StringBuilder();
        int u = 0 ;
        while (k < len){
            if(c[k] - '9' <= 0){
                int l = k;
                while (c[k + 1] - '9' <= 0){
                    k ++;
                }
                String temp = s.substring(l, k + 1);
                u = Integer.parseInt(temp);
            }
            else if(c[k] == '['){
                str.push(res);
                nums.push(u);
                u = 0 ;
                res = new StringBuilder();
            }
            else if(c[k] == ']'){
                int p = nums.pop();
                for(int i = 0 ; i < p ; i ++){
                    str.peek().append(res);
                }
                res = str.pop();
            }
            else {
                res.append(c[k]);
            }
            k ++;
        }
        return res.toString();
    }
}
