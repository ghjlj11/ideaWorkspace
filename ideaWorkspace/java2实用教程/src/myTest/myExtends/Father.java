package myTest.myExtends;

import java.lang.ref.SoftReference;

/**
 * @author 86187
 */
class Father {
    private static String name = "mm";
    public final int x = 2;
    public SoftReference<Integer> s = new SoftReference<Integer>(3);
    public Father(){
        name = "lj";
    }
    public static void show(){
        System.out.println("ll");
    }
    public String getName(){
        return name;
    }
    public void setName(String name){

    }
}
