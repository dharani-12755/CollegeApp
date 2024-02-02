package com.example.college.constants;

public enum Department {

    CSE(1),
    ECE(2),
    IT(3),
    EEE(4);
    Integer value;

    Department(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
