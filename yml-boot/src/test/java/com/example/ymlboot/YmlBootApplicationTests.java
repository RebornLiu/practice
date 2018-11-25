package com.example.ymlboot;

import com.example.ymlboot.bean.Person;
import com.example.ymlboot.bean.Student;
import com.example.ymlboot.bean.Teacher;
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

    @Resource
    private Teacher teacher;

    @Test
    public void testYmlSelf() {
        System.out.println(student);
    }

    @Test
    public void testYmlGloble() {
        System.out.println(person);
    }

    @Test
    public void testYmlFactory() {
        System.out.println(teacher);
    }

}
