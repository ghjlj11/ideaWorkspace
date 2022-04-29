package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author 86187
 */
public class Test3 {
    public static void main(String[] args){
        Map<String,Integer> map = new HashMap<>();
        map.put( "张三丰" , 108 );
        map.put( "殷天正" , 79 );
        map.put( "张翠山" , 36 );
        map.put( "殷素素" , 30 );
        map.put( "张无忌" , 8 );
        map.put( "周芷若" , 7 );
        System.out.println(map);
        BiConsumer<String, Integer> biConsumer = new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + "=" + integer);
            }
        } ;
        map.forEach(biConsumer);
    }
}
