package lj.leetcode.code8;
import java.util.Map;
import java.util.HashMap;
public class Ca {
    public int myAtoi(String s) {
        Sc d=new Sc();
        for(int i=0;i<s.length();i++){
            d.st(s.charAt(i));
        }
        return (int)(d.sign*d.ans);
    }
}
class Sc {
    public int sign=1;
    public long ans=0;
    private String state="start";
    private Map<String,String[]> a=new HashMap<String,String[]>(){{
        put("start",new String[]{"start","signed","in_number","end"});
        put("signed",new String[]{"end","end","in_number","end"});
        put("in_number",new String[]{"end","end","in_number","end"});
        put("end",new String[]{"end","end","end","end"});
    }};
    public void st(char c) {
        state = a.get(state)[sh(c)];
        if (state.equals("signed")) {
            if (c == '-')
                sign = -1;
            else
                sign = 1;
        } else if (state.equals("in_number")) {
            ans = ans * 10 + c - '0';
            if (sign == 1)
                ans = Math.min(ans, (long) Integer.MAX_VALUE);
            else {
                ans = Math.min(ans, -(long) Integer.MIN_VALUE);
            }
        }
    }
    private int sh(char c) {
        if(c==' '){return 0;}
        else if(c=='-'||c=='+'){return 1;}
        else if(Character.isDigit(c)){return 2;}
        else{return 3;}
    }
}
