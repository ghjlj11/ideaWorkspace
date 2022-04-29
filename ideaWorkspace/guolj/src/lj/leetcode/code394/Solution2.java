package lj.leetcode.code394;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86187
 */
public class Solution2 {
    public String decodeString(String s) {
        Deque<String> str = new ArrayDeque<>();
        int len = s.length();
        int k = 0 ;
        char[] chars = s.toCharArray();
        while (k < len){
            if(chars[k] - '9' <= 0){
                int l = k ;
                while (chars[k + 1] - '9' <= 0){
                    k ++;
                }
                str.push(s.substring(l, k + 1));
            }
            else if(chars[k] == ']'){
                Deque<String> temp = new ArrayDeque<>();
                while (!"[".equals(str.peek())){
                    temp.addFirst(str.pop());
                }
                StringBuilder st = new StringBuilder();
                while (!temp.isEmpty()){
                    st.append(temp.removeFirst());
                }
                str.pop();
                int t = Integer.parseInt(str.pop());
                String o = st.toString();
                o = o.repeat(t);
                str.push(o);
            }
            else {
                str.push(String.valueOf(chars[k]));
            }
            k ++;
        }
        StringBuilder res = new StringBuilder();
        while (!str.isEmpty()){
            res.append(str.removeLast());
        }
        return res.toString();
    }
}
