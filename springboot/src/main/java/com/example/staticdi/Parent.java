package com.example.staticdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Parent {

    @Autowired
    private Service service;

    private static Map<String, String> map = new HashMap<>();

    private static SService sService;

    @Autowired
    public
    void setsService(SService sService) {
        Parent.sService = sService;
    }

    @PostConstruct
    public void init() {
        map.put("123", "123");
        service.service();
    }


    public void echo() {
        System.out.println("parent echo");
    }

}
