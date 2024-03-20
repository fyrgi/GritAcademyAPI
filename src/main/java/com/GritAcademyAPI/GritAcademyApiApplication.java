package com.GritAcademyAPI;

import com.GritAcademyAPI.dao.StudentDAO;
import com.GritAcademyAPI.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GritAcademyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GritAcademyApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// addStudent(studentDAO);
			// findStudent(studentDAO, 100);
			//allStudent(studentDAO);
		};
	}

	private void findStudent(StudentDAO studentDAO, Integer id) {
		Student foundStudent = studentDAO.findById(id);
		System.out.println("The student we found is " + foundStudent.getFirstName() + " " + foundStudent.getLastName());
	}

	private void allStudent(StudentDAO studentDAO) {
		List<Student> result = studentDAO.findAll();
		for(Student student : result){
			System.out.println(student);
		}
	}

	private void addStudent(StudentDAO studentDAO) {
		// create the new student object
		Student tempStudent = new Student("Cris", "Olave", "");
		// save the new student
		System.out.println("Saving...");
		studentDAO.save(tempStudent);
		// display the new student
		System.out.println("Displaying student with id "+tempStudent.getId());

	}

}
