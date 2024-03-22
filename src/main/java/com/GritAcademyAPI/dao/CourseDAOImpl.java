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
public class CourseDAOImpl implements CourseDAO{
    private EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Course newCourse) {
        //entityManager.persist(newCourse);
        Course managedCourse = entityManager.merge(newCourse);
        entityManager.persist(managedCourse);
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
    public List<Course> findByName(String name){
        TypedQuery<Course> found = entityManager.createQuery("FROM Course WHERE name = :data ", Course.class);
        found.setParameter("data", name);
        return found.getResultList();
    }

    @Override
    public List<Course> findByDescription(String description) {
        TypedQuery<Course> found = entityManager.createQuery("FROM Course WHERE description LIKE :data ", Course.class);
        found.setParameter("data", "%" + description + "%");
        return found.getResultList();
    }

    @Override
    public List<Course> findCoursesWithoutDescription(String description) {
        TypedQuery<Course> found = entityManager.createQuery("FROM Course WHERE description IS NULL OR description = ''", Course.class);
        return found.getResultList();
    }

    @Override
    public void deleteAllStudentRegistrations(long id) {
        TypedQuery<StudentCourse> found = entityManager.createQuery("SELECT sc FROM StudentCourse sc WHERE sc.idCourse.id = :courseId", StudentCourse.class);
        found.setParameter("courseId", id);
        List<StudentCourse> studentCourses = found.getResultList();
        for (StudentCourse studentCourse : studentCourses) {
            entityManager.remove(studentCourse);
        }
    }

    @Override
    public Student findTheStudentsOfTheCourse(long id) {
        TypedQuery<Student> requestData = entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :givenId", Student.class);
        requestData.setParameter("givenId", id);
        Student student = requestData.getSingleResult();
        return student;
    }
}
