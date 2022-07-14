package com.ghj;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusStudyApplicationTests {

    //增删改查类
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

}
