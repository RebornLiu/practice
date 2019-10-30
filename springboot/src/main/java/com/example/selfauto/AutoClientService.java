package com.example.selfauto;

import com.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AutoClientService {

    @Resource
    private HelloService helloService;

    public void hello() {
        helloService.echo();
    }
}
