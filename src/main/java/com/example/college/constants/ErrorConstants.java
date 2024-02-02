package com.example.college.constants;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ErrorConstants {
    public enum ErrorCode {
        STUDENT_NOT_FOUND(3001, "Student not found");

        Integer code;
        String message;

        ErrorCode(int code, String  message) {
            this.code = code;
            this.message = message;
        }

        public static ErrorCode get(Integer code) {
            for (ErrorCode error : values()) {
                if (code.equals(error.getCode())) {
                    return error;
                }
            }
            return null;
        }

        private static final Set<String> ERROR_CODES = Stream.of(ErrorCode.values()).map(ErrorCode::name).collect(Collectors.toSet());

        public static Set<String> getErrorCodes() {
            return ERROR_CODES;
        }

        @JsonValue
        public Integer getCode() {
            return this.code;
        }

        @JsonValue
        public String getMessage() {
            return message;
        }

        @JsonValue
        public String getMessage(Object... args) {
            return String.format(message, args);
        }

        public static ErrorCode of(String error, ErrorCode defaultCode) {
            try {
                return ErrorCode.valueOf(error);
            } catch (IllegalArgumentException e) {
                return defaultCode;
            }
        }
    }
}
