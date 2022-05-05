package com.ghj;

import com.ghj.controller.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootTest
class SpringBoot06SecurityApplicationTests {

    @Test
    void contextLoads() {

        //通过反射获得注解， 然后获得注解里的属性
        Class<MyController> aClass = MyController.class;
        RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
        String[] strings = annotation.value();
        System.out.println(Arrays.toString(strings));

        //反射获得方法， 方法上的注解， 注解里的属性。
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println(methods.length);
        for (Method method : methods) {
            RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
            String[] path = annotation1.value();
            System.out.println(Arrays.toString(path));
        }
    }

}
