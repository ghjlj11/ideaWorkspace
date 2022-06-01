import com.ghj.mapper.UserMapper;
import com.ghj.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    ApplicationContext context ;
    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void test01(){
        UserMapper impl2 = context.getBean("userMapperImpl2", UserMapper.class);
        int k = impl2.add(new User(1, "k", 23));
        System.out.println(k);
    }
    @Test
    public void t2(){
        UserMapper userMapperImpl2 = context.getBean("userMapperImpl2", UserMapper.class);
        List<User> select = userMapperImpl2.select();
        System.out.println(select);
    }
}
