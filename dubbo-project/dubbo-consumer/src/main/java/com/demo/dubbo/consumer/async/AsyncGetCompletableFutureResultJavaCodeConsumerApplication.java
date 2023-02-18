package com.demo.dubbo.consumer.async;

import com.demo.dubbo.api.async.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 利用 java 配置来启动
 */
public class AsyncGetCompletableFutureResultJavaCodeConsumerApplication {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://");
        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<AsyncUserQueryFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(AsyncUserQueryFacade.class);
        //真正开启异步化调用，让调用方可以通过 Future 拿到结果
        referenceConfig.setAsync(true);

        // 直接拿到引用的代理对象，然后进行远程调用
        AsyncUserQueryFacade asyncUserQueryFacade = referenceConfig.get();
        //开启异步的话拿不到返回结果
        System.out.println(asyncUserQueryFacade.queryUserById("111"));

        // 拿到一个 Future 对象， 这个 Future 对象哪里来的呢？其实是从 RpcContext 远程调用上下文中获取得到的
        System.out.println(RpcContext.getContext().getFuture().get());

        CompletableFuture<Object> completableFuture = RpcContext.getContext().getCompletableFuture();
        completableFuture.whenComplete((result, e) ->{
            if(e == null) {
                System.out.println("注定可以拿到结果：" + result);
            }
            else {
                System.out.println("非常意外走到了异常分支：" + e.getLocalizedMessage());
            }
        });

        //阻塞等待
        System.in.read();
    }
}
