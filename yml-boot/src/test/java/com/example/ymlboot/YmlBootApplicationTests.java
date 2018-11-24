package com.example.ymlboot;

import com.example.ymlboot.bean.Person;
import com.example.ymlboot.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YmlBootApplicationTests {

    @Resource
    private Student student;

    @Resource
    private Person person;

    @Test
    public void testYmlSelf() {
        System.out.println(student);
    }

    @Test
    public void testYmlGloble() {
        System.out.println(person);
    }

}
