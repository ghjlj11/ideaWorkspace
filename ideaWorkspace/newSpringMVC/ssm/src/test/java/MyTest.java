import com.ghj.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(context);
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        System.out.println(userMapper.selectById(2));
    }
}
