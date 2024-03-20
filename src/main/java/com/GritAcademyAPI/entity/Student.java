package com.GritAcademyAPI.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="students")
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name="student_course",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_course")
    )
    @JsonIgnoreProperties("students")
    //@JsonManagedReference // Because of @JsonIdentityInfo this annotation is not needed anymore. The annotation is better for OneToMany relationship
    private Set<Course> courses;

    // adding a convenience method helping with the advanced relationships
    public void addCourse(Course course){
        if(courses == null){
            courses = new HashSet<>() {
            };
        }
        courses.add(course);
    }

    public Student(String firstName, String lastName, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        if(!city.isEmpty()){
            this.city = city;
        }
    }
}
