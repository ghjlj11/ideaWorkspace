package com.ghj.blog.com.ghj.service.impl;

import com.ghj.entity.Blog;
import com.ghj.mapper.BlogMapper;
import com.ghj.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ghj
 * @since 2022-07-17
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
