package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;
    // inject manager using construction injection

    // entityManager is automatically created by spring boot.
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Student newStudent) {
        entityManager.persist(newStudent);
    }

    @Override
    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query. FROM is not the name of the table but the Entity instead
        TypedQuery<Student> findAll = entityManager.createQuery("FROM Student ORDER BY id ASC", Student.class);

        //return results
        return findAll.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> found = entityManager.createQuery("FROM Student WHERE firstName = :data ", Student.class);
        found.setParameter("data", firstName);
        //found.setParameter("data", "%" + firstName + "%"); //used when firstName LIKE :data
        return found.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> found = entityManager.createQuery("FROM Student WHERE lastName = :data ", Student.class);
        found.setParameter("data", lastName);
        return found.getResultList();
    }

    @Override
    public List<Student> findByCity(String city) {
        TypedQuery<Student> found = entityManager.createQuery("FROM Student WHERE city = :data ", Student.class);
        found.setParameter("data", city);
        return found.getResultList();
    }

    @Override
    public List<Student> findStudentsWithoutCity(String city) {
        TypedQuery<Student> found = entityManager.createQuery("FROM Student WHERE city IS NULL OR city = ''", Student.class);
        return found.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Student toBeDeleted = entityManager.find(Student.class, id);
        entityManager.remove(toBeDeleted);
    }

    @Override
    public Course findTheCoursesOfTheStudent(long id) {
        TypedQuery<Course> requestData = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.students WHERE c.id = :courseId", Course.class);
        requestData.setParameter("courseId", id);
        Course course = requestData.getSingleResult();
        return course;
    }

}
