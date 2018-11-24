package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = -5103586390868309968L;

    @Id
    private int id;

    @Column(name = "std_id")
    private int studentId;

    @Column(name = "std_name")
    private String studentName;

    @Column(name = "std_age")
    private int studentAge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                '}';
    }
}
