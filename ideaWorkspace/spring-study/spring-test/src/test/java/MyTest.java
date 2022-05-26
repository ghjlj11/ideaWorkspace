import com.ghj.pojo.Computer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

public class MyTest {
    @Test
    public void t1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer);
    }
}
