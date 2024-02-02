package com.example.college.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.serializer.Serializer;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "Attendance")
@Getter
//@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Attendance implements Serializable {
    @Id
    @Column  (name ="total leaves")
    @JsonProperty ("total leaves")
    private int leave;

    @Column (name ="students_id")
    @JsonProperty ("students_id")
    private  int students_id;

    @Column (name ="overall attendance")
    @JsonProperty ("overall attendance")
    private  int overall_attendance;

    @Column (name ="Attendance_percentage")
    @JsonProperty ("Attendance_percentage")
    private int Attendance_percentage;

    public void setId(Long id) {
        this.id = id;
    }

}
