package com.example.yaml.annotation;

import com.example.yaml.yaml.YmlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@PropertySource(value = "classpath:config/personInfo.yml", factory = YmlPropertySourceFactory.class)
public @interface YmlPropertySource {
    @AliasFor(attribute = "value", annotation = PropertySource.class)
    String[] value();
}
