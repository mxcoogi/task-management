package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.entity.Author;
import org.example.taskmanager.task.exception.AlreadyExistEmailException;
import org.example.taskmanager.task.exception.EmailNotFoundException;
import org.example.taskmanager.task.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorRepository implements IAuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author saveAuthor(String email, String password, String name) {

        try {
            Long id = vertifyAuthorByEmail(email);
            //throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 email 입니다");
            throw new AlreadyExistEmailException();
        } catch (EmptyResultDataAccessException e) {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("author").usingGeneratedKeyColumns("id");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("email", email);
            parameters.put("password", password);
            parameters.put("name", name);
            LocalDate created_at = LocalDate.now();
            LocalDate updated_at = LocalDate.now();
            parameters.put("created_at", created_at);
            parameters.put("updated_at", updated_at);
            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
            return new Author(key.longValue(), email, name, password, created_at, updated_at);
        }

    }

    private Long vertifyAuthorByEmail(String email) throws RuntimeException {
        return jdbcTemplate.queryForObject("select id from author where email = ?", Long.class, email);
    }

    @Override
    public Author vertifyAuthorByEmailPassword(String email, String password) {
        Author author = jdbcTemplate.query("select * from author where email = ?", authorRowMapper(), email)
                .stream().findAny().orElseThrow(() -> new EmailNotFoundException());
        if (!author.getPassword().equals(password)) {
            throw new InvalidPasswordException();
        }
        return author;
    }


    @Override
    public Author getAuthor(String email) {

        return jdbcTemplate.query("select * from author where email = ?", authorRowMapper(), email)
                .stream().findAny().orElseThrow(() -> new EmailNotFoundException());
    }

    @Override
    public int updateAuthorName(String email, String name) {
        LocalDate update_at = LocalDate.now();
        return jdbcTemplate.update("UPDATE author SET name = ?, updated_at = ? WHERE email = ?", name, update_at, email);
    }


    private RowMapper<Author> authorRowMapper() {
        return new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Author(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("created_at").toLocalDate()
                );
            }
        };
    }
}
