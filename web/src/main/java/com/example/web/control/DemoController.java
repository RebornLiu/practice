package com.example.web.control;

import com.example.web.annotaions.MyMapping;
import com.example.web.beans.Demo;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    private Demo demo;

    @MyMapping(ph = "echo")
    public String echo() {
        return demo.echo();
    }
}