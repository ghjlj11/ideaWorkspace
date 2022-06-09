import com.ghj.service.UserService;
import com.ghj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

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
        HashMap<Integer, String> map = new HashMap<>();

        Iterable<String> list = new LinkedList<>();
        for (String s1 : list) {
            
        }
        
        HashSet<String> set = new HashSet<>();
        System.out.println(bean.getClass());
        System.out.println(Arrays.toString(bean.getClass().getInterfaces()));
    }
}
