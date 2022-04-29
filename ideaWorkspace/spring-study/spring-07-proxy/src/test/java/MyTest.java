import com.ghj.demo01.ProxyInvocationHandler;
import com.ghj.demo01.UserService;
import com.ghj.demo01.UserServiceImp;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class MyTest {
    @Test
    public void test01(){
        UserService userService = new UserServiceImp();
        ProxyInvocationHandler phi = new ProxyInvocationHandler();
        phi.setTarget(userService);
        UserService proxy = (UserService) phi.getProxy();
        proxy.add();
        proxy.delete();
        proxy.select();
    }
}
