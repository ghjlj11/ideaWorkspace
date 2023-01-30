package workTest;

import java.util.Map;
import java.util.UUID;

/**
 * @author 86187
 */
public class TestEntityToMap {
    public static void main(String[] args) throws Exception {
        T1 t1 = new T1();
        t1.setA(3);
        t1.setS("222");
        Map<String, Object> map = EntityToMapUtil.toMap(t1);
        System.out.println(map);
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}
