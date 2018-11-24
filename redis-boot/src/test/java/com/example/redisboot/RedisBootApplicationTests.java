package com.example.redisboot;

import com.example.bean.Student;
import org.junit.Assert;
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
    RedisTemplate<Object, Object> stingRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> fastJsonRedisTemplate;

    @Test
    public void testRedisTemplate() {
        stingRedisTemplate.opsForValue().set("name", "test_name");
        String name = (String)stingRedisTemplate.opsForValue().get("name");
        Assert.assertEquals(name, "test_name");
    }

    @Test
    public void testStringRedisTemplate() {
        Student student = new Student();
        student.setAge(11);
        student.setName("student_name");
        fastJsonRedisTemplate.opsForValue().set("student", student);
        Student student1 = (Student) fastJsonRedisTemplate.opsForValue().get("student");

        Assert.assertEquals(student1.getAge(), student.getAge());
        Assert.assertEquals(student1.getName(), student.getName());
    }

}
