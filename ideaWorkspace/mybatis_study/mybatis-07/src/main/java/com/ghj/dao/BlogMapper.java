package com.ghj.dao;

import com.ghj.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public interface BlogMapper {
    void insertBlog(Blog blog);
    List<Blog> selectBlogIf(Blog blog);
    List<Blog> selectBlogChoose(Map<String,String> map);
    void updateBlog(Map map);

    List<Blog> selectBlogForeach(Map map);
}
