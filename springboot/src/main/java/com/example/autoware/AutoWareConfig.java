package com.example.autoware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AutoWareConfig {

    @Bean
    public Container container(List<IDemo> iDemoList){
        Container container = new Container();
        container.setIDemoList(iDemoList);
        return container;
    }
}
