package org.example.taskmanager.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class DeleteFailedException extends RuntimeException {
    private static final String MESSAGE = "Failed Delete";
  @Getter
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public DeleteFailedException() {
        super(MESSAGE);
    }
  public HttpStatus getStatus() {
    return STATUS;
  }
}
