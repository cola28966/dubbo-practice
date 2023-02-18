package com.demo.dubbo.consumer.sync;

import com.demo.dubbo.api.UserUpdateFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用 java 配置来启动
 */
public class JavaCodeConsumerApplication {

    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://47.116.142.117:2181");

        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<UserUpdateFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserUpdateFacade.class);

        // 直接拿到引用的代理对象，然后进行远程调用
        UserUpdateFacade userUpdateFacade = referenceConfig.get();
        System.out.println(userUpdateFacade.updateUserById("112", "zhangsan"));

        //阻塞等待
        System.in.read();
    }
}
