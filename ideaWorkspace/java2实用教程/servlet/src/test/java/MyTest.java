import com.ghj.dao.MyDao;
import com.ghj.pojo.Clazz;
import com.ghj.pojo.Student;
import com.ghj.service.StudentService;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MyTest {
    MyDao myDao = new MyDao();
    StudentService s = new StudentService();
    
    @Test
    public void test() throws SQLException    {
        List<Student> list = myDao.selectAll();
        System.out.println(list);
    }
    @Test
    public void test2() throws SQLException    {
        Student s= new Student(9, "阿甘", "1234", "7654321","123456", 3, new Timestamp(System.currentTimeMillis()));
        int add = myDao.add(s);
        System.out.println(add);
    }
    @Test
    public void test3() throws SQLException    {
        Student student = myDao.selectById(2);
        System.out.println(student);
    }
    @Test
    public void test4() throws SQLException    {
        Student s= new Student(9, "阿A", "1234", "7654321","123456", 3, new Timestamp(System.currentTimeMillis()));
        int update = myDao.update(s);
        System.out.println(update);
    }
    @Test
    public void te5() throws SQLException    {
        int delete = myDao.delete(9);
        System.out.println(delete);
    }
}
