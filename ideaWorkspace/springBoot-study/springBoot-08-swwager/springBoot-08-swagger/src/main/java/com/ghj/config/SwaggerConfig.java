package com.ghj.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 86187
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Contact contact(){
        return new Contact("郭欢军","https://www.baidu.com","2367792309@qq.com");
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "郭欢军的Swagger",
                "什么东西啊",
                "2.0",
                "https://www.baidu.com",
                contact(),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    @Bean
    public Docket docket(Environment environment){

        //通过获得环境的profiles.active的值， 然后进行判断是否在定义的profiles里面获得boolean， 在判断是否开启swagger。
        Profiles of = Profiles.of("dev", "test");
        boolean b = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置是否开启
                .enable(b)
                //select下就只有这三个方法；
                .select()
                //RequestHandlerSelectors选择器，basePackage通过包扫描, withClassAnnotation通过类注解扫描，withMethodAnnotation方法注解
                .apis(RequestHandlerSelectors.basePackage("com.ghj.controller"))
                //ant就是过滤 ， 只扫描该路径下的请求
                //any就是所有的，none就是所有都不。
                .paths(PathSelectors.ant("/ghj/**"))
                .build();
    }
}
