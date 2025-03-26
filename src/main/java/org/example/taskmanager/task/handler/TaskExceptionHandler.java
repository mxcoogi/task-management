package org.example.taskmanager.task.handler;

import jakarta.validation.ConstraintViolationException;
import org.example.taskmanager.task.dto.ErrorDto;
import org.example.taskmanager.task.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(AlreadyExistEmailException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExistEmailException(AlreadyExistEmailException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEmailNotFoundException(EmailNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDto> handleInvalidPasswordException(InvalidPasswordException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<ErrorDto> handleUpdateFailedException(UpdateFailedException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<ErrorDto> handleDeleteFailedException(DeleteFailedException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorDto> handleInvalidEmailException(InvalidEmailException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDto, errorDto.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDto, errorDto.getHttpStatus());
    }


    /**
     * @param ex valid exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<ErrorDto> handleMissingRequestValueException(MissingRequestValueException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), (HttpStatus) ex.getStatusCode());

        return new ResponseEntity<>(errorDto, ex.getStatusCode());
    }


}
