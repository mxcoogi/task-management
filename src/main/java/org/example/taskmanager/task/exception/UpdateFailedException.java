package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UpdateFailedException extends RuntimeException {
    private static final String MESSAGE = "Failed Update";
    @Getter
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public UpdateFailedException() {
        super(MESSAGE);
    }
    public HttpStatus getStatus() {
        return STATUS;
    }
}
