package com.demo.dubbo.consumer;

import com.demo.dubbo.api.UserQueryFacade;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        String configPath = "classpath*:META-INF/spring/*.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        //接口调用
        UserQueryFacade userQueryFacade = ctx.getBean(UserQueryFacade.class);
        System.out.println(userQueryFacade.queryUser("tom"));

        //阻塞等待
        System.in.read();
    }
}
