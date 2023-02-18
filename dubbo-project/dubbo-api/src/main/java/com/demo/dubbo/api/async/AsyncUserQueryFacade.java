package com.demo.dubbo.api.async;

import java.util.concurrent.CompletableFuture;

public interface AsyncUserQueryFacade {

    String queryUserById(String id);

    CompletableFuture<String> queryUserByName(String name);
}
