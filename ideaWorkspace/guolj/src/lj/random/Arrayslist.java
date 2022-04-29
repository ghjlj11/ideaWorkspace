package lj.random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Arrayslist {
    public static void main(String[] args){
        ArrayList<String> st=new ArrayList<String>();
        LinkedList<Integer> gh=new LinkedList<Integer>();
        gh.add(23);
        gh.add(24);
        gh.add(25);
        gh.add(26);
        Iterator<Integer> s=gh.iterator();
        while (s.hasNext()){
            System.out.println(s.next());
        }
        st.add("ggg");
        st.add("hhh");
        st.add("jjj");
        st.add("lll");
        st.add("jjj");
        st.remove("ggg");
        System.out.println(st);
        Iterator<String> ii=st.iterator();
        System.out.println(ii.next());
        while (ii.hasNext()){
            System.out.println(ii.next());
        }
    }
}
