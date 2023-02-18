package com.demo.dubbo.provider.impl;

import com.demo.dubbo.api.UserUpdateFacade;


public class UserUpdateFacadeImpl implements UserUpdateFacade {

    public String queryUser(String name) {
        return "hello " + name + ", my name is bob";
    }

    @Override
    public String updateUserById(String id, String name) {
        return "id=" + id + ", name=" + name +", change to newName=tom";
    }
}
