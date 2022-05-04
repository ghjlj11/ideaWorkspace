package myClass;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(s1.P);
        Class<s1> s1Class = s1.class;
        System.out.println(s1Class.getClassLoader());
        Class<? extends Class> aClass = s1.class.getClass();
        System.out.println(aClass);
    }
}
