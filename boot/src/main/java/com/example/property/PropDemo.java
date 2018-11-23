package com.example.property;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:conf/app.properties"})
@ConfigurationProperties(prefix = "config")
public class PropDemo {
    @Value("${appName}")
    private String appName;

    private Integer port;

    public void say() {
        System.out.println("appName:" + appName);
        System.out.println("port:" + port);

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
