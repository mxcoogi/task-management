package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
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
    public TaskResponseDto findTaskByIdOrElseThrow(Long id) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
    }
    /**
     *
     * @param id 작업 식별자
     * @deprecated
     * @return TaskResponseDto
     */
    @Override
    public TaskResponseDto findTaskById(Long id) {
        Optional<Task> taskById = taskRepository.findTaskById(id);
        if(taskById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Task task = taskById.get();
        return toResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> findTaskAll(TaskRequestDto dto) {
        String authorName = dto.getAuthorName();
        LocalDateTime updated_at = dto.getUpdated_at();
        LocalDate date = updated_at.toLocalDate();
        List<Task> allTask = taskRepository.findTaskAll();
        List<TaskResponseDto> result;
        result = allTask.stream()
                .filter(s-> date.isEqual(s.getUpdated_at().toLocalDate()) && authorName.equals(s.getAuthorName()))
                .map(this::toResponseDto).toList();
        return result;
    }
    //전제조회 조건 해야댐 RequestTask에서 날짜형식 받는거 생각해야댐

    private TaskResponseDto toResponseDto(Task task){
        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
    }




}
