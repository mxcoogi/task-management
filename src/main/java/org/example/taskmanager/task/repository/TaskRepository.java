package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.TaskRequestDto;
import org.example.taskmanager.task.dto.TaskResponseDto;
import org.example.taskmanager.task.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository implements ITaskRepository {


    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Task> findTaskById(Long id) {
        List<Task> result = jdbcTemplate.query("select * from task where id = ?", taskRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Task findTaskByIdOrElseThrow(Long id) {
        List<Task> result = jdbcTemplate.query("select * from task where id = ?", taskRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Task> findTaskAll() {
        return jdbcTemplate.query("select * from task", taskRowMapper());
    }

    @Override
    public List<Task> findTaskByPage(Long page, Long id) {
        int limit = 5;
        Long offset = (page - 1) * limit;
        String query = "SELECT * FROM task WHERE authorId = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, taskRowMapper(), id, limit, offset);

    }


    @Override
    public TaskResponseDto saveTask(TaskRequestDto dto) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorEmail", dto.getAuthorEmail());
        parameters.put("taskName", dto.getTaskName());
        LocalDate created_at = LocalDate.now();
        LocalDate updated_at = LocalDate.now();
        parameters.put("created_at", created_at);
        parameters.put("updated_at", updated_at);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TaskResponseDto(key.longValue(), dto.getTaskName(), dto.getAuthorName(), dto.getAuthorEmail(), created_at, updated_at);
    }

    @Override
    public int updateTaskName(Long id, String taskName) {
        LocalDate update_at = LocalDate.now();
        return jdbcTemplate.update("UPDATE task SET taskName = ?, updated_at = ? WHERE id = ?", taskName, update_at, id);
    }


    @Override
    public int deleteTask(Long id) {

        return jdbcTemplate.update("delete from task where id = ?", id);
    }


    private RowMapper<Task> taskRowMapper() {
        return new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Task(
                        rs.getLong("id"),
                        rs.getString("taskName"),
                        rs.getString("authorEmail"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("updated_at").toLocalDate()
                );
            }
        };
    }

}
