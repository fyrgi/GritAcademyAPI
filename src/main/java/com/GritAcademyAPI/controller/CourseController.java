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

    @GetMapping("/courses/name/{courseName}")
    private List<Course> getCourse(@PathVariable String courseName) {
        if (courseService.findByName(courseName) == null ){
            throw new RuntimeException("Course with name "+ courseName +" is not found.");
        }
        return courseService.findByName(courseName);
    }
    @GetMapping("/courses/description/{description}")
    private List<Course> getCoursesByDescription(@PathVariable String description) {
        /*// We get the student city even if they don't have one. That happens with another query in StudentDAO
        if(description.equalsIgnoreCase("null")){

            return courseService.findCoursesWithoutDescription(description);
        }*/
        if (courseService.findByDescription(description) == null ){
            throw new RuntimeException("The part \""+ description + "\" of the description could not be found in any course.");
        }
        return courseService.findByDescription(description);
    }
}
