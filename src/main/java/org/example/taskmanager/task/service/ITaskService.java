package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;

import java.util.List;

public interface ITaskService {

    TaskResponseDto saveTask(TaskRequestDto dto);
    TaskResponseDto findTaskById(Long id);
    TaskResponseDto findTaskByIdOrElseThrow(Long id);
    List<TaskResponseDto> findTaskAll(TaskRequestDto dto);
    TaskResponseDto updateTask(Long id, TaskRequestDto dto);
    void deleteTask(Long id);
}
