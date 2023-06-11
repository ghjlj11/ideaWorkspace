package cpm.ghj.provider.service.impl;

import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 * 服务提供者，接口实现类
 * DubboService 注解与之前xml配置类似，也有版本号，接口，超时时间，重试次数等
 */
@DubboService(interfaceClass = UserProviderService.class)
public class UserProviderServiceImpl implements UserProviderService {
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(888L, 8, 88, "boot-dubbo"));
        return list;
    }
}
