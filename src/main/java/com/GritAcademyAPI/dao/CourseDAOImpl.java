package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO{
    private EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Course newCourse) {
        entityManager.persist(newCourse);
    }

    @Override
    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> findAll = entityManager.createQuery("FROM Course ORDER BY name ASC, id ASC", Course.class);

        //return results
        return findAll.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Course toBeDeleted = entityManager.find(Course.class, id);
        entityManager.remove(toBeDeleted);
    }

    @Override
    public Student findTheStudentsOfTheCourse(long id) {
        TypedQuery<Student> requestData = entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :givenId", Student.class);
        requestData.setParameter("givenId", id);
        Student student = requestData.getSingleResult();
        return student;
    }
}
