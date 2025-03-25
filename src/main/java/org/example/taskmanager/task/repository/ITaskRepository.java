package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    Optional<Task> findTaskById(Long id);
    Task findTaskByIdOrElseThrow(Long id);
    List<Task> findTaskAll();
    List<Task> findTaskByPage(Long page, Long id);
    TaskResponseDto saveTask(Task task);
    int updateTaskName(Long id, String taskName);
    int deleteTask(Long id);

}
