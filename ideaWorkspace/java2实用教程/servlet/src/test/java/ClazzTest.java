import com.ghj.dao.ClazzDao;
import com.ghj.pojo.Clazz;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ClazzTest {
    ClazzDao clazzDao = new ClazzDao();
    
    @Test
    public void te1() throws SQLException     {
        Clazz clazz = clazzDao.selectById(5);
        System.out.println(clazz);
    }
    @Test
    public void te2() throws SQLException     {
        List<Clazz> list = clazzDao.selectAll();
        System.out.println(list);
    }
    @Test
    public void te3() throws SQLException     {
        int kk = clazzDao.add(new Clazz(5, "kk", "876t5re"));
        System.out.println(kk);
    }
    @Test
    public void t4() throws SQLException     {
        int ghjkk = clazzDao.update(new Clazz(5, "ghjkk", "1234"));
        System.out.println(ghjkk);
    }
    @Test
    public void t5() throws SQLException     {
        int delete = clazzDao.delete(5);
        System.out.println(delete);
    }
}
