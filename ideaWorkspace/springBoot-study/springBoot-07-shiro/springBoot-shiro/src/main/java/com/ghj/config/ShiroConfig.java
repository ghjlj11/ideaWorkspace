package com.ghj.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 86187
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getSecurityManager") DefaultWebSecurityManager de){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(de);
        /**
         * anno : 无需认证就可访问；
         * authc： 必须认证才可以访问；
         * user： 必须拥有记住我才可以访问
         * perms： 拥有对某个资源权限才可以访问
         * role：拥有某个角色权限才可以访问
         *
         */

        //拦截设置
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        System.out.println(filterMap);

        //设置登录页面
        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/unauthorized");

        return bean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager de = new DefaultWebSecurityManager();
        de.setRealm(userRealm);
        return de;
    }

    /**
     * 创建realm；
     * @return
     */
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    /**
     * 使用ShiroDialect 整合 shiro 与 thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
