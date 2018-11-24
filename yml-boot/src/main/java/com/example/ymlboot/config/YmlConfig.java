package com.example.ymlboot.config;


import lombok.Data;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@Configuration
public class YmlConfig {

    /**
     * @see org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
     * */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

        String[] locations = new String[]{"config/student.yml"};
        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();

        Resource[] resources = Arrays.stream(locations)
                .map(ClassPathResource::new)
                .collect(Collectors.toList()).toArray(new Resource[locations.length]);

        yamlPropertiesFactoryBean.setResources(resources);
        propertyPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean.getObject());
        return propertyPlaceholderConfigurer;
    }
}
