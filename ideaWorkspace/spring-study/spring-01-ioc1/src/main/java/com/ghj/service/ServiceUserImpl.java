package com.ghj.service;

import com.ghj.dao.User;

public class ServiceUserImpl implements ServiceUser{

    User user;


    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void getUser() {
        user.getUser();
    }
}
