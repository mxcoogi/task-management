package org.example.taskmanager.task.handler;

import org.example.taskmanager.task.dto.ErrorDto;
import org.example.taskmanager.task.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(AlreadyExistEmailException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExistEmailException(AlreadyExistEmailException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEmailNotFoundException(EmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDto> handleInvalidPasswordException(InvalidPasswordException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<ErrorDto> handleUpdateFailedException(UpdateFailedException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<ErrorDto> handleDeleteFailedException(DeleteFailedException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }


}
