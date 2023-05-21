package com.ghj.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 86187
 * 第二种路由匹配的方式， 通过config文件
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        /**
         * 相当于访问/baidu 请求的时候， 就会跳转到https://www.baidu.com/
         */
        routes.route("path_route",
                r->r.path("/baidu")
                        .uri("https://www.baidu.com/"));

        return routes.build();

    }
}
