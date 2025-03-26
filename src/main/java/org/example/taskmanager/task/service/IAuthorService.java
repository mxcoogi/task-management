package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;

public interface IAuthorService {

    AuthorResponseDto createAuthor(AuthorRequestDto dto);

    AuthorResponseDto updateAuthor(AuthorRequestDto dto);
}
