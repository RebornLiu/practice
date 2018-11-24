package com.example.mybatisboot;

import com.example.mybatisboot.dao.StudentDao;
import com.example.mybatisboot.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisBootApplicationTests {


    @Autowired
    StudentDao studentDao;

    @Test
    public void testSelect() {
        Student student = studentDao.select(1);
        Assert.assertEquals(student.getStudentName(), "JPA");
    }

}
