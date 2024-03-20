package com.GritAcademyAPI.service;

import com.GritAcademyAPI.dao.StudentDAO;
import com.GritAcademyAPI.dao.StudentDAOImpl;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    // inject the Student data access object
    private StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO theStudentDAO){
        studentDAO = theStudentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Student newStudent) {
        studentDAO.save(newStudent);
    }

    @Override
    public Student findById(long id){
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        return studentDAO.findByFirstName(firstName);
    }

    @Override
    public List<Student> findByLastName(String lastNme) {
        return studentDAO.findByLastName(lastNme);
    }

    @Override
    public List<Student> findByCity(String city) {
        return studentDAO.findByCity(city);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        studentDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Course findTheCoursesOfTheStudent(long id) {
        return studentDAO.findTheCoursesOfTheStudent(id);
    }


}
