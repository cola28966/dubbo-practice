package com.demo.dubbo.provider.impl;

import com.demo.dubbo.api.UserQueryFacade;

public class UserQueryFacadeImpl implements UserQueryFacade {
    @Override
    public String queryUser(String name) {
        return "hello " + name + ", my name is bob";
    }
}
