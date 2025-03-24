package org.example.taskmanager.task.repository;

import org.example.taskmanager.task.dto.AuthorResponseDto;

public interface IAuthorRepository {

    AuthorResponseDto saveAuthor(String email, String name);
    Long getAuthorId(String email);
    String getAuthorEmail(Long id);
}
