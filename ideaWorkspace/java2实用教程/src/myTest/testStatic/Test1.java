package myTest.testStatic;

import static myTest.testStatic.StaticClass.mm;

/**
 * @author guohuanjun1
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 t = new Test1();
        t.h1();
    }

    public void h1() {
        mm();
    }
}
