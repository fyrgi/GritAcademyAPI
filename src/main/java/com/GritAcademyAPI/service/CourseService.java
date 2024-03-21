package com.GritAcademyAPI.service;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;

import java.util.List;

public interface CourseService {

    List<Course> findAll();
    void save(Course newCourse);
    Course findById(long id);
    List<Course> findByName(String name);
    List<Course> findByDescription(String description);
    void deleteById(long id);
    Student findTheStudentsOfTheCourse(long id);
}
