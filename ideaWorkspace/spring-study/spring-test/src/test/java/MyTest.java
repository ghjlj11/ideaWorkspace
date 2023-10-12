import com.ghj.test.TestSelfInject;
import com.ghj.pojo.Computer;
import com.ghj.pojo.Test01;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void t1(){
        System.out.println("-------------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println("-------------------");
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println("-------------------");
        System.out.println(computer);
        TestSelfInject bean = context.getBean(TestSelfInject.class);
        bean.test();
        context.close();
    }
    @Test
    public void t2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        Test01 test01 = applicationContext.getBean("test01", Test01.class);
        System.out.println(test01);
        applicationContext.close();
    }
}
