import com.ghj.service.UserService;
import com.ghj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("factoryBean.xml");
        UserService bean = context.getBean("proxy", UserService.class);
        String s = bean.delete();

    }
}
