package lj.leetcode.code32;

import java.util.Stack;

public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st=new Stack<Integer>();
        int n=s.length(),i=0;
        int ans=0;
        st.push(-1);
        for(i=0;i<n;i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }
            else {
                st.pop();
                if(st.empty()){
                    st.push(i);
                }
                else {
                    ans=Math.max(ans,i-st.peek());
                }
            }
        }
        return ans;
    }
}
