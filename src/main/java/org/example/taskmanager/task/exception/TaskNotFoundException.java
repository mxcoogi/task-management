package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Not Found Task";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    public TaskNotFoundException() {
        super(MESSAGE);
    }
    public HttpStatus getStatus() {
        return STATUS;
    }
}
