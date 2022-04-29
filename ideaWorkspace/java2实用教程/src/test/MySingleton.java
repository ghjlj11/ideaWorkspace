package test;

/**
 * @author 86187
 */
public class MySingleton {
    private static MySingleton singleton = new MySingleton();
    private MySingleton() {
    }
    public static MySingleton getInstance(){
        return singleton;
    }
}
