package com.example.selfCache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Component
public class Demo {

    @CachePut(value = "self-cache", key = "#key")
    public String echo(String key) {
        return key + key;
    }
}
