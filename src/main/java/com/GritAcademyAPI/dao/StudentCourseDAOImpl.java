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

    @Override
    public void deleteAllCoursesForStudent(long id) {
        TypedQuery<StudentCourse> found = entityManager.createQuery("SELECT sc FROM StudentCourse sc WHERE sc.idStudent.id = :studentId", StudentCourse.class);
        found.setParameter("studentId", id);
        List<StudentCourse> studentCourses = found.getResultList();
        for (StudentCourse studentCourse : studentCourses) {
            System.out.println("Entered The result of the courses");
            entityManager.remove(studentCourse);
        }
    }
}
