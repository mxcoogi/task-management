package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AlreadyExistEmailException extends RuntimeException {

    private static final String MESSAGE = "already exist email";
    @Getter
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public AlreadyExistEmailException() {
        super(MESSAGE);
    }

    public HttpStatus getStatus() {
        return STATUS;
    }

}
