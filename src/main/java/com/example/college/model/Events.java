package com.example.college.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Events {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "event_date")
    @JsonProperty("event_date")
    private Instant eventDate;

    @Column(name = "event_type")
    @JsonProperty("event_type")
    private String eventType;

    @Column(name = "location")
    @JsonProperty("location")
    private String location;

    @Column(name = "in_charge")
    @JsonProperty("in_charge")
    private String inCharge;

    @Column(name = "registration_needed")
    @JsonProperty("registration_needed")
    private Boolean registrationNeeded;

    public void setId(Long id) {
        this.id = id;
    }

}
