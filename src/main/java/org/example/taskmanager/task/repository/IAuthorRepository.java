package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.entity.Author;

import java.util.Optional;

public interface IAuthorRepository {

    AuthorResponseDto saveAuthor(String email, String name);
    Optional<Author> getAuthor(String email);
    String getAuthorEmail(Long id);
}
