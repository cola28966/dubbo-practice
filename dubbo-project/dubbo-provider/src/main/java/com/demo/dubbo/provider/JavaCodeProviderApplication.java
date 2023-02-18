package com.demo.dubbo.provider;

import com.demo.dubbo.api.UserUpdateFacade;
import com.demo.dubbo.provider.impl.UserUpdateFacadeImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.container.Main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用 java 配置来启动
 */
public class JavaCodeProviderApplication {

    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://");

        // 创建暴露 UserUpdateFacade 这个服务的对象
        ServiceConfig<UserUpdateFacade> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(UserUpdateFacade.class);
        serviceConfig.setRef(new UserUpdateFacadeImpl());

        serviceConfig.export();

        System.out.println("Dubbo " + UserUpdateFacade.class.getSimpleName() + " started!");
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");

        System.in.read();
    }
}
