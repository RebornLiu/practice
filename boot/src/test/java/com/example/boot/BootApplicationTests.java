package com.example.boot;

import com.example.beans.BeanDemo;
import com.example.property.PropDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

    @Resource
    BeanDemo beanDemo;

    @Resource
    PropDemo propDemo;

    @Test
    public void annotationBean() {
        beanDemo.say();
    }

    @Test
    public void annotabtionValue() {
        propDemo.say();

    }

}
