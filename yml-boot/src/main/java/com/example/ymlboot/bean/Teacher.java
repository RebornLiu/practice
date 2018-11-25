package com.example.ymlboot.bean;


import com.example.ymlboot.config.YmlFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:config/teacher.yml", factory = YmlFactory.class)
@ConfigurationProperties(prefix = "teacher")
public class Teacher {

    private String name;
}
