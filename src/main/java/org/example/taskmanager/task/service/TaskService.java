package org.example.taskmanager.task.service;

import lombok.extern.slf4j.Slf4j;
import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;
import org.example.taskmanager.task.repository.IAuthorRepository;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;
    private final IAuthorRepository authorRepository;

    public TaskService(ITaskRepository taskRepository, IAuthorRepository authorRepository) {
        this.taskRepository = taskRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public TaskResponseDto saveTask(TaskRequestDto dto) {
        //logic 작성
        //email로 작성자 테이블 확인 후 save
        Long authorId = authorRepository.getAuthorId(dto.getAuthorEmail());
        if(authorId == null){
            AuthorResponseDto authorResponseDto = authorRepository.saveAuthor(dto.getAuthorEmail(), dto.getAuthorName());
            authorId = authorResponseDto.getId();
        }

        Task task = new Task(dto.getTaskName(), dto.getPassword(), authorId);
        return taskRepository.saveTask(task);
    }

    @Override
    public TaskResponseDto findTaskByIdOrElseThrow(Long id) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        return toResponseDto(task);
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
                //.filter(s -> authorName == null || authorName.equals(s.getAuthorName())) //2번 필터
                .map(this::toResponseDto).toList();
        return result;
    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {

        String updateTaskName = dto.getTaskName();
        String updateAuthorName = dto.getAuthorName();

        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if(!validPassword(task.getPassword(), dto.getPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(updateTaskName == null){
            updateTaskName = task.getTaskName();
        }
        if(updateAuthorName == null){
            //updateAuthorName = task.getAuthorName();
        }
        int row = taskRepository.updateTask(task.getId(), updateTaskName, updateAuthorName);
        if(row == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toResponseDto(taskRepository.findTaskByIdOrElseThrow(id));

    }

    @Override
    public void deleteTask(Long id) {
        int row = taskRepository.deleteTask(id);
        if(row == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private TaskResponseDto toResponseDto(Task task){

        //return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorName(), task.getCreated_at(), task.getUpdated_at());
        return null;
    }

    private boolean validPassword(String taskPassword, String dtoPassword){
        if(dtoPassword == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return taskPassword.equals(dtoPassword);
    }




}
