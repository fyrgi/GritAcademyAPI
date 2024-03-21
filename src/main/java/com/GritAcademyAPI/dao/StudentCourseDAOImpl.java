package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseDAOImpl implements StudentCourseDAO{
    private EntityManager entityManager;
    @Autowired
    public StudentCourseDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Course> findTheCoursesOfAStudent(Long studentId) {
        TypedQuery<Course> found = entityManager.createQuery("FROM StudentCourse WHERE idStudent = :data ", Course.class);
        found.setParameter("data", studentId);
        return found.getResultList();
    }

    @Override
    public List<Student> findThStudentsInACourse(Long courseId) {
        return null;
    }

    @Override
    public List<StudentCourse> findAllRecords() {
        TypedQuery<StudentCourse> findAll = entityManager.createQuery("FROM StudentCourse ORDER BY id ASC", StudentCourse.class);

        //return results
        return findAll.getResultList();
    }
}
