import com.ghj.pojo.Computer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

public class MyTest {
    @Test
    public void t1(){
        System.out.println("-------------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println("-------------------");
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println("-------------------");
        System.out.println(computer);
        context.close();
    }
}
