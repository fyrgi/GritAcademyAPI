package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentCourseDAOImpl implements StudentCourseDAO{
    private EntityManager entityManager;
    @Autowired
    public StudentCourseDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<StudentCourse> findTheCoursesOfAStudent(Long studentId) {
        TypedQuery<StudentCourse> found = entityManager.createQuery("FROM StudentCourse WHERE student.id = :data ", StudentCourse.class);
        found.setParameter("data", studentId);
        return found.getResultList();
    }

    @Override
    public List<StudentCourse> findThStudentsInACourse(Long courseId) {
        TypedQuery<StudentCourse> found = entityManager.createQuery("FROM StudentCourse WHERE course.id = :data ", StudentCourse.class);
        found.setParameter("data", courseId);
        return found.getResultList();
    }

    @Override
    public List<StudentCourse> findAllRecords() {
        TypedQuery<StudentCourse> findAll = entityManager.createQuery("FROM StudentCourse ORDER BY id ASC", StudentCourse.class);

        return findAll.getResultList();
    }

    @Override
    public void deleteRegistration(long id) {
        StudentCourse toBeDeleted = entityManager.find(StudentCourse.class, id);
        entityManager.remove(toBeDeleted);
    }

    @Override
    public void save(StudentCourse registration) {
        StudentCourse managedRegistration = entityManager.merge(registration);
        entityManager.persist(managedRegistration);
    }
}
