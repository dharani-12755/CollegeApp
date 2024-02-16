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
@Table(name = "attendance", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "semester"})})
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Attendance implements Serializable {

    @Id
    @Column(name = "student_id")
    @JsonProperty("student_id")
    private Long studentId;

    @Column(name = "semester")
    @JsonProperty("semester")
    private int semester;

    @Column(name = "no_of_absences")
    @JsonProperty("no_of_absences")
    private int noOfAbsences;

    @Column(name = "attendance_percentage")
    @JsonProperty("attendance_percentage")
    private String attendancePercentage;

}
