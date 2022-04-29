package lj.leetcode.code8;

import java.util.HashSet;
import java.util.Set;
public class Ba {
    Set<Character> k=new HashSet<Character>(){{
        add('1');
        add('2');
        add('3');
        add('4');
        add('5');
        add('6');
        add('7');
        add('8');
        add('9');
        add('0');
    }};
    public int myAtoi(String s) {
        long res;
        int i=-1,m=-1;
        StringBuffer sb=new StringBuffer();
        if(s.length()<1){return 0;}
        while (i<s.length()-1&&(s.charAt(i+1)==' ')){
            i++;
        }
        if (i<s.length()-1&&(s.charAt(i+1) == '-' || s.charAt(i+1) == '+')) {
            sb.append(s.charAt(i+1));
            i++;
            m++;
        }
        if(i<s.length()-1&&!k.contains(s.charAt(i+1))){return 0;}
        while (i<s.length()-1&&k.contains(s.charAt(i+1))){
            sb.append(s.charAt(i+1));
            i++;
        }
        while (m<sb.length()-1&&sb.charAt(m+1)=='0'){sb=sb.deleteCharAt(m+1);}
        if((sb.length()==1&&(sb.charAt(0)=='-'||sb.charAt(0)=='+'))||sb.length()<1){return 0;}
        if(sb.charAt(0)=='-'&&sb.length()>11){return Integer.MIN_VALUE;}
        else if((sb.charAt(0)=='+'&&sb.length()>11)||(k.contains(sb.charAt(0))&&sb.length()>10)){return Integer.MAX_VALUE;}
        res=Long.valueOf(String.valueOf(sb));
        if(res<Integer.MIN_VALUE){return Integer.MIN_VALUE;}
        if(res>Integer.MAX_VALUE){return Integer.MAX_VALUE;}
        return Integer.parseInt(String.valueOf(res));
    }
}
