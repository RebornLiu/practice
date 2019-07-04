package com.example.yaml.yaml;

import com.example.yaml.annotation.MyConfigPrefix;
import com.example.yaml.annotation.YmlPropertySource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = "classpath:config/personInfo.yml", factory = YmlPropertySourceFactory.class)
//@YmlPropertySource(value = "classpath:config/student.yml")
@PropertySource(value = "classpath:config/student.yml", factory = YmlPropertySourceFactory.class)
//@MyConfigPrefix(prefix = "std")
@ConfigurationProperties(prefix = "std")
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
