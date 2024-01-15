package com.ghj.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/15 21:15
 */
@Mapper
@Repository
public interface TestMapper {

    /**
     * insert
     * @param params
     */
    void insert (Map<String, Object> params);

    /**
     * update
     * @param params
     * @return
     */
    int update (Map<String, Object> params);

    /**
     * delete
     * @param id
     * @return
     */
    int delete (String id);

    /**
     * getAll
     * @return
     */
    List<Map<String, Object>> getAll ();

    /**
     * getById
     * @param id
     * @return
     */
    Map<String, Object> getById (String id);
}
