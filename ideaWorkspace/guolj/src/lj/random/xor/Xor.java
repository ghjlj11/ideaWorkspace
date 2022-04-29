package lj.random.xor;

/**
 * @author 86187
 */
public class Xor {
    public static void main(String[] args){
        int a = 4;
        int b = 6;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
