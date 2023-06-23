package com.ghj;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghj.mapper.UserMapper;
import com.ghj.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class MybatisPlusStudyApplicationTests {

    /**
     * 增删改查类
     */
    @Autowired
    UserMapper userMapper;

    IService<User> service = new ServiceImpl();


    /**
     * 测试select， 当参数为null， 则是查询所有的数据
     */
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);

        System.out.println("结果输出!");
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 测试insert
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("ghj");
        user.setAge(21);
        user.setEmail("123456");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /**
     * 测试update
     */
    @Test
    public void update(){
        User user = new User();
        user.setId(1546474832920682500L);
        user.setName("ss");
        user.setAge(21);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 测试乐观锁
     */

    @Test
    public void optimistic(){

        User user = new User();
        user.setId(1111L);
        user.setName("郭欢军");
        User user1 = userMapper.selectById(1111L);
        user.setVersion(user1.getVersion());
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    /**
     * 查询语句selectBatchIds(),通过List集合存放id查询
     */

    @Test
    public void select(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
        User user = userMapper.selectById(1L);
        System.out.println(user);

    }

    /**
     *查询语句， 通过map封装查询条件查询。
     */

    @Test
    public void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "ghj");
//        map.put("id", "1111");
//        map.put("age", "21");
//        map.put("email", "123456");

        List<User> list = userMapper.selectByMap(map);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 测试分页插件selectPage(Page, null)
     */
    @Test
    public void testPage(){

        //首先需要new 一个page ， 第一个参数是第几页， 第二个参数是页面的大小。
        Page<User> page = new Page<>(2, 3);

        //通过page查询。
        userMapper.selectPage(page, null);


        List<User> users = page.getRecords();

        users.forEach(System.out::println);

    }


    /**
     * 删除测试deleteById()，根据指定的id删除, 参数也可以是一个实体类， 根据实体类的id删除。
     */

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(1546474832920682502L);

        user.setName("ll");
        int i = userMapper.deleteById(user);
//        int i = userMapper.deleteById(1546474832920682501L);
        System.out.println(i);
    }

    /**
     * 通过map来删除，里面存放的东西就是查询条件
     */
    @Test
    public void testDelete2(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("id", 1546474832920682499L);
        map.put("name", "ghj");
        map.put("age", "21");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }


    /**
     * 通过Collection来存放id， 然后全部删除。
     */
    @Test
    public void testDelete3(){

        ArrayList<Long> longs = new ArrayList<>();

        longs.add(1546474832920682497L);
        longs.add(4321L);
        int i = userMapper.deleteBatchIds(longs);

        System.out.println(i);
    }

    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogic(){
        int i = userMapper.deleteById(1546474832920682500L);
        System.out.println(i);
    }

    /**
     * 测试条件构造器 wrapper
     */
    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //链式语法， eq等于，column与对应的值匹配，ge大于。
        wrapper
                .eq("id", 2)
                .ge("age", 12)
                .eq("name", "jack");

        List<Object> objects = userMapper.selectObjs(wrapper);

        System.out.println(objects);
    }

    /**
     * 测试条件构造器 wrapper
     */
    @Test
    public void testWrapper2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 12, 21);
        //查询一共有多少个数据在这些范围内
        Long count = userMapper.selectCount(wrapper);

        //查询并返回所有的符合条件的数据
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(count);

    }

    /**
     * 模糊查询like
     */
    @Test
    public void testWrapper3(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "j");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);

        System.out.println(maps);
    }

    /**
     * 子查询inSql()
     */
    @Test
    public void testWrapper4(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 3");

        List<Object> list = userMapper.selectObjs(wrapper);

        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testLocalDateTime(){
        User user = new User();
        user.setLocalDateTime(LocalDateTime.now());
        user.setEmail("aaa@qq.com");
        user.setName("haha");
        user.setAge(22);
        int insert = userMapper.insert(user);
        System.out.println(insert);

    }
}
