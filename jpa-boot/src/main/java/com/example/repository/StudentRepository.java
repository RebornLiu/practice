package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentName(String stdName);

    /**
     * Param 注解必须指定
     * */
    @Query(nativeQuery = true, value = "select * from student where std_age=:age")
    Student findByAge(@Param("age") Integer age);


    /**
     * 更新操作 需要添加 Modifying Transactional
     * */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set std_age=:age where std_name=:name")
    void updateStudentInfo(@Param("name") String name, @Param("age") Integer age);
}
