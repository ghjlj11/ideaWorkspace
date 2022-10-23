package my_stringbuffer;

import java.util.Scanner;

/**
 * @author 86187
 */
public class Test01 {
    public static void main(String[] args){
        String s = null;
        MyStringBuffer myStringBuffer = new MyStringBuffer("fghjkloiuy");
//        StringBuffer s1 = new StringBuffer();
//        s1.append(12.22);
//        System.out.println(s1);
//        boolean f = true;
//        String s2 = f + "";
//        System.out.println(s2);
        myStringBuffer.delete(2,5);
        myStringBuffer.ensureCapacity(100);
        System.out.println(myStringBuffer);
        int x = myStringBuffer.capacity();
        int y = myStringBuffer.length();
        //myStringBuffer.append("asdf");
        myStringBuffer.reverse();
        System.out.println(x+"   " + y);
        System.out.println(myStringBuffer);
        System.out.println(myStringBuffer.indexOf("k"));
        System.out.println(myStringBuffer.lastIndexOf("k"));
        System.out.println(myStringBuffer.subString(0,0));
        System.out.println(myStringBuffer);
        myStringBuffer.replace(0,3,"hfgh");
        System.out.println(myStringBuffer);
        Scanner sc = new Scanner(System.in);
    }
}
