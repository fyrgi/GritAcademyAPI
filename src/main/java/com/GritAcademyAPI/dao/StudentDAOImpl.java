package com.GritAcademyAPI.dao;

import com.GritAcademyAPI.dto.StudentDTO;
import com.GritAcademyAPI.entity.Course;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.entity.StudentCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
        // Because I am using Postman to ensure that the POST is working I had a problem with persist() causing HTTP response 500
        //entityManager.persist(newStudent);
        // instead I use the merge() first and then commit which works perfectly in Postman, ensuring adding data to the database.
        Student managedStudent = entityManager.merge(newStudent);
        entityManager.persist(managedStudent);
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
    public void deleteAllCoursesForStudent(long id) {
        TypedQuery<StudentCourse> found = entityManager.createQuery("SELECT sc FROM StudentCourse sc WHERE sc.idStudent.id = :studentId", StudentCourse.class);
        found.setParameter("studentId", id);
        List<StudentCourse> studentCourses = found.getResultList();
        for (StudentCourse studentCourse : studentCourses) {
            System.out.println("Entered The result of the courses");
            entityManager.remove(studentCourse);
        }
    }
    @Override
    public void deleteById(long id) {
        System.out.println("Entered deleteById");
        Student toBeDeleted = entityManager.find(Student.class, id);
        deleteAllCoursesForStudent(id);
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
