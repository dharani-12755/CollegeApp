package com.example.college.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "grades", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "semester"})})
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Grades implements Serializable {


    @Id
    @Column(name = "student_id")
    @JsonProperty("student_id")
    private Long studentId;

    @Column(name = "semester")
    @JsonProperty("semester")
    private int semester;

    @Column(name = "gpa")
    @JsonProperty("gpa")
    private double gpa;

    @Column(name = "cgpa")
    @JsonProperty("cgpa")
    private String cgpa;

    @Column(name = "performance")
    @JsonProperty("performance")
    private String performance;
}
