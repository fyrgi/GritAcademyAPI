package com.GritAcademyAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;

    // Constructor, getters, and setters
    public StudentDTO(Long id, String firstName, String lastName, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    // Getters and setters
}
