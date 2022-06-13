package com.ghj.config;

import com.ghj.pojo.People;
import com.ghj.service.PeopleServiceImpl;
import com.ghj.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.HttpCookie;

/**
 * @author 86187
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    PeopleServiceImpl peopleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权===>PrincipalCollection");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取people
        Subject subject = SecurityUtils.getSubject();
        People curPeople = (People) subject.getPrincipal();
        //给用户授权
        //info.addStringPermission("user:add");
        System.out.println("curPeople===>" + curPeople);
        info.addStringPermission(curPeople.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证===>AuthenticationToken");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //验证用户名；
        People people = peopleService.selectByName(token.getUsername());
        if(people == null){
            return null;
        }

        //验证密码
        //把people传到授权的方法里面。
        System.out.println("密码===>" + people.getPwd());
        return new SimpleAuthenticationInfo(people, people.getPwd(), people.getName());
    }
}
