package com.example.college.errors;

import lombok.Getter;


public class EntityNotFoundException extends RuntimeException {
    private final ApiError apiError;

    public ApiError getApiError() {
        return apiError;
    }

    public Long getEntityId() {
        return entityId;
    }

    private final Long entityId;


    public EntityNotFoundException(ApiError apiError, Long entityId) {
        this.apiError = apiError;
        this.entityId = entityId;
    }
}