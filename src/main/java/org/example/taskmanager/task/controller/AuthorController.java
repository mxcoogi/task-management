package org.example.taskmanager.task.controller;


import jakarta.validation.Valid;
import org.example.taskmanager.task.dto.AuthorRequestDto;
import org.example.taskmanager.task.dto.AuthorResponseDto;
import org.example.taskmanager.task.service.IAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * 작성자 생성
     *
     * @param dto email, password, name
     * @return AuthorResponseDto
     */
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(
            @RequestBody @Valid AuthorRequestDto dto
    ) {
        return new ResponseEntity<>(authorService.createAuthor(dto), HttpStatus.CREATED);
    }

    /**
     * 작성자 정보 업데이트
     *
     * @param dto email, password, name
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @RequestBody @Valid AuthorRequestDto dto
    ) {
        return new ResponseEntity<>(authorService.updateAuthor(dto), HttpStatus.OK);
    }


}
