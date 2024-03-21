package com.GritAcademyAPI.dao;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/***
 * StudentDAO is the interface that contains the methods implemented in the java class
 * in the DAO stands for Data access objet
 *
 *
 * */
public interface StudentDAO {
    void save(Student newStudent);

    Student findById(long id);

    List<Student> findAll();
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastNme);
    List<Student> findByCity(String city);
    List<Student> findStudentsWithoutCity(String city);
    void deleteById(long id);
    Course findTheCoursesOfTheStudent(long id);
}
