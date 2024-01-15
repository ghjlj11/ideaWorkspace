import com.ghj.test.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/15 22:22
 */
@SpringBootTest
public class TestSpringBoot {

    @Resource
    TestMapper testMapper;

    @Test
    public void testAdd () {
        Map<String, Object> insertMap = new HashMap<>();
        insertMap.put("id", "222");
        insertMap.put("name", "222");
        insertMap.put("code", "222");
        insertMap.put("address", "222");
        insertMap.put("age", 222);
        insertMap.put("brith", new Date());
        testMapper.insert(insertMap);
    };

    @Test
    public void testUpdate () {
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("id", "222");
        updateMap.put("name", "xixi");
        testMapper.update(updateMap);
    }

    public void testDelete () {
        testMapper.delete("222");
    }
}
