package com.example.mybatisboot.dao;

import com.example.mybatisboot.entity.Student;
import org.springframework.stereotype.Service;

public interface StudentDao {
    Student select(int id);
}
