package com.example.redisboot;

import com.example.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisBootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

/*        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
        System.out.println("test object" + redisTemplate.toString());*/

        Student student = new Student();
        student.setAge(11);
        student.setName("liuweiliang");
        redisTemplate.opsForValue().set("student", student);
        Student stduent1 = (Student) redisTemplate.opsForValue().get("student");
        System.out.println(stduent1);
    }

}
