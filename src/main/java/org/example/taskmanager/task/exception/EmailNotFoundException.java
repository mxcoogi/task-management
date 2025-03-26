package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Not Found Email";
    @Getter
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public EmailNotFoundException() {
        super(MESSAGE);
    }

    public HttpStatus getStatus() {
        return STATUS;
    }
}
