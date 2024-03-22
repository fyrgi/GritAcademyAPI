package com.GritAcademyAPI.service;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    List<Course> findTheCoursesOfAStudent(Long studentId);
    List<Student> findThStudentsInACourse(Long courseId);
    List<StudentCourse> findAllRecords();
    void deleteAllCoursesForStudent(long id);

    public void save(StudentCourse registration);
}
