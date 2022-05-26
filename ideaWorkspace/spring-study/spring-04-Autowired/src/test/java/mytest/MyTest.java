package mytest;

import com.ghj.pojo.Dog;
import com.ghj.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people", People.class);
        System.out.println(people.getCat());
        System.out.println(people.getDog());
        people.getDog().shout();
        people.getCat().shout();
    }
}
