import com.ghj.config.GhjConfig;
import com.ghj.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        //如果完全使用配置类，我们就只能通过ApplicationConfig上下文来获取容器；
        ApplicationContext context = new AnnotationConfigApplicationContext(GhjConfig.class);
        User user  = context.getBean("getUser", User.class);
        System.out.println(user);
    }
}
