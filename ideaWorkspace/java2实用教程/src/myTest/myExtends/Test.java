package myTest.myExtends;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author 86187
 */
public class Test {
    public static void main(String[] args) throws NoSuchFieldException {
        Son s = new Son();
        String name1 = s.getName();
        System.out.println(name1);
        s.setName("kk");
        System.out.println(s.x);
        System.out.println(s.getName());
        System.out.println(s.s.get());
    }
}
