import com.alibaba.fastjson.JSON;
import com.ghj.pojo.Books;
import com.ghj.service.BooksService;
import com.ghj.service.BooksServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Test01 {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BooksService impl = context.getBean("bookServiceImpl", BooksServiceImpl.class);
        List<Books> books = impl.selectAll();
        // System.out.println(books);
        Books books1 = new Books(1111, "1111", 1111, "1111");
        String s = JSON.toJSONString(books1);
        Books books2 = JSON.parseObject(s, Books.class);
    }
}
