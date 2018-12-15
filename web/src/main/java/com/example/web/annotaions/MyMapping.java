package com.example.web.annotaions;


import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface MyMapping {

    @AliasFor(attribute = "path", annotation = RequestMapping.class)
    String ph();
}
