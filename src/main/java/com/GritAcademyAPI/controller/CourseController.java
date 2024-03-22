package com.GritAcademyAPI.controller;

import com.GritAcademyAPI.dto.CoursesDTO;
import com.GritAcademyAPI.dto.StudentDTO;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import com.GritAcademyAPI.service.CourseService;
import com.GritAcademyAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService theCourseService){
        courseService = theCourseService;
    }

    // Find the information of the courses with the student list
    @GetMapping("/courses-full")
    private List<Course> findAllFull() {
        return courseService.findAll();
    }

    // Find the information of all courses without the student list.
    @GetMapping("/courses")
    private List<CoursesDTO> findAll() {
        List<Course> courses = courseService.findAll();
        return courses.stream()
                .map(course -> new CoursesDTO(course.getId(), course.getName(), course.getDescription()))
                .collect(Collectors.toList());
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

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable long courseId){
        Course record = courseService.findById(courseId);
        if(record == null){
            throw new RuntimeException("Course with id "+ courseId +" is not found.");
        }
        courseService.deleteAllStudentRegistrations(courseId);
        courseService.deleteById(courseId);
    }

    @PostMapping("/courses")
    public void save(@RequestBody Course newCourse)  {
        courseService.save(newCourse);
    }

}
