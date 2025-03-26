package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Author;
import org.example.taskmanager.task.entity.Task;
import org.example.taskmanager.task.exception.DeleteFailedException;
import org.example.taskmanager.task.exception.InvalidEmailException;
import org.example.taskmanager.task.exception.UpdateFailedException;
import org.example.taskmanager.task.repository.IAuthorRepository;
import org.example.taskmanager.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;
    private final IAuthorRepository authorRepository;

    public TaskService(ITaskRepository taskRepository, IAuthorRepository authorRepository) {
        this.taskRepository = taskRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public TaskResponseDto createTask(TaskRequestDto dto) {

        //이메일 비밀번호 검증
        String email = dto.getAuthorEmail();
        String password = dto.getAuthorPassword();
        Author author = authorRepository.vertifyAuthorByEmailPassword(email, password);
        return taskRepository.saveTask(dto, author.getName());
    }

    @Override
    public TaskResponseDto findTaskByIdOrElseThrow(Long id) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        Author author = authorRepository.getAuthor(task.getAuthorEmail());
        return toResponseDto(task, author.getName());
    }


    @Override
    public List<TaskResponseDto> findTaskByPage(Long page, String email) {
        Author author = authorRepository.getAuthor(email);
        List<Task> taskList = taskRepository.findTaskByPage(page, email);
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
        if (!task.getAuthorEmail().equals(dto.getAuthorEmail())) {
            throw new InvalidEmailException();
        }
        Author author = authorRepository.vertifyAuthorByEmailPassword(dto.getAuthorEmail(), dto.getAuthorPassword());

        int row = taskRepository.updateTaskName(task.getId(), updateTaskName);
        if (row == 0) {
            throw new UpdateFailedException();
        }
        return toResponseDto(taskRepository.findTaskByIdOrElseThrow(id), author.getName());
    }


    @Transactional
    @Override
    public void deleteTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findTaskByIdOrElseThrow(id);
        if (!task.getAuthorEmail().equals(dto.getAuthorEmail())) {
            throw new InvalidEmailException();
        }
        authorRepository.vertifyAuthorByEmailPassword(dto.getAuthorEmail(), dto.getAuthorPassword());
        int row = taskRepository.deleteTask(id);
        if (row == 0) {
            throw new DeleteFailedException();
        }

    }

    private TaskResponseDto toResponseDto(Task task, String authorName) {

        return new TaskResponseDto(task.getId(), task.getTaskName(), authorName, task.getAuthorEmail(), task.getCreated_at(), task.getUpdated_at());
    }


}
