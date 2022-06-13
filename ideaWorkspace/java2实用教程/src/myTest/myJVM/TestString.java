package myTest.myJVM;

/**
 * @author 86187
 */
public class TestString {
    public static void main(String[] args) {
        String s = new String("1");
        while (true){
            System.out.println(s.length());
            s += s;
        }
    }
}
