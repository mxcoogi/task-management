package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;

public interface ITaskService {

    TaskResponseDto saveTask(TaskRequestDto dto);

}
