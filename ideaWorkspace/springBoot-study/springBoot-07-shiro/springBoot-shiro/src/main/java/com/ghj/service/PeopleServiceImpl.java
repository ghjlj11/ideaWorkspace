package com.ghj.service;

import com.ghj.mapper.PeopleMapper;
import com.ghj.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */

@Service
public class PeopleServiceImpl implements PeopleService{

    @Autowired
    PeopleMapper peopleMapper;
    @Override
    public People selectByName(String name) {
        return peopleMapper.selectByName(name);
    }

    @Override
    public List<People> selectAll() {
        return peopleMapper.selectAll();
    }
}
