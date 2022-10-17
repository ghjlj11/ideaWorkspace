package com.ghj.dao;

import com.ghj.pojo.Blog;
import com.ghj.utils.IDUtils;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class TestBlog {
    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.getID());
        blog.setTitle("如何养鸡");
        blog.setAuthor("欧阳斐");
        blog.setCreateTime(new Date(System.currentTimeMillis() - 300));
        blog.setViews(4);
        mapper.insertBlog(blog);


        blog.setId(IDUtils.getID());
        blog.setTitle("如何养鸭");
        blog.setCreateTime(new Date(System.currentTimeMillis() - 900));
        mapper.insertBlog(blog);

        blog.setId(IDUtils.getID());
        blog.setTitle("如何养牛");
        blog.setCreateTime(new Date(System.currentTimeMillis() - 1500));
        mapper.insertBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02 (){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        //blog.setAuthor("欧阳斐");
        blog.setViews(2);
        List<Blog> blogs = mapper.selectBlogIf(blog);
        for ( Blog blog1 : blogs) {
            System.out.println(blog1);
        }
        sqlSession.close();
    }

    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        //map.put("author", "欧阳斐");
        //map.put("title", "如何养鸡");
        map.put("views", 2);
        List<Blog> blogs = mapper.selectBlogChoose(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void test04(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("author", "邹龙");
        map.put("title", "抓水母");
        map.put("views",10);
        map.put("id","799531b5cd2149fba890afdf68b7fca3");
        mapper.updateBlog(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test05(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        map.put("ids",ids);

        List<Blog> blogs = mapper.selectBlogForeach(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        /**
         * 一级缓存 , 默认开启一级缓存，只在一次sqlSession中有效，从开始到close
         * 执行第二相同的select操作时，不用再从数据库中查找，直接从缓存中
         * 并且blogs2 == blogs 地址内存都一样；
         * 当执行update、insert、delete语句时刷新缓存；
         * 默认采用LRU算法缓存；
         * 可以手动清空缓存；
         */
        System.out.println("-----------------------------------");
        List<Blog> blogs2 = mapper.selectBlogForeach(map);

        for (Blog blog : blogs2) {
            System.out.println(blog);
        }
        System.out.println(blogs == blogs2);
        sqlSession.close();

        /**
         * 开启二级缓存，可以缓存sqlSession关闭之前的内容；
         * 下次指定时间内开启会话查询相同的内容时则从缓存中查找；
         * 二级缓存只在一个namespace中有效，即在一个xml中有效；
         * 如果只写一个cache标签不带参数则要考虑序列化类；
         */
        System.out.println("-----------------------------------");
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        List<Blog> blogs1 = mapper1.selectBlogForeach(map);

        for (Blog blog : blogs1) {
            System.out.println(blog);
        }
        sqlSession1.close();
    }
    @Test
    public void test06(){
        List<Map<String, Object>> l1 = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        l1.add(map);
        System.out.println(l1);
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.selectInId(l1);
        System.out.println(blogs);

    }
}
