package com.example.ymlboot.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

public class YmlFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource == null) {
            super.createPropertySource(name, resource);
        }
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load(name, resource.getResource());
        if (propertySourceList != null && propertySourceList.size() > 0) {
            return propertySourceList.get(0);
        }

        return super.createPropertySource(name, resource);
    }
}
