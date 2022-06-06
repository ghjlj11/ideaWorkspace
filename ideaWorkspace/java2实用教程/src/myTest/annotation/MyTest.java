package myTest.annotation;

import java.lang.reflect.Method;

/**
 * @author 86187
 */
public class MyTest {
    public static String s = "lj";
    @MyLog(value = "lj")
    public static String  getName(){
        return MyTest.s;
    }
    public static void main(String[] args) throws NoSuchMethodException {
        Class<MyTest> myTest = MyTest.class;
        Method getName = myTest.getMethod("getName");
        MyLog annotation = getName.getAnnotation(MyLog.class);
        System.out.println(annotation.value());
        if(annotation != null){
            System.out.println("你好呀MyLog");
        }
        else {
            System.out.println("出错啦");
        }
    }
}
