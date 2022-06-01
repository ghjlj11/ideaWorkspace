import com.ghj.dao.UserDao;
import com.ghj.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test01 {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-JDBC.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        System.out.println(userDao);
        JdbcTemplate jdbcTemplate = userDao.getJdbcTemplate();
        System.out.println(jdbcTemplate);
        User user = userDao.selectById(2);
        System.out.println(user);
    }
}
