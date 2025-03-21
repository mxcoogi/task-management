package org.example.taskmanager.task.repository;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository implements ITaskRepository{


    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Task> findTaskByID(Long id) {

        return null;
    }

    @Override
    public TaskResponseDto saveTask(Task task) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorName", task.getAuthorName());
        parameters.put("taskName", task.getTaskName());
        parameters.put("password", task.getPassword());
        LocalDateTime created_at  = LocalDateTime.now();
        LocalDateTime updated_at  = LocalDateTime.now();
        parameters.put("created_at", created_at);
        parameters.put("updated_at", updated_at);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new TaskResponseDto(key.longValue(), task.getTaskName(), task.getAuthorName(), created_at, updated_at);
    }
}
