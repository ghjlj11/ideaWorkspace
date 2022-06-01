import com.ghj.service.UserService;
import com.ghj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("factoryBean.xml");
        UserService bean = context.getBean("proxy", UserService.class);
        String s = bean.delete();
        if(bean instanceof List<?> l){
            System.out.println("是属于List");
        }
        if(bean instanceof ArrayList<?>){
            System.out.println("是属于ArrayList");
        }
        System.out.println(bean.getClass());
        System.out.println(Arrays.toString(bean.getClass().getInterfaces()));
    }
}
