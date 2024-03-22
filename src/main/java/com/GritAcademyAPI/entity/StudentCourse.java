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
    @JoinColumn(name = "id_student", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    // This new implementation fixes a problem where a new record couldn't be add via post and postman because it
    // expected Object type Student and Object Type Course.
    @Column(name = "id_student")
    private Long dbStudentId;

    @Column(name = "id_course")
    private Long dbCourseId;

    public StudentCourse(Long idStudent, Long idCourse){
        this.dbStudentId = idStudent;
        this.dbCourseId = idCourse;
    }

}