package org.example.taskmanager.task.controller;

import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;
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


    /**
     * @param dto TaskRequestDto
     * @return TaskResponseDto, HttpStatus
     */
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestBody TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.createTask(dto), HttpStatus.CREATED);
    }

    /**
     * id값으로 task 단건 조회
     *
     * @param id task 식별자
     * @return TaskResponseDto, HttpStatus
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTask(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(taskService.findTaskByIdOrElseThrow(id), HttpStatus.OK);
    }

    /**
     * authorName, updated_at 으로 task 전체 조회
     *
     * @param dto authorName, updated_at
     * @return List<TaskResponseDto>
     */
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findTaskAll(
            @RequestBody TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.findTaskAll(dto), HttpStatus.OK);
    }
    @GetMapping("/page/{page}")
    public ResponseEntity<List<TaskResponseDto>> findTaskByPage(
            @PathVariable Long page,
            @RequestBody AuthorRequestDto dto
    ){
        return new ResponseEntity<>(taskService.findTaskByPage(page, dto.getEmail()), HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskName(
            @PathVariable Long id,
            @RequestBody TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.updateTaskName(id, dto), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDto dto
    ) {
        taskService.deleteTask(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
