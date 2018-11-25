package com.example.ymlboot.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;

public class YmlFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load(name, resource.getResource());
        if (propertySourceList != null && propertySourceList.size() > 0) {
            return propertySourceList.get(0);
        }

        return null;
    }
}
