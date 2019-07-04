package com.example.yaml.yaml;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;


/**
 * 自定义配置文件的加载factory 用于@PropertySource注解
 * @see Student
 * */
public class YmlPropertySourceFactory extends DefaultPropertySourceFactory {
        @Override
        public PropertySource createPropertySource(String name, EncodedResource resource) throws IOException {
            if (resource == null) {
                return super.createPropertySource(name, resource);
            }
            List<PropertySource<?>> propertySourceList = new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource());
            if (!propertySourceList.isEmpty()) {
                return propertySourceList.iterator().next();
            }
            return super.createPropertySource(name, resource);
        }

}
