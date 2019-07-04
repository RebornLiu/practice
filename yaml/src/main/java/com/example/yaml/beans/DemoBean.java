package com.example.yaml.beans;


import com.example.yaml.annotation.MyService;
import org.springframework.stereotype.Service;

@MyService(name = "demoBeanHello")
//@Service("demoBeanHello")
public class DemoBean {
    public void echo() {
        System.out.println("demo bean");
    }
}
