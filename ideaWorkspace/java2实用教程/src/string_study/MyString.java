package string_study;

import java.util.*;

/**
 * @author 86187
 */
public class MyString {
    public static void main(String[] args) {
        String s = "abc";
        String s1 = "abc";
        String s2 = "ab";
        String s3 = s2 + "c";
        String s4 = s2.intern() + "c";
        String s5 = s2.concat("c");
        s5 = s5.toUpperCase();
        char[] chars = s5.toCharArray();
        System.out.println(Arrays.toString(chars));
        System.out.println(s3 == s5);
        byte[] bytes = s5.getBytes();
        //Arrays.sort(bytes, 1, 2, Collections.reverseOrder());
        System.out.println(Arrays.toString(bytes));
        String s6 = "🚗 🐕 🐖 🐅 🦁 🐘 ";
        s6 = s6.toUpperCase(Locale.TRADITIONAL_CHINESE);
        System.out.println(s6);
        Properties p  = new Properties();
    }
}
