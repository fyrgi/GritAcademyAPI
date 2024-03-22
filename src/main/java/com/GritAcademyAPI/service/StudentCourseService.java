package com.GritAcademyAPI.service;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourse> findTheCoursesOfAStudent(Long studentId);
    List<StudentCourse> findThStudentsInACourse(Long courseId);
    List<StudentCourse> findAllRecords();
    void deleteRegistration(long id);

    public void save(StudentCourse registration);
}
