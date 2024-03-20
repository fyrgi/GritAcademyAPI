package com.GritAcademyAPI.service;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;

import java.util.List;

public interface CourseService {

    List<Course> findAll();
    void save(Course newCourse);
    Course findById(long id);
    Course findByName(String name);
    void deleteById(long id);
    Student findTheStudentsOfTheCourse(long id);
}
