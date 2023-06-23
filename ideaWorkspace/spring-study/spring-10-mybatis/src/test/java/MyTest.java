import com.ghj.mapper.UserMapper;
import com.ghj.mapper.UserMapperImpl;
import com.ghj.mapper.UserMapperImpl2;
import com.ghj.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

public class MyTest {
    @Test
    public void test01() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper impl = context.getBean("userMapperImpl2", UserMapper.class);
        User user1 = new User();
        user1.setId(11223344L);
        user1.setName("llk");
        user1.setAge(23);
        user1.setLocal_date_time(LocalDateTime.now());

        int insert = impl.insert(user1);
        System.out.println("insert===>:" + insert);

        List<User> users = impl.select();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
