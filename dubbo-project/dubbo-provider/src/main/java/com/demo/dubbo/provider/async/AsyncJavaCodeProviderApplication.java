package com.demo.dubbo.provider.async;

import com.demo.dubbo.api.UserUpdateFacade;
import com.demo.dubbo.api.async.AsyncUserQueryFacade;
import com.demo.dubbo.provider.impl.UserUpdateFacadeImpl;
import com.demo.dubbo.provider.impl.asnyc.AsyncUserQueryFacadeImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用 java 配置来启动
 */
public class AsyncJavaCodeProviderApplication {

    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://");

        // 创建暴露 UserUpdateFacade 这个服务的对象
        ServiceConfig<AsyncUserQueryFacade> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(AsyncUserQueryFacade.class);
        serviceConfig.setRef(new AsyncUserQueryFacadeImpl());

        serviceConfig.export();

        System.out.println("Dubbo " + AsyncUserQueryFacade.class.getSimpleName() + " started!");
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");

        System.in.read();
    }
}
