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

    // This method returns a record with the id of the registration, the whole information about the student including
    // their current courses, and the id of the course for the current registration.
    @GetMapping("/registrations/student/{studentId}")
    private List<StudentCourse> findForStudent(@PathVariable long studentId)  {
        return studentCourseService.findTheCoursesOfAStudent(studentId);
    }

    //This method returns all registrations for a certain course. The student is an object where even the student
    // other registrations (Courses) can be seen.
    @GetMapping("/registrations/course/{courseId}")
    private List<StudentCourse> findForCourse(@PathVariable long courseId)  {
        return studentCourseService.findThStudentsInACourse(courseId);
    }
    @GetMapping("/registrations")
    private List<StudentCourse> findAll()  {
        return studentCourseService.findAllRecords();
    }

    @PostMapping("/registrations")
    public void save(@RequestBody StudentCourse registration)  {
        studentCourseService.save(registration);
    }

    @DeleteMapping("/registrations/{registrationId}")
    public void delete(@PathVariable long registrationId)  {
        studentCourseService.deleteRegistration(registrationId);
    }
}
