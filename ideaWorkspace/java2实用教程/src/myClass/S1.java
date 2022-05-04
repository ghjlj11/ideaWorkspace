package myClass;

import javax.print.attribute.Size2DSyntax;

/**
 * @author 86187
 */
public class S1 extends s2 {
    public static final long P = 5000000000000000000L;
    public String s;
    private Integer k;
    static {
        System.out.println("初始化s1");
    }
    public S1(){}
    public S1(int k){
        this.k = k;
    }
    private S1(String s){
        this.s = s;
    }
    public void method1(int k){}
    private int method2(){
        return 2;
    }
    public static void method3(int k){}
    private static int method4(){
        return 2;
    }

    @Override
    public String toString() {
        return "S1{" +
                "s='" + s + '\'' +
                ", k=" + k +
                '}';
    }

    private class Ak{
        int k;
    }
    public class Bk{
        public Bk(){}
        int j;
    }
}
