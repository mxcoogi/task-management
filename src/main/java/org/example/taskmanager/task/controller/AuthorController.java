package org.example.taskmanager.task.controller;


import org.example.taskmanager.task.service.IAuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }


}
