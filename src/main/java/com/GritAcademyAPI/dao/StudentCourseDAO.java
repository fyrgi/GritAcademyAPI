package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;

import java.util.List;

public interface StudentCourseDAO {
    List<Course> findTheCoursesOfAStudent(Long studentId);
    List<Student> findThStudentsInACourse(Long courseId);
    List<StudentCourse> findAllRecords();
}
