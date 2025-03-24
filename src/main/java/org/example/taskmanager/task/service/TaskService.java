package org.example.taskmanager.task.service;

import lombok.extern.slf4j.Slf4j;
import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Author;
import org.example.taskmanager.task.entity.Task;
import org.example.taskmanager.task.repository.IAuthorRepository;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Long authorId;
        Optional<Author> author = authorRepository.getAuthor(dto.getAuthorEmail());
        if(author.isEmpty()){
            AuthorResponseDto authorResponseDto = authorRepository.saveAuthor(dto.getAuthorEmail(), dto.getAuthorName());
            authorId = authorResponseDto.getId();
        }else{
            authorId = author.get().getId();
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
        String authorEmail = dto.getAuthorEmail();
        LocalDate updated_at = dto.getUpdated_at();

        //레포에서 리스트 가져오기
        List<Task> allTask = taskRepository.findTaskAll();
        Optional<Author> author = authorRepository.getAuthor(authorEmail); //id가 null값이면..? 그냥 무시하고 조회한다
        Long authorId;
        if(author.isEmpty()){
            authorId = null;
        }else{
            authorId = author.get().getId();
        }
        //비교 및 대입
        List<TaskResponseDto> result;
        result = allTask.stream()
                .filter(s-> updated_at==null || updated_at.equals("")|| updated_at.isEqual(s.getUpdated_at())) //1번 필터
                .filter(s -> authorId == null || authorId.equals(s.getAuthorId())) //2번 필터
                .map(this::toResponseDto).toList();
        return result;
    }


    @Transactional
    @Override
    public TaskResponseDto updateTaskName(Long id, TaskRequestDto dto) {

        String updateTaskName = dto.getTaskName();
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if(!validPassword(task.getPassword(), dto.getPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(updateTaskName == null){
            updateTaskName = task.getTaskName();
        }

        int row = taskRepository.updateTaskName(task.getId(), updateTaskName);

        if(row == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toResponseDto(taskRepository.findTaskByIdOrElseThrow(id));

    }

    @Override
    public AuthorResponseDto updateAuthorName(AuthorRequestDto dto){
        String email = dto.getEmail();
        String name = dto.getName();
        int row = authorRepository.updateAuthorName(email, name);
        if(row == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<Author> author = authorRepository.getAuthor(email);
        if(author.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Author responseAuthor = author.get();
        return new AuthorResponseDto(responseAuthor.getId(), responseAuthor.getName(), responseAuthor.getEmail(), responseAuthor.getCreated_at(), responseAuthor.getUpdated_at());
    }


    @Override
    public void deleteTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if(!validPassword(task.getPassword(), dto.getPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        int row = taskRepository.deleteTask(id);
        if(row == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private TaskResponseDto toResponseDto(Task task){

        return new TaskResponseDto(task.getId(), task.getTaskName(), task.getAuthorId(), task.getCreated_at(), task.getUpdated_at());
    }

    private boolean validPassword(String taskPassword, String dtoPassword){
        if(dtoPassword == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return taskPassword.equals(dtoPassword);
    }




}
