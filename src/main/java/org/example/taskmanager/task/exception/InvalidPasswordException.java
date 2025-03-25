package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends RuntimeException {
    private static final String MESSAGE = "Invalid password";
    @Getter
    private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;
    public InvalidPasswordException() {
        super(MESSAGE);
    }
    public HttpStatus getStatus() {
        return STATUS;
    }
}
