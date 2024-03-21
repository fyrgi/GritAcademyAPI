package com.GritAcademyAPI.service;

import com.GritAcademyAPI.dao.StudentCourseDAO;
import com.GritAcademyAPI.dao.StudentDAO;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService{

    private StudentCourseDAO studentCourseDAO;
    public StudentCourseServiceImpl(StudentCourseDAO theStudentCourseDAO){
        studentCourseDAO = theStudentCourseDAO;
    }
    @Override
    public List<Course> findTheCoursesOfAStudent(Long studentId) {
        return studentCourseDAO.findTheCoursesOfAStudent(studentId);
    }

    @Override
    public List<Student> findThStudentsInACourse(Long courseId) {
        return studentCourseDAO.findThStudentsInACourse(courseId);
    }

    @Override
    public List<StudentCourse> findAllRecords() {
        return studentCourseDAO.findAllRecords();
    }
}
