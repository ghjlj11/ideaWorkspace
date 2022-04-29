import com.ghj.mapper.UserMapper;
import com.ghj.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper impl2 = context.getBean("userMapperImpl2", UserMapper.class);
        List<User> users = impl2.select();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
