package com.GritAcademyAPI.service;

import com.GritAcademyAPI.dao.CourseDAO;
import com.GritAcademyAPI.dao.CourseDAOImpl;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseDAO courseDAO;

    public CourseServiceImpl(CourseDAO theCourseDAO){
        courseDAO = theCourseDAO;
    }

    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Course newCourse) {

    }

    @Override
    public Course findById(long id) {
        return courseDAO.findById(id);
    }

    @Override
    public Course findByName(String name) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
    }

    @Override
    @Transactional
    public Student findTheStudentsOfTheCourse(long id) {
        return courseDAO.findTheStudentsOfTheCourse(id);
    }
}
