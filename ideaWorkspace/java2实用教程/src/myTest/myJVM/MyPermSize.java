package myTest.myJVM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 86187
 * JDK8以后就完全没有了永久代，所以没有PermSize的配置了
 * 可以用元空间有关的配置
 */
public class MyPermSize {
    public static void main(String[] args) {
        Set<String> list = new HashSet<>();
        int i = 0 ;
        while (true){
            list.add(String.valueOf(i ++).intern());
        }
    }
}
