package org.example.taskmanager.task.repository;


import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository implements ITaskRepository{
    @Override
    public TaskResponseDto saveTask(TaskRequestDto dto) {


        return new TaskResponseDto();
    }
}
