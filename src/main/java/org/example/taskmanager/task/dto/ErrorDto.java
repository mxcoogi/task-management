package org.example.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorDto {
    private String message;
    private HttpStatus httpStatus;

}
