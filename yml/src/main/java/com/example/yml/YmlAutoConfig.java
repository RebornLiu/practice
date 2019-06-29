package com.example.yml;


import com.example.beans.DemoService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@AutoConfigureAfter(PropertySourcesPlaceholderConfigurer.class)
public class YmlAutoConfig {

    @Bean
    public DemoService buildBean() {
        return new DemoService();
    }

    @Bean
    public static PropertyPlaceholderConfigurer createYmlPlaceHolder() throws IOException {
       /* InputStream inputStream = YmlAutoConfig.class.getClassLoader().getResourceAsStream("conf/test.yml");
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new InputStreamResource(inputStream));
        propertyPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean.getObject());*/

        System.out.println("_________" + YmlAutoConfig.class.getClassLoader());
        InputStream inputStream = YmlAutoConfig.class.getClassLoader().getResourceAsStream("conf/test.yml");
        ClassPathResource inputStream1 = new ClassPathResource("classpath:conf/test.yml");
        inputStream1.getInputStream();
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/test.yml");

        return new PropertyPlaceholderConfigurer();
    }
}
