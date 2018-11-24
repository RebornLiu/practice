package com.example.mybatisboot.entity;
import lombok.Data;

import java.io.Serializable;


@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -5103586390868309968L;

    private int id;

    private int studentId;

    private String studentName;

    private int studentAge;
}
