package com.ghj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghj.mapper.PeopleMapper;
import com.ghj.pojo.People;
import com.ghj.service.PeopleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/25 22:34
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {
}
