package com.example.college.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApiError implements Serializable {
    private HttpStatus status;
    private int errorType;
    private String message;

    public ApiError(HttpStatus status, int errorType, String message) {
        this.status = status;
        this.errorType = errorType;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getErrorType() {
        return errorType;
    }

    public String getMessage() {
        return message;
    }
}
