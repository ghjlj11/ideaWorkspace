package myClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(S1.P);
        Class<S1> s1Class = S1.class;
        System.out.println(s1Class.getClassLoader());

        System.out.println("------------------------------------------------");
        //反射获得实例对象
        Class<S1> aClass = S1.class;
        Class<String> stringClass = String.class;
        Constructor<S1> constructor = aClass.getConstructor();
        constructor.setAccessible(true);
        S1 s = constructor.newInstance();
        System.out.println(aClass);

        System.out.println("------------------------------------------------");
        //反射给对象的字段赋值
        Field[] fields = aClass.getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        Field p = aClass.getDeclaredField("s");
        p.set(s, new String("222"));
        System.out.println(s);
        Field k = aClass.getDeclaredField("k");
        k.setAccessible(true);
        k.set(s,12345);
        System.out.println(s);

        System.out.println("------------------------------------------------");
        //反射获得类的方法
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        //通过名字获得声明的方法
        Method method2 = aClass.getDeclaredMethod("method4");
        method2.setAccessible(true);
        //如果获得实例方法， 那么invoke必须传一个对应的实例， 如果类方法， 那么invoke实例随意
        Object invoke = method2.invoke(new Object());
        System.out.println(invoke);

        System.out.println("------------------------------------------------");
        //通过反射获取内部类
        Class<?>[] classes = aClass.getDeclaredClasses();

//        for (Class<?> a : classes) {
//            String name = a.getSimpleName();
//            System.out.println(name);
//            if("Bk".equals(name)){
//                Constructor<?> aConstructor = a.getConstructor(null);
//                System.out.println("000000");
//                Object o = aConstructor.newInstance(null);
//                System.out.println(o);
//            }
//        }
        System.out.println(Arrays.toString(classes));
        System.out.println(Arrays.toString(aClass.getClasses()));
        //获得所属类， 方法或者字段调用。
        Class<?> declaringClass = method2.getDeclaringClass();
        System.out.println(declaringClass);

    }
}
