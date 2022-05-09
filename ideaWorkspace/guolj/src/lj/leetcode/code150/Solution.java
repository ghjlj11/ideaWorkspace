package lj.leetcode.code150;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int evalRPN(String[] tokens) {
        int len = tokens.length;
        Deque<Integer> deq1 = new ArrayDeque<>();
        for(int i = 0  ; i < len ; i ++){
            if(isNum(tokens[i])){
                int t = Integer.parseInt(tokens[i]);
                deq1.push(t);
            }
            else {
                int x = deq1.pop();
                int y = deq1.pop();
                if("+".equals(tokens[i])){
                    deq1.push(y + x);
                }
                else if("-".equals(tokens[i])){
                    deq1.push(y - x);
                }
                else if("*".equals(tokens[i])){
                    deq1.push(y * x);
                }
                else {
                    deq1.push(y / x);
                }
            }
        }
        return deq1.pop();
    }
    private boolean isNum(String s){
        int len = s.length();
        char c = s.charAt(len - 1);
        if(c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }
}
