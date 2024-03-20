package com.GritAcademyAPI.controller;

import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentController {

    // Call the Service and the Service calls the DAO
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService theStudentService){
        studentService = theStudentService;
    }

    @GetMapping("/students")
    private List<Student> findAll() {
        return studentService.findAll();
    }

    // add mapping
    @GetMapping("/students/{studentId}")
    private Student getStudent(@PathVariable long studentId) {
        if (studentService.findById(studentId) == null ){
            throw new RuntimeException("Student with id "+ studentId +" is not found.");
        }
        return studentService.findById(studentId);
    }

    @GetMapping("/students/city={city}")
    private List<Student> getStudentsByCity(@PathVariable String city) {
        if (studentService.findByCity(city) == null ){
            throw new RuntimeException("No students from "+ city +".");
        }
        return studentService.findByCity(city);
    }
    @PostMapping("/students")
    public void save(@RequestBody Student newStudent){
        // set the id to 0. Since it is Long add the L after 0 to cast it from int to long
        newStudent.setId(0L);
        studentService.save(newStudent);
    }

}
