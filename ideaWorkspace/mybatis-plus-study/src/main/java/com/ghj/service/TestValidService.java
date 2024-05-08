package com.ghj.service;

import com.ghj.pojo.TestValidEntity;
import com.ghj.valid.Group;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/26 17:39
 */
public interface TestValidService {

    /**
     * test
     * @param validEntity
     */
    void test(@Valid TestValidEntity validEntity);

    /**
     * tet
     * @param list
     */
    void tet(@Valid @NotEmpty List<TestValidEntity> list);
}
