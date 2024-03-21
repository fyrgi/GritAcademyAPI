package com.GritAcademyAPI.service;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    void save(Student newStudent);
    Student findById(long id);

    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastNme);
    List<Student> findByCity(String city);
    List<Student> findStudentsWithoutCity(String city);
    void deleteById(long id);

    Course findTheCoursesOfTheStudent(long id);
}
