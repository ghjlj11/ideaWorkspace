package myTest;

import com.ghj.pojo.User;
import com.ghj.service.Test02;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);
        User user1 = context.getBean("user", User.class);
        System.out.println(user.name);
        System.out.println(user == user1);
    }

    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Test02 test02 = context.getBean("test02", Test02.class);
        System.out.println(test02);
    }
}
