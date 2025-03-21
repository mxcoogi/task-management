package org.example.taskmanager.task.service;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        //요청값 가져오기
        String authorName = dto.getAuthorName();
        LocalDate updated_at = dto.getUpdated_at();

        //레포에서 리스트 가져오기
        List<Task> allTask = taskRepository.findTaskAll();

        //비교 및 대입
        List<TaskResponseDto> result;
        result = allTask.stream()
                .filter(s-> updated_at==null|| updated_at.isEqual(s.getUpdated_at())) //1번 필터
                .filter(s -> authorName == null || authorName.equals(s.getAuthorName())) //2번 필터
                .map(this::toResponseDto).toList();
        return result;
    }
    //전제조회 조건 해야댐 RequestTask에서 날짜형식 받는거 생각해야댐

    private TaskResponseDto toResponseDto(Task task){
        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
    }




}
