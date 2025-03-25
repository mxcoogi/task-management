package org.example.taskmanager.task.controller;


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
     *
     * @param dto email, name, password
     * @return
     */
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto dto){

        return new ResponseEntity<>(authorService.createAuthor(dto), HttpStatus.CREATED);
    }

    /**
     *
     * @param dto email, password, name
     * @return
     */
    @PutMapping
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @RequestBody AuthorRequestDto dto
    ){
        return new ResponseEntity<>(authorService.updateAuthor(dto), HttpStatus.OK);
    }


}
