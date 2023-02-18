package com.demo.dubbo.consumer.async;

import com.demo.dubbo.api.async.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * 利用 java 配置来启动
 */
public class AsyncCompletableFutureJavaCodeConsumerApplication {

    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://");

        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<AsyncUserQueryFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(AsyncUserQueryFacade.class);
        referenceConfig.setTimeout(1000);

        // 直接拿到引用的代理对象，然后进行远程调用
        AsyncUserQueryFacade asyncUserQueryFacade = referenceConfig.get();
        CompletableFuture<String> completableFuture = asyncUserQueryFacade.queryUserByName("tom");
        completableFuture.whenComplete((value, e) -> {
            if(e != null) {
                e.printStackTrace();
            }
            else {
                System.out.println(value);
            }
        });

        //阻塞等待
        System.in.read();
    }
}
