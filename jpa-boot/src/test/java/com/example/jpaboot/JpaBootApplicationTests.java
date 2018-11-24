package com.example.jpaboot;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("hikari")
//@ActiveProfiles("tomcat")
public class JpaBootApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void save() {
        Student student = new Student();
        student.setStudentId(1111);
        student.setStudentAge(12);
        student.setStudentName("JPA");
        studentRepository.save(student);
    }

    @Test
    public void query() {
        Student student = studentRepository.findById(1).get();
        Assert.assertEquals(student.getStudentId(), 1111);
    }

    @Test
    public void query2() {
        Student student = studentRepository.findByStudentName("JPA");
        Assert.assertEquals(student.getStudentId(), 1111);
    }

    @Test
    public void queryByAge() {
        Student student = studentRepository.findByAge(12);
        Assert.assertEquals(student.getStudentId(), 1111);
    }

    @Test
    public void update() {
        studentRepository.updateStudentInfo("JPA", 34);
        Student student = studentRepository.findById(1).get();
        Assert.assertEquals(34, student.getStudentAge());
    }
}
