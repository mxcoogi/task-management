package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends RuntimeException {
    private static final String MESSAGE = "Invalid email";
    @Getter
    private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public InvalidEmailException() {
        super(MESSAGE);
    }
    public HttpStatus getStatus(){
        return STATUS;
    }
}

