package com.example.yaml.annotation;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConfigurationProperties
public @interface MyConfigPrefix {
    @AliasFor(annotation = ConfigurationProperties.class)
    String prefix() default "";
}
