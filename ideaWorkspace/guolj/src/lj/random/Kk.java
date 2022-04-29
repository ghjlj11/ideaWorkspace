package lj.random;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @author 86187
 */
public class Kk {
    public static void main(String[] args) throws ParseException {
        List<HashSet<Integer>> st = new ArrayList<HashSet<Integer>>();
        StringBuilder s = new StringBuilder();
        String s1 = "1,2,3,4,5,6,7,8";
        String s2 = "1,2,3,4,5,6,7,8";
        System.out.println(s1 == s2);
        s.append(s1);
        s.append(s2);
        char[] k = new char[s1.length()];
        k=s1.toCharArray();
        System.out.println(s);
        Deque<Integer> c = new LinkedList<Integer>();
        List<Integer> stack = new Stack<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        Integer[] sa ={5,2,3,4,1,0};
        int[] nm ={1,2,3,4,5};
        Arrays.sort(sa, 1, 4, Collections.reverseOrder());
        System.out.println(Arrays.toString(sa));
        int[] sx = {1,2,3,4,5};
        int[] dc = {1,2,3,4,5};
        System.out.println(sx == dc);
        Integer v = 125;
        Integer b = 125;
        System.out.println(v == b);
        Date date = new Date();
        StringBuffer ss =new StringBuffer ("1234567");
        StringBuffer ss1 = new StringBuffer();
        ss1 = ss;
        ss.insert(3,"as");
        System.out.println(ss);
        Deque<Integer> deque = new LinkedList<Integer>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        System.out.println(deque);
        System.out.println(deque.pop());
        System.out.println(deque);
        System.out.println(deque.poll());
        System.out.println(deque);
        System.out.println(deque.peek());
        System.out.println(deque);
        int xx = (int) 'a';
        short hj = 34;
        Integer kl = (int)hj;
        System.out.println(ss == ss1);
        Short kk = 211;
        Short tt = 211;
        System.out.println(kk == tt);
        RoundingMode roundingMode = RoundingMode.DOWN;
        NumberFormat numberFormat = new DecimalFormat("#.0000");
        numberFormat.setRoundingMode(RoundingMode.DOWN);
        double dd = 3.1415926;
        String su = numberFormat.format(dd);
        Number df =  numberFormat.parse(su);
        System.out.println(su);
        System.out.println(df instanceof Double);
    }
    static void j(int x){--x;}
}
