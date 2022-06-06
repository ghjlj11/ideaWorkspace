package myTest.myReference;

import java.lang.ref.PhantomReference;

/**
 * @author 86187
 */
public class MyReference {
    public PhantomReference<String> s = new PhantomReference<>("ghj", null);

    public static void main(String[] args) {
        MyReference myReference = new MyReference();
        System.out.println(myReference.s.get());
    }
}
