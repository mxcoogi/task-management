package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;

public interface ITaskRepository {

    TaskResponseDto saveTask(TaskRequestDto dto);
}
