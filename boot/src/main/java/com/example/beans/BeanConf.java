package com.example.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConf {

    @Bean
    public BeanDemo getBeanDemo() {
        return new BeanDemo();
    }
}
