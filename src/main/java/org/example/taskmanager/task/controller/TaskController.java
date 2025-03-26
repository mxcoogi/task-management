package org.example.taskmanager.task.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * 작업 생성 API
     *
     * @param dto TaskRequestDto authorEmail, taskName, authorPassword
     * @return TaskResponseDto, HttpStatus
     */
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestBody @Valid TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.createTask(dto), HttpStatus.CREATED);
    }

    /**
     * task 단건 조회 API
     *
     * @param id task 식별자
     * @return TaskResponseDto, HttpStatus
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTask(
            @Positive @PathVariable Long id
    ) {
        return new ResponseEntity<>(taskService.findTaskByIdOrElseThrow(id), HttpStatus.OK);
    }

    /**
     * 작성자의 게시물 전체조회
     *
     * @param page        확인할 페이지 기본값 1
     * @param authorEmail 게시물 소유자의 이메일
     * @return List<TaskResponseDto> 작업 리스트
     */
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findTaskByPage(
            @RequestParam(value = "page", defaultValue = "1") @Positive Long page,
            @RequestParam(value = "authorEmail") @NotBlank @Email String authorEmail
    ) {
        return new ResponseEntity<>(taskService.findTaskByPage(page, authorEmail), HttpStatus.OK);
    }


    /**
     * taskName 변경 API
     *
     * @param id
     * @param dto email, password, taskName
     * @return TaskResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskName(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid TaskRequestDto dto
    ) {
        return new ResponseEntity<>(taskService.updateTaskName(id, dto), HttpStatus.OK);
    }


    /**
     * task 삭제 API
     *
     * @param id
     * @param dto email, password
     * @return HttpStatusCode
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid TaskRequestDto dto
    ) {
        taskService.deleteTask(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
