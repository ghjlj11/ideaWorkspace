package lj.leetcode.code20;

import java.util.Stack;

public class cu {
    public boolean isValid(String s) {
        Stack<Character> k=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char l=s.charAt(i);
            if(l=='('||l=='['||l=='{'){
                k.push(l);
            }
            else if(l==')'){
                if(!k.empty()&&k.peek()=='('){k.pop();}
                else{return false;}
            }
            else if(l==']'){
                if(!k.empty()&&k.peek()=='['){k.pop();}
                else{return false;}
            }
            else if(l=='}'){
                if(!k.empty()&&k.peek()=='{'){k.pop();}
                else{return false;}
            }
        }
        return k.empty();
    }
}
