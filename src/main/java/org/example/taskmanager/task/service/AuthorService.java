package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.repository.IAuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService{

    private final IAuthorRepository authorRepository;

    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        return null;
    }
}
