package test;

/**
 * @author 86187
 */
public class Test2 {
    public static void main(String[] args) {
        int k = 5;
        Test2 t = new Test2();
        System.out.println(t.sd(k));
    }
    private long sd(int k){
        return k == 0 ? 1 : k * sd(k - 1);
    }
}
