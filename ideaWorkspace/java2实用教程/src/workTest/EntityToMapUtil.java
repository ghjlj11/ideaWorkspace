package workTest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guohuanjun
 * @create 2023-1-10 16:51
 */
public class EntityToMapUtil {
    public static Map<String, Object> toMap(Object entity) throws Exception {
        Map<String, Object> res = new HashMap<>(16);
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            Object value = field.get(entity);
            res.put(key, value);
        }
        return res;
    }
}
