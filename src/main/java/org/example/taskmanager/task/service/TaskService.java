package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto saveTask(TaskRequestDto dto) {
        //logic 작성
        Task task = new Task(dto.getTaskName(), dto.getAuthorName(), dto.getPassword());

        return taskRepository.saveTask(task);
    }

    @Override
    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
    }


//    @Override
//    public TaskResponseDto findTaskById(Long id) {
//        Optional<Task> taskById = taskRepository.findTaskById(id);
//        if(taskById.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        Task task = taskById.get();
//        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
//    }

}
