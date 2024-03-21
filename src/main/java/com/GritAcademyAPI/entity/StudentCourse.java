package com.GritAcademyAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="student_course")
@ToString
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private Student idStudent;

    @ManyToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    private Course idCourse;
}