package myTest.myExtends;

/**
 * @author 86187
 */
public interface Grand {
    public static final int n = 0;

    public static void mm() {
        System.out.println("jj");
    }
    public void kk();
    private int ll(int k){
        return k;
    }
    public default void ll(){
        System.out.println("ll");
    }
}
