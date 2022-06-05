package myTest.father;

import java.lang.reflect.Field;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "hello";
        System.out.println(s);
        Class ac = s.getClass();
        Field value = ac.getDeclaredField("value");
        value.setAccessible(true);
        char[] ch = (char[]) value.get(s);
        ch[2] = 'w';
        System.out.println(s);
    }
}
