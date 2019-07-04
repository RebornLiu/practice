package com.example.yaml;

import com.example.yaml.annotation.MyService;
import com.example.yaml.beans.DemoBean;
import com.example.yaml.yaml.Global;
import com.example.yaml.yaml.PropertiesDemo;
import com.example.yaml.yaml.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Resource
    private Student student;

    @Test
    public void contextLoads() {
        PropertiesDemo p = applicationContext.getBean("demo", PropertiesDemo.class);
        System.out.println(p);
    }

    @Test
    public void testLoadYaml() {
        Global p = applicationContext.getBean("global", Global.class);

        System.out.println(p.getCountry());
        p.getCities().forEach(System.out::println);
        p.getPeople().forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

    }

    @Test
    public void testLoadStudentYml() {
        System.out.println(student);
    }

    @Test
    public void testGetBean() {
        Component component = AnnotatedElementUtils.findMergedAnnotation(DemoBean.class, Component.class);
        System.out.println(component.value());
    }


}
