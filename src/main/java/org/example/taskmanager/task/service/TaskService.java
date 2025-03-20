package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService{

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto saveTask(TaskRequestDto dto) {
        //logic 작성
        return taskRepository.saveTask(dto);
    }
}
