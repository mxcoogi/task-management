package org.example.taskmanager.task.controller;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestBody TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.saveTask(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTask(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(taskService.findTaskByIdOrElseThrow(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findTaskAll(
            @RequestBody TaskRequestDto dto
    ){
        return new ResponseEntity<>(taskService.findTaskAll(dto), HttpStatus.OK);
    }
}
