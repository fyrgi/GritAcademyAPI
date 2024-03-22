package com.GritAcademyAPI.controller;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import com.GritAcademyAPI.service.StudentCourseService;
import com.GritAcademyAPI.service.StudentCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentCourseController {
    private StudentCourseService studentCourseService;

    @Autowired
    public StudentCourseController(StudentCourseService theStudentCourseService){
        studentCourseService = theStudentCourseService;
    }

    @GetMapping("/courses-of-student/{studentId}")
    private List<Course> findForStudent(@PathVariable long studentId)  {
        return studentCourseService.findTheCoursesOfAStudent(studentId);
    }

    @GetMapping("/registrations")
    private List<StudentCourse> findAll()  {
        return studentCourseService.findAllRecords();
    }

    @PostMapping("/registrations")
    public void save(@RequestBody StudentCourse registration)  {
        studentCourseService.save(registration);
    }
}
