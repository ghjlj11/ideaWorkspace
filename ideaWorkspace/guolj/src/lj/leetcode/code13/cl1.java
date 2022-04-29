package lj.leetcode.code13;
import java.util.HashMap;
import java.util.Map;

public class cl1 {
    /*HashMap<Character,Integer> ar=new HashMap<Character,Integer>(){{
        put('M',1000);
        put('D',500);
        put('C',100);
        put('L',50);
        put('X',10);
        put('V',5);
        put('I',1);
    }};*/
    public int romanToInt(String s) {
        Map<Character,Integer> ar=new HashMap<Character,Integer>();
        ar.put('M',1000);
        ar.put('D',500);
        ar.put('C',100);
        ar.put('L',50);
        ar.put('X',10);
        ar.put('V',5);
        ar.put('I',1);
        int sum=0;
        for(int i=0;i<s.length();i++){
            int x=ar.get(s.charAt(i));
            if(i<s.length()-1&&x<ar.get(s.charAt(i+1))){
                sum-=x;
            }
            else{
                sum+=x;
            }
        }
        return sum;
    }
}
