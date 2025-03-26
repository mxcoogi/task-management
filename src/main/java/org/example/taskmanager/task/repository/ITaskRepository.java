package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;

import java.util.List;

public interface ITaskRepository {

    Task findTaskByIdOrElseThrow(Long id);

    List<Task> findTaskByPage(Long page, String email);

    TaskResponseDto saveTask(TaskRequestDto dto, String authorName);

    int updateTaskName(Long id, String taskName);

    int deleteTask(Long id);

}
