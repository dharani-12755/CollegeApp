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
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "library")
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Library {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "book_name")
    @JsonProperty("book_name")
    private String bookName;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "due_days")
    @JsonProperty("due_days")
    private Long dueDays;

    @Column(name = "author")
    @JsonProperty("author")
    private String author;

    @Column(name = "edition")
    @JsonProperty("edition")
    private String edition;

    @Column(name = "borrower_name")
    @JsonProperty("borrower_name")
    private String borrowerName;

    @Column(name = "checkout_date")
    @JsonProperty("checkout_date")
    private Instant checkoutDate;

    @Column(name = "checkin_date")
    @JsonProperty("checkin_date")
    private Instant checkinDate;

    @Column(name = "due_date")
    @JsonProperty("due_date")
    private Instant dueDate;

    @Column(name = "fine_amount")
    @JsonProperty("fine_amount")
    private Long fineAmount;

    @Column(name = "returned")
    @JsonProperty("returned")
    private Boolean returned;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    public void setDueDate() {
        // Calculate due date 10 days from checkout date
        this.dueDate = this.checkoutDate.plus(this.dueDays, ChronoUnit.DAYS);
    }

    public void setFineAmount() {
        if (checkinDate != null && checkinDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, checkinDate);
            if (daysOverdue == 0) {
                this.fineAmount = 10L;
            } else {
                this.fineAmount = daysOverdue * 10;
            }
            // $1 per day overdue
        } else {
            this.fineAmount = 0L; // No fine
        }
    }
}
