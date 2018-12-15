package com.example.web.annotaions;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * 虽然注解是可以组合的 但是组合注解的处理可能是不同的AnnotatedElementUtils可以处理组合注解
 * AnnotationUtils并不能处理组合注解
 * @see org.springframework.core.annotation。AnnotatedElementUtils
 * @see org.springframework.core.annotation。AnnotationUtils
 * @see org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor#getAnnotation 使用的是AnnotaionUitl
 * 所以这种自定义的并不会直接起作用
 * */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConfigurationProperties
public @interface MyConfig {
    @AliasFor(attribute = "prefix", annotation = ConfigurationProperties.class)
    String prefix();
}
