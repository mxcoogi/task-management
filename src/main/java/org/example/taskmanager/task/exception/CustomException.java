package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
