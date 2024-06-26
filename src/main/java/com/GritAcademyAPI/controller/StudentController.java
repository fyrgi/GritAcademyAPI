package com.GritAcademyAPI.controller;

import com.GritAcademyAPI.dto.StudentDTO;
import com.GritAcademyAPI.entity.Student;
import com.GritAcademyAPI.service.StudentCourseService;
import com.GritAcademyAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class StudentController {

    // Call the Service and the Service calls the DAO
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService theStudentService){
        studentService = theStudentService;
    }

    // Wrapping in ResponseEntity gives more control over the Http response. Used when not as straightforward approach
    // of Http response is happening.
    @GetMapping("/students-full")
    private ResponseEntity<List<Student>> findAllFull() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/students")
    private List<StudentDTO> findAll() {
        List<Student> students = studentService.findAll();
        return students.stream()
                .map(student -> new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getCity()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable long studentId){
        Student record = studentService.findById(studentId);
        if(record == null){
            throw new RuntimeException("Student with id "+ studentId +" is not found.");
        }
        studentService.deleteAllCoursesForStudent(studentId);
        studentService.deleteById(studentId);
    }

    @GetMapping("/students/{studentId}")
    private Student getStudent(@PathVariable long studentId) {
        if (studentService.findById(studentId) == null ){
            throw new RuntimeException("Student with id "+ studentId +" is not found.");
        }
        return studentService.findById(studentId);
    }

    @GetMapping("/students/first-name/{firstName}")
    private List<Student> getStudentsByFirstName(@PathVariable String firstName) {
        if (studentService.findByFirstName(firstName) == null ){
            throw new RuntimeException("No students with first name "+ firstName +".");
        }
        return studentService.findByFirstName(firstName);
    }

    @GetMapping("/students/last-name/{lastName}")
    private List<Student> getStudentsByLastName(@PathVariable String lastName) {
        if (studentService.findByLastName(lastName) == null ){
            throw new RuntimeException("No students with last name "+ lastName +".");
        }
        return studentService.findByLastName(lastName);
    }

    @GetMapping("/students/city/{city}")
    private List<Student> getStudentsByCity(@PathVariable String city) {
        // We get the student city even if they don't have one. That happens with another query in StudentDAO
        if(city.equalsIgnoreCase("null")){
            if (studentService.findStudentsWithoutCity(city) == null ){
                throw new RuntimeException("No students without data for City.");
            }
            return studentService.findStudentsWithoutCity(city);
        }
        if (studentService.findByCity(city) == null ){
            throw new RuntimeException("No students from "+ city +".");
        }
        return studentService.findByCity(city);
    }

    @PostMapping("/students")
    public void save(@RequestBody Student newStudent){
        // set the id to 0. Since it is Long add the L after 0 to cast it from int to long
        newStudent.setId(0L);
        studentService.save(newStudent);
    }



}
