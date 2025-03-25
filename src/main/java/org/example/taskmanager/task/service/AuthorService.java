package org.example.taskmanager.task.service;

import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.entity.Author;
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

        //비즈니스 로직
        Author author = authorRepository.saveAuthor(dto.getEmail(), dto.getPassword(), dto.getName());
        return toAuthorResponse(author);
    }
    private AuthorResponseDto toAuthorResponse(Author author){
        return new AuthorResponseDto(author.getId(), author.getName(), author.getEmail(), author.getCreated_at(), author.getUpdated_at());
    }

}
