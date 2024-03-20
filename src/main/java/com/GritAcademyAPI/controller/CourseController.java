package com.GritAcademyAPI.controller;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.service.CourseService;
import com.GritAcademyAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService theCourseService){
        courseService = theCourseService;
    }

    @GetMapping("/courses")
    private List<Course> findAll() {
        return courseService.findAll();
    }

    // add mapping
    @GetMapping("/courses/{courseId}")
    private Course getCourse(@PathVariable long courseId) {
        if (courseService.findById(courseId) == null ){
            throw new RuntimeException("Student with id "+ courseId +" is not found.");
        }
        return courseService.findById(courseId);
    }
}
