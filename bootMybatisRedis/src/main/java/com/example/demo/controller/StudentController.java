package com.example.demo.controller;

import com.example.demo.dao.StudentMapper;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentExample;
import com.example.demo.entity.VO.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //代表controller
@RequestMapping("/studentC")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/index")
    public Message index(){
        Message message = new Message();
        Student student = new Student();
        student.setName("a");
        student.setAge(11);
        studentMapper.insertSelective(student);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAgeIsNull();
        List<Student> list = studentMapper.selectByExample(studentExample);
        message.setSuccess(true);
        message.setMessage(String.valueOf(list.size()));
        return message;
    }

    @RequestMapping("/get")
    public Student getStudent(Integer id){
        Student student = new Student();
        student = studentMapper.selectByPrimaryKey(id);
        return  student;
    }
}
