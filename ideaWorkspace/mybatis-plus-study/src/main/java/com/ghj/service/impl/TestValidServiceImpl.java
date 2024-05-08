package com.ghj.service.impl;

import com.ghj.pojo.TestValidEntity;
import com.ghj.service.PeopleService;
import com.ghj.service.TestValidService;
import com.ghj.valid.Group;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * Description:  JSR-303校验
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/26 17:41
 */
@Service
@Validated
public class TestValidServiceImpl implements TestValidService {


    @Override
    @Validated(Group.Group2.class)
    public void test( @Valid TestValidEntity validEntity) {
        System.out.println(validEntity);
    }

    @Override
    public void tet( @Valid @NotEmpty List<TestValidEntity> list) {
        System.out.println(list);
    }
}
