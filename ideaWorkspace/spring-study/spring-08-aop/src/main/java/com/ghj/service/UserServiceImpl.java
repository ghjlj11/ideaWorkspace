package com.ghj.service;

/**
 * @author 86187
 */
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public String delete() {

        System.out.println("删除了一个用户");
        return "ghj and lj";
    }

    @Override
    public void update() {

        System.out.println("更新了一个用户");
    }

    @Override
    public void select() {

        System.out.println("查询了一个用户");
    }
}
