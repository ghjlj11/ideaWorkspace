package com.ghj.service;

import com.ghj.pojo.People;

import java.util.List;

/**
 * @author 86187
 */
public interface PeopleService {
    public People selectByName(String name);

    public List<People> selectAll();
}
