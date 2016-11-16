package com.hgt.dao;

import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.Student;
import io.yaoooo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:直接操作数据库students表的类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/

@RestController
public class StudentController {

    private final String BASE_URL = "stu";

    @Autowired
    StudentService studentService;

    @RequestMapping(value = BASE_URL + "/id/{id}", method = RequestMethod.GET)
    public DataResult<Student> findStudentById(@PathVariable int id) {
        return studentService.findStudentById(id);
    }

    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<Student>> findAllStudents() {
        return studentService.findAllStudents();
    }

    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
}
