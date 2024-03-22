package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;

import java.util.List;

public interface CourseDAO {
    void save(Course newCourse);

    Course findById(long id);

    List<Course> findAll();
    List<Course> findByName(String name);

    List<Course> findByDescription(String description);

    List<Course> findCoursesWithoutDescription(String description);
    void deleteAllStudentRegistrations(long id);
    void deleteById(long id);
    Student findTheStudentsOfTheCourse(long id);

}
