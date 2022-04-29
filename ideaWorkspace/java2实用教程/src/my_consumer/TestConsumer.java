package my_consumer;

import java.util.function.Consumer;

/**
 * @author 86187
 */
public class TestConsumer {
    public static  void main(String[] args){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        String s ="12345";
        consumer.accept(s);
        consumer = s1 -> System.out.println(s1);
        consumer.accept("ertyui");
        consumer = System.out :: println;
        consumer.accept("3456789");
    }
}
