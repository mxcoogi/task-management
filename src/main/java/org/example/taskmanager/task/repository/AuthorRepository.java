package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorRepository implements IAuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AuthorResponseDto saveAuthor(String email, String name) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("author").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("name", name);
        LocalDate created_at  = LocalDate.now();
        LocalDate updated_at  = LocalDate.now();
        parameters.put("created_at", created_at);
        parameters.put("updated_at", updated_at);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new AuthorResponseDto(key.longValue(), name, email, created_at, updated_at);
    }

    @Override
    public Long getAuthorId(String email) {
        Long id;
        try{
            id = jdbcTemplate.queryForObject("select * from author where email = ?", Long.class, email);
        } catch (DataAccessException e) {
            id = null;
        }
        return id;
    }

    @Override
    public String getAuthorEmail(Long id) {
        return "";
    }
}
