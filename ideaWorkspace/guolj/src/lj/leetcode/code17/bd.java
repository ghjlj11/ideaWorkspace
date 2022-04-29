package lj.leetcode.code17;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bd {
    public Map<Character,String> m=new HashMap<Character,String>(){{
        put('2',"abc");
        put('3',"def");
        put('4',"ghi");
        put('5',"jkl");
        put('6',"mno");
        put('7',"pqrs");
        put('8',"tuv");
        put('9',"wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        List<String> t=new ArrayList<String>();
        StringBuffer b=new StringBuffer();
        int index=0;
        if(index==digits.length()){
            return t;
        }
        abc(digits,t,b,index);
        return t;
    }
    public void abc(String digits,List<String> t,StringBuffer b,int index){
        if(index==digits.length()){
            t.add(b.toString());
        }
        else {
            String o = m.get(digits.charAt(index));
            int n = o.length();
            for (int i = 0; i < n; i++) {
                b.append(o.charAt(i));
                abc(digits, t, b, index+1);
                b.deleteCharAt(index);
            }
        }
    }
}
