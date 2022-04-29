package lj.random;
import java.util.Arrays;
public class zhizhen {
    public static void main(String[] args){
        mm a=new mm();
        mm b=new mm();
        a=b;
        b.y=14;
        int x=b.y/2;
        System.out.println(a.y);
        String p="123455";
        int[] l=new int[p.length()];
        for(int i=0;i<p.length();i++){
            l[i]=Integer.parseInt(String.valueOf(p.charAt(i)));
            System.out.println(l[i]);
        }
        System.out.println(l);
        int n=123456;
        String sd=String.valueOf(n);
        int[] m=new int[sd.length()];
        for(int i=0;i<sd.length();i++){
            m[i]=Integer.parseInt(String.valueOf(sd.charAt(i)));
        }
        System.out.println(Arrays.toString(m));
        System.out.println(m);
    }
}
class mm{
    int x,y;
}