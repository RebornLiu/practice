package com.example.web.beans;

import com.example.web.annotaions.MyConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@MyConfig(prefix = "person")
//@ConfigurationProperties(prefix = "person")
public class Demo {
    private String name;

    public String echo() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
