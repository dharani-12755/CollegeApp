package com.example.college.model;

import com.example.college.constants.Department;
import com.example.college.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "students")
@Getter
//@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "age")
    @JsonProperty("age")
    private Long age;

    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private Long dateOfBirth;

    @Column(name = "department")
    @JsonProperty("department")
    private Department department;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @Column(name = "contact_no")
    @JsonProperty("contact_no")
    private String contactNo;

    @Column(name = "emergency_contact_no")
    @JsonProperty("emergency_contact_no")
    private String emergencyContactNo;

    @Column(name = "use_college_bus")
    @JsonProperty("use_college_bus")
    private Boolean useCollegeBus;

    public void setId(Long id) {
        this.id = id;
    }

}
