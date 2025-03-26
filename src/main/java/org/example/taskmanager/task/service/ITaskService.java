package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;

import java.util.List;

public interface ITaskService {

    TaskResponseDto createTask(TaskRequestDto dto);

    TaskResponseDto findTaskByIdOrElseThrow(Long id);

    void deleteTask(Long id, TaskRequestDto dto);

    TaskResponseDto updateTaskName(Long id, TaskRequestDto dto);

    List<TaskResponseDto> findTaskByPage(Long page, String email);
}
