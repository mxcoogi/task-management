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
    public TaskResponseDto createTask(TaskRequestDto dto) {

        //이메일 비밀번호 검증
        String email = dto.getAuthorEmail();
        String password = dto.getAuthorPassword();
        Author author = authorRepository.vertifyAuthorByEmailPassword(email, password);
        dto.setAuthorName(author.getName());
        return taskRepository.saveTask(dto);
    }

    @Override
    public TaskResponseDto findTaskByIdOrElseThrow(Long id) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        Author author = authorRepository.getAuthor(task.getAuthorEmail());
        return toResponseDto(task, author.getName());
    }


    @Override
    public List<TaskResponseDto> findTaskAll(TaskRequestDto dto) {
        //요청값 가져오기
        String authorEmail = dto.getAuthorEmail();

        //레포에서 작성자 정보 가져오기
        Author author = authorRepository.getAuthor(authorEmail);
        //레포에서 리스트 가져오기
        List<Task> allTask = taskRepository.findTaskAll();
        //비교 및 대입
        List<TaskResponseDto> result;
        result = allTask.stream()
                .filter(s -> authorEmail == null || authorEmail.equals(s.getAuthorEmail())) //2번 필터
                .map((Task task) -> toResponseDto(task, author.getName())).toList();
        return result;
    }

    @Override
    public List<TaskResponseDto> findTaskByPage(Long page, String email) {
        Author author = authorRepository.getAuthor(email); //id가 null값이면..? 그냥 무시하고 조회한다
        Long id = author.getId();
        List<Task> taskList = taskRepository.findTaskByPage(page, id);
        return taskList.stream().map((Task task) -> toResponseDto(task, author.getName())).toList();
    }


    @Transactional
    @Override
    public TaskResponseDto updateTaskName(Long id, TaskRequestDto dto) {

        String updateTaskName = dto.getTaskName();
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if (updateTaskName == null) {
            updateTaskName = task.getTaskName();
        }
        Author author = authorRepository.vertifyAuthorByEmailPassword(dto.getAuthorEmail(), dto.getAuthorPassword())

        int row = taskRepository.updateTaskName(task.getId(), updateTaskName);

        if (row == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toResponseDto(taskRepository.findTaskByIdOrElseThrow(id),author.getName());
    }


    @Override
    public void deleteTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if (!validPassword(task.getPassword(), dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        int row = taskRepository.deleteTask(id);
        if (row == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private TaskResponseDto toResponseDto(Task task, String authorName) {

        return new TaskResponseDto(task.getId(), task.getTaskName(), authorName, task.getAuthorEmail(), task.getCreated_at(), task.getUpdated_at());
    }


}
