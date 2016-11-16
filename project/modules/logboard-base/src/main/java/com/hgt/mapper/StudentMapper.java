package com.hgt.mapper;

import io.yaoooo.entity.Student;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:students表操作mapper定义
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public interface StudentMapper {

    Student findStudentById(int id);

    List<Student> findAllStudents();

    void addStudent(Student student);
}
