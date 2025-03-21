package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;

import java.util.Optional;

public interface ITaskRepository {

    Optional<Task> findTaskByID(Long id);
    TaskResponseDto saveTask(Task task);
}
