package com.ghj;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ghj.mapper.TestTableMapper;
import com.ghj.pojo.People;
import com.ghj.pojo.Sex;
import com.ghj.pojo.TestTable01;
import com.ghj.pojo.TestValidEntity;
import com.ghj.service.PeopleService;
import com.ghj.service.TestTableService;
import com.ghj.service.TestValidService;
import com.ghj.utils.ThreadPollExecutorUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/24 23:45
 */
@SpringBootTest
public class TestTable {

    @Resource
    TestTableMapper testTableMapper;

    @Resource
    TestTableService testTableService;

    @Resource
    PeopleService peopleService;

    @Resource
    TestValidService validService;

    @Test
    public void test01(){
        QueryWrapper<TestTable01> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", "1", "2");
        List<TestTable01> selectList = testTableMapper.selectList(queryWrapper);
        System.out.println("query:" + selectList);
    }

    @Test
    public void test02(){
        TestTable01 testTable01 = new TestTable01();
        testTable01.setName("gg");
        List<TestTable01> testTable01List = Collections.singletonList(testTable01);
        boolean saveBatch = testTableService.saveBatch(testTable01List);
        System.out.println("query:" + saveBatch);
    }

    @Test
    public void testPeople() throws ExecutionException, InterruptedException {
        // 地址
        String[] address = {"shanghai", "beijing", "nanjing", "nanchang", "guangzhou", "hangzhou", "wuhan", "changsha", "xian"};
        // 生日
        LocalDateTime[] births = {LocalDateTime.parse("2001-02-23T12:23:34"), LocalDateTime.parse("2000-01-26T18:53:44"), LocalDateTime.parse("2002-11-12T06:09:13")};
        // 名字
        String[] names = {"jams", "jenny", "tom", "jack", "mike", "tonny", "flank"};
        // 性别
        Sex[] sexes = {Sex.MALE, Sex.FEMALE, Sex.OTHER};
        //体重
        int minWeight = 110, maxWeight = 181;
        // 身高
        int minHeight = 166, maxHeight = 191;

        int sizes = 4000000;
        List<People> peopleList = new ArrayList<>(sizes);

        Random random = new Random();
        int[] addressIndexes = random.ints(sizes, 0, address.length).toArray();
        int[] birthIndexes = random.ints(sizes, 0, births.length).toArray();
        int[] nameIndexes = random.ints(sizes, 0, names.length).toArray();
        int[] sexIndexes = random.ints(sizes, 0, sexes.length).toArray();
        int[] weight = random.ints(sizes, minWeight, maxWeight).toArray();
        int[] height = random.ints(sizes, minHeight, maxHeight).toArray();

        for (int i = 0; i < sizes; i++) {
            People people = new People().setName(names[nameIndexes[i]]).setSex(sexes[sexIndexes[i]]).setBrith(births[birthIndexes[i]])
                    .setAddress(address[addressIndexes[i]]).setWeight(new BigDecimal(weight[i])).setHeight(new BigDecimal(height[i]));
            peopleList.add(people);
        }

        CompletableFuture[] tasks = new CompletableFuture[10];

        for (int i = 0; i < 10; i++) {
            List<People> subList = peopleList.subList(i * 400000, (i + 1) * 400000);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < 100; j++) {
                    List<People> peoples = subList.subList(j * 4000, (j + 1) * 4000);
                    System.out.println("result:" + peopleService.saveBatch(peoples));
                }
            }, ThreadPollExecutorUtil.getThreadPoll());
            tasks[i] = future;
        }
        CompletableFuture.allOf(tasks).get();
    }

    @Test
    public void testValidService(){

        TestValidEntity testValid = new TestValidEntity().setName("").setAge(55);

        validService.test(testValid);

        System.out.println("ss");

    }
    @Test
    public void testValid(){

        List<TestValidEntity> list = new ArrayList<>();
        TestValidEntity entity = new TestValidEntity().setAge(100);
        //list.add(entity);
        validService.tet(list);

        System.out.println("ss");

    }

}
