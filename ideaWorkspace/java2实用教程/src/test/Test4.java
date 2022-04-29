package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 86187
 */
public class Test4 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>() ;
        list.add("张三丰");
        list.add( "殷天正" );
        list.add( "张翠山" );
        list.add( "殷素素" );
        list.add( "张无忌" );
        list.add( "周芷若" );
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("=========================");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("=========================");
        Consumer<String> consumer = System.out::println;
        list.forEach(consumer);
    }
}
