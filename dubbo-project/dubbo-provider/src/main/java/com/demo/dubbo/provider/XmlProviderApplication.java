package com.demo.dubbo.provider;

import org.apache.dubbo.container.Main;

/**
 * 利用 xml 配置来启动
 */
public class XmlProviderApplication {

    public static void main(String[] args) {
        Main.main(new String[]{"spring","log4j"});
    }
}
